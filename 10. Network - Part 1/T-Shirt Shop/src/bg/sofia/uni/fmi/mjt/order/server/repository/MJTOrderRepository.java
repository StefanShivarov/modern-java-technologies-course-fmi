package bg.sofia.uni.fmi.mjt.order.server.repository;

import bg.sofia.uni.fmi.mjt.order.server.Response;
import bg.sofia.uni.fmi.mjt.order.server.destination.Destination;
import bg.sofia.uni.fmi.mjt.order.server.order.Order;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Color;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Size;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.TShirt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MJTOrderRepository implements OrderRepository {

    private static final String INVALID_MESSAGE_PREFIX = "invalid: ";
    private static final int INVALID_ORDER_ID = -1;
    private static final int INITIAL_ID_VALUE = 1;
    private int currentOrderId;
    private final List<Order> orders;

    public MJTOrderRepository() {
        orders = new ArrayList<>();
        currentOrderId = INITIAL_ID_VALUE;
    }

    @Override
    public Response request(String size, String color, String destination) {
        if (size == null || color == null || destination == null) {
            throw new IllegalArgumentException("Order request should not contain null elements!");
        }

        StringBuilder errorMessage = new StringBuilder(INVALID_MESSAGE_PREFIX);
        TShirt tShirt = new TShirt(Size.fromString(size), Color.fromString(color));
        Destination destinationEnum = Destination.fromString(destination);

        if (!validateEnums(tShirt.size(), tShirt.color(),
                destinationEnum, errorMessage).get()) {
            orders.add(new Order(INVALID_ORDER_ID, tShirt, destinationEnum));
            return Response.decline(errorMessage.toString());
        }

        orders.add(new Order(currentOrderId, tShirt, destinationEnum));
        return Response.create(currentOrderId++);
    }

    @Override
    public Response getOrderById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id can't be a non-positive number!");
        }

        return orders.stream()
                .filter(o -> o.id() == id)
                .findFirst()
                .map(value -> Response.ok(List.of(value)))
                .orElseGet(() -> Response.notFound(id));
    }

    @Override
    public Response getAllOrders() {
        return Response.ok(orders);
    }

    @Override
    public Response getAllSuccessfulOrders() {
        return Response.ok(
                orders.stream()
                        .filter(o -> o.id() != INVALID_ORDER_ID)
                        .toList()
        );
    }

    private AtomicBoolean validateEnums(
            Size size,
            Color color,
            Destination destination,
            StringBuilder errorMessage
    ) {
        AtomicBoolean areValid = new AtomicBoolean(true);
        if (size == Size.UNKNOWN) {
            areValid.set(false);
            errorMessage.append("size");
        }

        if (color == Color.UNKNOWN) {
            if (!areValid.get()) {
                errorMessage.append(",");
            }
            errorMessage.append("color");
            areValid.set(false);
        }

        if (destination == Destination.UNKNOWN) {
            if (!areValid.get()) {
                errorMessage.append(",");
            }
            errorMessage.append("destination");
            areValid.set(false);
        }

        return areValid;
    }

}
