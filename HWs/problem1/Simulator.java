package problem1;

import java.util.ArrayList;
import java.util.Random;

public class Simulator {
    private ArrayList<Integer> heroes;
    private ArrayList<Integer> allHeroes;
    private double finalPrice;
    private Random rand = new Random();

    public Simulator() {
        this.heroes = new ArrayList<>();
        this.allHeroes = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            this.allHeroes.add(i);
        }
        this.finalPrice = 0.0;
    }

    public void simulate() {
        boolean isTrue = false;

        do {
            heroes.add(generateRandomNumber());
            finalPrice += 0.5;
            if (heroes.containsAll(allHeroes)) {
                isTrue = true;
            }
        } while (!isTrue);

        print();
    }

    private int generateRandomNumber() {
        return (1 + rand.nextInt(10));
    }

    public void print() {
        System.out.println("The random hero pictures: ");
        System.out.println(heroes);
        System.out.printf("The total price is: %.2f%n", finalPrice);
        System.out.printf("Is it possible all cards to be collected? %s%n", (isPossible() ? "Yes" : "No"));
    }

    private boolean isPossible() {
        return (this.finalPrice <= 7.0);
    }
}
