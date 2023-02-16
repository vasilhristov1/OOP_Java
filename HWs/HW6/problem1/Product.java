package hw6.problem1;

public class Product {
    private final int INV_NUMBER;
    private String invDescription;
    private Category category;
    private double price;

    public Product() {
        this.INV_NUMBER = 0;
        setInvDescription("");
        setCategory(Category.A);
        setPrice(0.0);
    }

    public Product(int inv_number, String invDescription, Category category, double price) {
        this.INV_NUMBER = inv_number;
        setInvDescription(invDescription);
        setCategory(category);
        setPrice(price);
    }

    public Product(Product product) {
        this.INV_NUMBER = product.getINV_NUMBER();
        setInvDescription(product.getInvDescription());
        setCategory(product.getCategory());
        setPrice(product.getPrice());
    }

    public int getINV_NUMBER() {
        return this.INV_NUMBER;
    }

    public String getInvDescription() {
        return this.invDescription;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setInvDescription(String invDescription) {
        this.invDescription = invDescription;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product No: %d %nDescription: '%s' %nPrice: %.2f",
                getINV_NUMBER(), getInvDescription(), getPrice());
    }
}
