import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Generate AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); 
        SecretKey secretKey = keyGen.generateKey();

        // Create AES Cipher instance
        Cipher cipher = Cipher.getInstance("AES");

        System.out.print("Enter text to encrypt: ");
        String input = sc.nextLine();

        // ENCRYPT
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(input.getBytes());

        String encBase64 = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encrypted Text: " + encBase64);

        // DECRYPT
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted Text: " + new String(decrypted));

        sc.close();
    }
}

