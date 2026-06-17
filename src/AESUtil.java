import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String SECRET_KEY =
            "1234567890123456";

    private static final SecretKeySpec secretKey =
            new SecretKeySpec(
                    SECRET_KEY.getBytes(),
                    "AES"
            );

    public static String encrypt(String text)
            throws Exception {

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey
        );

        byte[] encrypted =
                cipher.doFinal(
                        text.getBytes()
                );

        return Base64.getEncoder()
                .encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText)
            throws Exception {

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey
        );

        byte[] decoded =
                Base64.getDecoder()
                        .decode(encryptedText);

        byte[] decrypted =
                cipher.doFinal(decoded);

        return new String(decrypted);
    }

}
