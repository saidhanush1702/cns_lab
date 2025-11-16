import java.math.BigInteger;
import java.util.*;

class RSA {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number to be encrypted and decrypted");
        BigInteger msg = BigInteger.valueOf(sc.nextInt());

        System.out.println("Enter 1st prime number p");
        BigInteger p = BigInteger.valueOf(sc.nextInt());

        System.out.println("Enter 2nd prime number q");
        BigInteger q = BigInteger.valueOf(sc.nextInt());

        BigInteger n = p.multiply(q);
        BigInteger z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        System.out.println("The value of z = " + z);

        // Find e such that gcd(e, z) = 1
        BigInteger e = BigInteger.TWO;
        while (e.compareTo(z) < 0) {
            if (e.gcd(z).equals(BigInteger.ONE)) {
                break;
            }
            e = e.add(BigInteger.ONE);
        }
        System.out.println("The value of e = " + e);

        // Find d such that (d * e) mod z = 1
        BigInteger d = BigInteger.ZERO;

        for (int i = 0; i <= 9; i++) {
            BigInteger x = BigInteger.ONE.add(z.multiply(BigInteger.valueOf(i)));
            if (x.mod(e).equals(BigInteger.ZERO)) {
                d = x.divide(e);
                break;
            }
        }

        System.out.println("The value of d = " + d);

        // Encryption: c = (msg^e) mod n
        BigInteger c = msg.modPow(e, n);
        System.out.println("Encrypted message is: ");
        System.out.println(c);

        // Decryption: msgback = (c^d) mod n
        BigInteger msgback = c.modPow(d, n);
        System.out.println("Decrypted message is: ");
        System.out.println(msgback);

        sc.close();
    }
}
