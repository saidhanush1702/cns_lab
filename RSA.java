import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // fixed small primes (example): p = 61, q = 53
        BigInteger p = new BigInteger("61");
        BigInteger q = new BigInteger("53");

        BigInteger n   = p.multiply(q);                            // n = 3233
        BigInteger phi = p.subtract(BigInteger.ONE)
                          .multiply(q.subtract(BigInteger.ONE));   // phi = 3120

        BigInteger e = new BigInteger("17");   // public key exponent (coprime with phi)
        BigInteger d = e.modInverse(phi);      // private key exponent

        System.out.print("Enter message (number < " + n + "): ");
        BigInteger m = sc.nextBigInteger();    // plaintext as number

        BigInteger c   = m.modPow(e, n);       // encryption: c = m^e mod n
        BigInteger dec = c.modPow(d, n);       // decryption: m = c^d mod n

        System.out.println("Ciphertext : " + c);
        System.out.println("Decrypted  : " + dec);

        sc.close();
    }
}