public class AESTest {

    public static void main(String[] args) {

        try {

            String original =
                    "What is Caesar Cipher?";

            String encrypted =
                    AESUtil.encrypt(original);

            String decrypted =
                    AESUtil.decrypt(encrypted);

            System.out.println(
                    "Original : " + original
            );

            System.out.println(
                    "Encrypted : " + encrypted
            );

            System.out.println(
                    "Decrypted : " + decrypted
            );

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}
