package hw7.problem1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;

public class JFXSceneController {
    private MilesPerGallon milesPerGallon;

    @FXML
    private TextField miles_driven;
    @FXML
    private TextField gallons_used;
    @FXML
    private Text showText;

    @FXML
    void initialize() {
        milesPerGallon = new MilesPerGallon();
    }

    @FXML
    protected void onCalculateButtonClick() {
        double m = 0.0;
        double g = 0.0;
        boolean check = false;

        try {
            m = Double.parseDouble(miles_driven.getText());
            g = Double.parseDouble(gallons_used.getText());
        } catch (NumberFormatException nfe) {
            check = true;
        }

        if (!check) {
            milesPerGallon.setMilesDriven(m);
            milesPerGallon.setGallonsUsed(g);

            double result = milesPerGallon.calculate();

            showText.setText(String.format("Miles per gallon: %.3f", result));
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }

}
