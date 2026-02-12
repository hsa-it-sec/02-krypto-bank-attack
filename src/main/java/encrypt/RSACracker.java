package encrypt;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.Cipher;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Security;

public class RSACracker {

    // Hm, I need to get the public key from somewhere... and add it here.
    // Should be in the format:
    // -----BEGIN PUBLIC KEY-----
    // ... BASE64 ENCODED KEY ...
    // -----END PUBLIC KEY-----
    private static final String PUBLIC_KEY_PEM = "";

    // Hm, I also need to capture the ciphertext from somewhere... and add it here.
    private static final String CIPHERTEXT_IN_HEX_FORMAT = "";

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        int pin = 1234;
        String myCipherHex = encryptPin(pin);
        System.out.println("Encrypted PIN (hex): " + myCipherHex);

    }

    /*
     * Helper method to encrypt a given PIN using the provided RSA public key.
     */
    private static String encryptPin(int pin) throws Exception {
        String pinAsString = String.format("%04d", pin);
        PublicKey publicKey = readPemPublicKey(RSACracker.PUBLIC_KEY_PEM);
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] input = pinAsString.getBytes(StandardCharsets.UTF_8);
        byte[] cipherText = cipher.doFinal(input);

        return new BigInteger(1, cipherText).toString(16);
    }

    private static PublicKey readPemPublicKey(String pem) throws Exception {
        try (StringReader reader = new StringReader(pem);
             PEMParser pemParser = new PEMParser(reader)) {

            Object object = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();

            if (object instanceof SubjectPublicKeyInfo) {
                return converter.getPublicKey((SubjectPublicKeyInfo) object);
            }
            throw new IllegalArgumentException("Unsupported PEM Key format: " + object.getClass());
        }
    }
}

