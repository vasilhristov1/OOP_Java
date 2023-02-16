package homework.zad1;

public class Zad1 {
    public static void main(String[] args) {
        int countAll = 0;
        int count12 = 0;
        int number;
        double P;

        for (int i = 3; i <= 9; i++) {
            for (int j = 2; j <= 8; j++) {
                for (int m = 4; m <= 9; m++) {
                    for (int n = 1; n <= 6; n++) {
                        for (int t = 6; t <= 9; t++) {
                            number = (int) ((i * Math.pow(10, 4)) + (j * Math.pow(10, 3)) + (m * Math.pow(10, 2)) + (n * Math.pow(10, 1)) + t);
                            countAll++;

                            if ((number % 12) == 0) {
                                count12++;
                            }
                        }
                    }
                }
            }
        }

        P = count12 / (double) countAll;

        String allNumbersCount = String.format("The count of all numbers with the properties given in the task is: %d", countAll);
        String allNumbersDivBy12 = String.format("The count of all numbers divisible by 12 is: %d", count12);
        String probability = String.format("The probability of all numbers with the given properties to be divisible by 12 is: %.2f", P);

        System.out.println(probability);
        System.out.println(allNumbersCount);
        System.out.println(allNumbersDivBy12);
    }
}
