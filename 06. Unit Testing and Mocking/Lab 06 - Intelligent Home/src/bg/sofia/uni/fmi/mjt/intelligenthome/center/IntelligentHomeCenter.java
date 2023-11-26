package bg.sofia.uni.fmi.mjt.intelligenthome.center;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.comparator.DeviceRegistrationComparator;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.comparator.KWhComparator;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.DeviceType;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.DeviceStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IntelligentHomeCenter {

    private DeviceStorage storage;

    public IntelligentHomeCenter(DeviceStorage storage) {
        this.storage = storage;
    }

    /**
     * Adds a @device to the IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException         in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already registered.
     */
    public void register(IoTDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException("Device is null!");
        }

        if (storage.exists(device.getId())) {
            throw new DeviceAlreadyRegisteredException("Device is already registered!");
        }

        storage.store(device.getId(), device);
        device.setRegistration(LocalDateTime.now());
    }

    /**
     * Removes the @device from the IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException  in case the @device is not found.
     */
    public void unregister(IoTDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException("Device is null!");
        }

        if (!storage.exists(device.getId())) {
            throw new DeviceNotFoundException("Device not found!");
        }

        storage.delete(device.getId());
    }

    /**
     * Returns a IoTDevice with an ID @id.
     *
     * @throws IllegalArgumentException in case @id is null or blank.
     * @throws DeviceNotFoundException  in case device with ID @id is not found.
     */
    public IoTDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is null or blank!");
        }

        if (!storage.exists(id)) {
            throw new DeviceNotFoundException("Device with id " + id + " not found!");
        }

        return storage.get(id);
    }

    /**
     * Returns the total number of devices with type @type registered in IntelligentHomeCenter.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public int getDeviceQuantityPerType(DeviceType type) {
        if (type == null) {
            throw new IllegalArgumentException("Device type is null!");
        }

        int quantity = 0;
        for (IoTDevice device : storage.listAll()) {
            if (device.getType() == type) {
                quantity++;
            }
        }

        return quantity;
    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     * <p>
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N is a negative number!");
        }

        List<IoTDevice> devices = new ArrayList<>(storage.listAll());
        devices.sort(new KWhComparator());
        Collections.reverse(devices);
        List<String> sortedIds = new ArrayList<>();

        if (n > devices.size()) {
            n = devices.size();
        }

        for (int i = 0; i < n; i++) {
            sortedIds.add(devices.get(i).getId());
        }

        return sortedIds;
    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the IntelligentHomeCenter (registration != installation).
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<IoTDevice> getFirstNDevicesByRegistration(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N is a negative number!");
        }

        List<IoTDevice> devices = new ArrayList<>(storage.listAll());
        devices.sort(new DeviceRegistrationComparator());
        Collections.reverse(devices);
        List<IoTDevice> result = new ArrayList<>();

        if (n > devices.size()) {
            n = devices.size();
        }

        for (int i = 0; i < n; i++) {
            result.add(devices.get(i));
        }

        return result;
    }

}
