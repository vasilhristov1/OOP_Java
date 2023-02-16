package hw6.problem3;

import hw6.problem1.Category;
import hw6.problem1.Product;
import hw6.problem2.ListOfProducts;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JfxSceneController {
    private ListOfProducts<Product> listOfProducts;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField categoryText;
    @FXML
    private TextField priceText;
    @FXML
    private Text showText;

    @FXML
    void initialize() {
        listOfProducts = new ListOfProducts<>();
        listOfProducts.setup();
    }

    @FXML
    protected void onAddToListButtonClick() {
        Random rand = new Random();
        int idNum = rand.nextInt();

        String descriptionTxt = descriptionText.getText();
        double priceTxt = Double.parseDouble(priceText.getText());
        String categoryTxt = categoryText.getText();
        Category catTxt = switch (categoryTxt) {
            case "B" -> Category.B;
            case "C" -> Category.C;
            case "D" -> Category.D;
            default -> Category.A;
        };

        Product product = new Product(idNum, descriptionTxt, catTxt, priceTxt);
        listOfProducts.addProduct(product);
    }

    @FXML
    protected void onSortProductsButtonClick() {
        Function<Product, Double> byPrice = Product::getPrice;

        Comparator<Product> highToLow = Comparator.comparing(byPrice);
        List<Product> list = listOfProducts.getProducts().stream()
                .sorted(highToLow.reversed())
                .collect(Collectors.toList());

        String result = list.toString();

        showText.setText(result);
    }

    @FXML
    protected void onShowProductsButtonClick() {
        double priceTxt = Double.parseDouble(priceText.getText());
        Predicate<Product> moreThanTheEntered = p -> (p.getPrice() > priceTxt);

        String result;

        List<Product> list = listOfProducts.getProducts().stream()
                        .filter(moreThanTheEntered)
                                .collect(Collectors.toList());

        result = list.toString();

        showText.setText(result);
    }

    @FXML
    protected void onAveragePriceButtonClick() {
        String avgPriceTxt = String.format("The average price of all the products is: %.2f%n", listOfProducts.averagePrice());

        showText.setText(avgPriceTxt);
    }

    @FXML
    protected void onGroupByCategoryButtonClick() {
        Map<Category, List<Product>> groupByCategory =
                listOfProducts.getProducts().stream().collect(Collectors.groupingBy(Product::getCategory));

        showText.setText(groupByCategory.toString());
    }

}
