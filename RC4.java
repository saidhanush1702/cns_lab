import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Random;

public class RC4 {

    /* ==========================
       PART 1 : RC4 IMPLEMENTATION
       ========================== */

    static byte[] rc4Key;

    // Generate RC4 symmetric key
    static void generateRC4Key() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            byte[] seed = String.valueOf(num).getBytes();

            KeyGenerator keyGen = KeyGenerator.getInstance("RC4");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            keyGen.init(56, sr);

            SecretKey skey = keyGen.generateKey();
            rc4Key = skey.getEncoded();

            System.out.println("RC4 Symmetric Key: " + new String(rc4Key));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // RC4 encrypt
    static byte[] rc4Encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(raw, "RC4");
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(clear);
    }

    // RC4 decrypt
    static byte[] rc4Decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(raw, "RC4");
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(encrypted);
    }


    /* ==========================
       PART 2 : BLOWFISH ENCRYPTION
       ========================== */

    static void blowfishDemo() {
        try {
            String text = "Hello world";
            System.out.println("\nBlowfish Encryption");
            System.out.println("Plaintext: " + text);

            // Generate Blowfish key
            KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();

            Cipher cipher = Cipher.getInstance("Blowfish");

            // Encrypt
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.out.println("Encrypted (bytes): " + new String(encrypted));

            // Decrypt
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println("Decrypted text: " + new String(decrypted));

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /* ==========================
       MAIN METHOD (RUN BOTH)
       ========================== */

    public static void main(String[] args) {
        try {
            // ---- RC4 ----
            System.out.println("===== RC4 Encryption / Decryption =====");
            generateRC4Key();

            String msg = "Hello World";
            System.out.println("Plaintext: " + msg);

            byte[] encrypted = rc4Encrypt(rc4Key, msg.getBytes());
            System.out.println("Encrypted RC4: " + new String(encrypted));

            byte[] decrypted = rc4Decrypt(rc4Key, encrypted);
            System.out.println("Decrypted RC4: " + new String(decrypted));

            // ---- Blowfish ----
            blowfishDemo();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
