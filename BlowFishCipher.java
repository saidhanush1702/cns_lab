import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class BlowFishCipher {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Create Blowfish key
        KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
        SecretKey secretkey = keygenerator.generateKey();

        // Create Cipher for Blowfish
        Cipher cipher = Cipher.getInstance("Blowfish");

        System.out.print("Enter your message: ");
        String inputText = sc.nextLine();

        // Encrypt
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        byte[] encrypted = cipher.doFinal(inputText.getBytes());

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretkey);
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Encrypted text: " + new String(encrypted));
        System.out.println("Decrypted text: " + new String(decrypted));

        sc.close();
    }
}

