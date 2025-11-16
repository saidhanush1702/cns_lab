import java.util.Scanner;

public class SubstitutionCipher {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Plain alphabet
        String a = "abcdefghijklmnopqrstuvwxyz";

        // Substitution alphabet (reverse)
        String b = "zyxwvutsrqponmlkjihgfedcba";

        System.out.println("Enter any string: ");
        String str = sc.next();
        System.out.println("String is: " + str);

        String encrypt = "";
        String decrypt = "";

        char c, d;

        // ENCRYPTION
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            int j = a.indexOf(c);   // find position in plain alphabet
            encrypt += b.charAt(j); // map to substitution alphabet
        }

        System.out.println("The encrypted data is: " + encrypt);

        // DECRYPTION
        for (int i = 0; i < encrypt.length(); i++) {
            d = encrypt.charAt(i);
            int j = b.indexOf(d);   // find position in reverse alphabet
            decrypt += a.charAt(j); // map back to original alphabet
        }

        System.out.println("The decrypted data is: " + decrypt);
    }
}
