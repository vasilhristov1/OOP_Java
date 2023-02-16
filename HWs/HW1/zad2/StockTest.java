package homework.zad2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class StockTest extends Application {
    private static Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static TextInputDialog dialog = new TextInputDialog();

    private void messageDialog(String infoMessage, String titleBar, String headerMessage) {
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public String inputDialog(String infoMessage, String titleBar, String headerMessage) {
        dialog.setTitle(titleBar);
        dialog.setHeaderText(headerMessage);
        dialog.setContentText(infoMessage);

        return dialog.showAndWait().get();
    }

    @Override
    public void start(Stage stage) {
        Stock stock = new Stock("APL", "apple");

        String priceInfoMessage = String.format("What is the current price of the stock with symbol - %s, and name - %s", stock.getSymbol(), stock.getName());
        double price = Double.parseDouble(inputDialog(priceInfoMessage, "User input", null));
        stock.setCurrentPrice(price);

        String previousPriceInfoMessage = String.format("What is the previous price of the stock with symbol - %s, and name - %s", stock.getSymbol(), stock.getName());
        double previousPrice = Double.parseDouble(inputDialog(previousPriceInfoMessage, "User input", null));
        stock.setPreviousClosingPrice(previousPrice);

        String message = String.format("The change in the price of the product %s is: %.2f%%", stock.getSymbol(), (stock.changePercent() * 100));
        messageDialog(message, "Info dialog", null);
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
