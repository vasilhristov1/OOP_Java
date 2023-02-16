package algebra;

public class AlgebraUtils {
    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsB = b[0].length;
        int rowsB = b.length;
        int colsA = a[0].length;

        if (colsA != rowsB) {
            return null;
        }

        double[][] c = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < rowsB; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }
}
