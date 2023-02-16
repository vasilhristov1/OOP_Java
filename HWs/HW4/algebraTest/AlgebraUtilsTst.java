package hw4;

import algebra.AlgebraUtils;
import java.util.Scanner;

public class AlgebraUtilsTst {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 3;

        double[][] a = new double[N][N];
        double[][] b = new double[N][N];

        System.out.println("Enter matrix a: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Enter matrix b: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                b[i][j] = scanner.nextDouble();
            }
        }

        double[][] c = AlgebraUtils.multiplyMatrix(a, b);

        for (double[] doubles : c) {
            for (int j = 0; j < c[0].length; j++) {
                System.out.printf("%.2f ", doubles[j]);
            }

            System.out.println();
        }
    }
}
