package homework.zad2;

public class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.previousClosingPrice = 0.0;
        this.currentPrice = 0.0;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public double getPreviousClosingPrice() {
        return this.previousClosingPrice;
    }

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public void setPreviousClosingPrice(double price) {
        this.previousClosingPrice = price;
    }

    public void setCurrentPrice(double price) {
        this.currentPrice = price;
    }

    public double changePercent() {
        return ((-1) * ((getPreviousClosingPrice() - getCurrentPrice()) / getPreviousClosingPrice()));
    }
}
