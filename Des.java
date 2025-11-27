import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Scanner;

public class Des {

    // Utility: convert byte[] to hex string for display
    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            System.out.print("Enter 8-character key (DES uses 8 bytes): ");
            String keyStr = sc.nextLine();

            if (keyStr.length() != 8) {
                System.out.println("Key must be exactly 8 characters for DES.");
                return;
            }

            // 1. Prepare key
            byte[] keyBytes = keyStr.getBytes("UTF-8");
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            // 2. Create Cipher object for DES (ECB mode with padding)
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // 3. Encrypt
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] ciphertextBytes = cipher.doFinal(plaintext.getBytes("UTF-8"));

            // 4. Decrypt
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);

            String decryptedText = new String(decryptedBytes, "UTF-8");

            // 5. Display results
            System.out.println("\n--- DES Encryption Demo ---");
            System.out.println("Plaintext     : " + plaintext);
            System.out.println("Key (string)  : " + keyStr);
            System.out.println("CiphertextHex : " + toHex(ciphertextBytes));
            System.out.println("DecryptedText : " + decryptedText);

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
