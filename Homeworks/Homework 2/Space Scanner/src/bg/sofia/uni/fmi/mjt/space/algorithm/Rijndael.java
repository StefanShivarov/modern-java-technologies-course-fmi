package bg.sofia.uni.fmi.mjt.space.algorithm;

import bg.sofia.uni.fmi.mjt.space.exception.CipherException;

import javax.crypto.SecretKey;
import java.io.InputStream;
import java.io.OutputStream;

public class Rijndael implements SymmetricBlockCipher {

    //TODO: implement
    public Rijndael(SecretKey secretKey) {

    }

    @Override
    public void encrypt(InputStream inputStream, OutputStream outputStream) throws CipherException {

    }

    @Override
    public void decrypt(InputStream inputStream, OutputStream outputStream) throws CipherException {

    }
}
