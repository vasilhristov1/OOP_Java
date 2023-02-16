package hw6.problem2;

import hw6.problem1.Category;
import hw6.problem1.Product;

import java.util.ArrayList;
import java.util.Random;

public class ListOfProducts <E extends Product> {
    private ArrayList<E> products;

    public ListOfProducts () {
        this.products = new ArrayList<>();
    }

    public ArrayList<E> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<E> products) {
        this.products = products;
    }

    public void addProduct(E p) {
        products.add(p);
    }

    public String[] toArray() {
        String[] productsStr = new String[getProducts().size()];

        for (int i = 0; i < getProducts().size(); i++) {
            productsStr[i] = getProducts().get(i).toString();
        }

        return productsStr;
    }

    public void setup() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int number = rand.nextInt();
            int category = rand.nextInt(1, 5);
            Category cat;
            switch (category) {
                case 1:
                    cat = Category.A;
                    break;
                case 2:
                    cat = Category.B;
                    break;
                case 3:
                    cat = Category.C;
                    break;
                case 4:
                    cat = Category.D;
                    break;
                default:
                    cat = Category.A;
            }
            double price = rand.nextDouble();
            String descr = String.format("This is product with number %d, which costs: %.2f%n", number, price);
            E product = (E) new Product(number, descr, cat, price);
            products.add(product);
        }
    }

    public double averagePrice() {
        double sumPrice = 0.0;
        double avgPrice = 0.0;

        for (int i = 0; i < getProducts().size(); i++) {
            sumPrice += getProducts().get(i).getPrice();
        }

        avgPrice = sumPrice / (double) getProducts().size();

        return avgPrice;
    }

    public String toString() {
        String description = "[";

        for (int i = 0; i < getProducts().size(); i++) {
            description = description + getProducts().get(i).getInvDescription();

            if (i + 1 < getProducts().size()) {
                description = description + ", ";
            }
        }

        description = description + "]";

        return description;
    }

}
