package bg.sofia.uni.fmi.mjt.space.algorithm;

import bg.sofia.uni.fmi.mjt.space.exception.CipherException;
import bg.sofia.uni.fmi.mjt.space.rocket.Rocket;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class Rijndael implements SymmetricBlockCipher {

    private static final String AES_ENCRYPTION_ALGORITHM = "AES";
    private static final int BUFFER_LENGTH = 1024;
    private final SecretKey secretKey;

    public Rijndael(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void encrypt(InputStream inputStream, OutputStream outputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {
                processStreamData(inputStream, cipherOutputStream);
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new CipherException("Error occurred while encrypting data!", e);
        }
    }

    @Override
    public void decrypt(InputStream inputStream, OutputStream outputStream) throws CipherException {
        try {
            Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            try (CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher)) {
                processStreamData(cipherInputStream, outputStream);
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new CipherException("Error occurred while decrypting data!", e);
        }
    }

    private void processStreamData(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[BUFFER_LENGTH];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

    public void serializeRocket(Rocket rocket, OutputStream outputStream) throws CipherException {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        ) {
            objectOutputStream.writeUTF(rocket.id());
            objectOutputStream.writeUTF(rocket.name());
            writeOptionalString(objectOutputStream, rocket.wiki());
            writeOptionalDouble(objectOutputStream, rocket.height());
            objectOutputStream.flush();

            encrypt(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), outputStream);
        } catch (IOException e) {
            throw new CipherException("Error occurred while encrypting rocket!", e);
        }
    }

    public Rocket deserializeRocket(InputStream inputStream) throws CipherException {
        try {
            ByteArrayOutputStream decryptedData = new ByteArrayOutputStream();
            decrypt(inputStream, decryptedData);

            try (ObjectInputStream objectInputStream = new ObjectInputStream(
                    new ByteArrayInputStream(decryptedData.toByteArray()))
            ) {
                String id = objectInputStream.readUTF();
                String name = objectInputStream.readUTF();
                Optional<String> wiki = readOptionalString(objectInputStream);
                Optional<Double> height = readOptionalDouble(objectInputStream);

                return new Rocket(id, name, wiki, height);
            }
        } catch (IOException e) {
            throw new CipherException("Error occurred while decrypting rocket!", e);
        }
    }

    private void writeOptionalString(ObjectOutputStream objectOutputStream, Optional<String> optional) throws IOException {
        if (optional.isPresent()) {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.writeUTF(optional.get());
        } else {
            objectOutputStream.writeBoolean(false);
        }
    }

    private void writeOptionalDouble(ObjectOutputStream objectOutputStream, Optional<Double> optional) throws IOException {
        if (optional.isPresent()) {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.writeDouble(optional.get());
        } else {
            objectOutputStream.writeBoolean(false);
        }
    }

    private Optional<String> readOptionalString(ObjectInputStream objectInputStream) throws IOException {
        if (objectInputStream.readBoolean()) {
            return Optional.of(objectInputStream.readUTF());
        } else {
            return Optional.empty();
        }
    }

    private Optional<Double> readOptionalDouble(ObjectInputStream objectInputStream) throws IOException {
        if (objectInputStream.readBoolean()) {
            return Optional.of(objectInputStream.readDouble());
        } else {
            return Optional.empty();
        }
    }

}
