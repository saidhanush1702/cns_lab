import java.util.Scanner;

public class HillCipher {

    static float[][] decrypt = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] mes = new float[3][1];
    static float[][] res = new float[3][1];

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        getKeyAndMessage();

        // Encryption: matrix multiplication
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                res[i][0] += a[i][k] * mes[k][0];

        System.out.print("\nEncrypted string is : ");
        for (int i = 0; i < 3; i++)
            System.out.print((char) (res[i][0] % 26 + 97));

        inverse();

        // Decryption: matrix multiplication with inverse matrix
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                decrypt[i][0] += b[i][k] * res[k][0];

        System.out.print("\nDecrypted string is : ");
        for (int i = 0; i < 3; i++)
            System.out.print((char) (decrypt[i][0] % 26 + 97));

        System.out.println();
    }

    public static void getKeyAndMessage() {
        System.out.println("Enter 3x3 matrix for key (It should be invertible): ");

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextFloat();

        System.out.print("\nEnter a 3 letter string: ");
        String msg = sc.next().toLowerCase();

        for (int i = 0; i < 3; i++)
            mes[i][0] = msg.charAt(i) - 'a';
    }

    public static void inverse() {
        float p, q;
        float[][] c = new float[3][3];

        // Copy matrix a into c
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                c[i][j] = a[i][j];

        // Initialize b as identity matrix
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                b[i][j] = (i == j ? 1 : 0);

        // Perform row operations to find inverse
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {

                p = c[i][k];
                q = c[k][k];

                for (int j = 0; j < 3; j++) {
                    if (i != k) {
                        c[i][j] = c[i][j] * q - p * c[k][j];
                        b[i][j] = b[i][j] * q - p * b[k][j];
                    }
                }
            }
        }

        // Divide each row by pivot to finish inverse
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                b[i][j] /= c[i][i];

        System.out.println("\nInverse Matrix is : ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }
}
