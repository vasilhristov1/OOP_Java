package hw8.problem1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class JFXSceneController {
    @FXML
    TextField userNameId;
    @FXML
    TextField phoneNumberId;
    @FXML
    TextField emailId;
    @FXML
    TextField passwordId;
    @FXML
    TextField cPasswordId;
    @FXML
    Text showMsg;
    @FXML
    Label usernameL;
    @FXML
    Label phoneNumberL;
    @FXML
    Label emailL;
    @FXML
    Label passwordL;
    @FXML
    Label cPasswordL;

    @FXML
    protected void onRegistrationButtonClick() {
        boolean usernameCheck = ValidateInput.validateUsername(userNameId.getText());
        boolean phoneNumberCheck = ValidateInput.validatePhoneNumber(phoneNumberId.getText());
        boolean emailCheck = ValidateInput.validateEmailAddress(emailId.getText());
        boolean confirmPasswordCheck = ValidateInput.validatePassword(passwordId.getText(), cPasswordId.getText());

        if (usernameCheck && phoneNumberCheck &&
        emailCheck && confirmPasswordCheck) {
            showMsg.setText("Successful registration!");
        } else {
            showMsg.setText("Invalid registration! Please, try again.");
        }

        if (!usernameCheck){
            usernameL.setText("Invalid field!");
        } else {
            usernameL.setText("");
        }

        if (!phoneNumberCheck) {
            phoneNumberL.setText("Invalid field!");
        } else {
            phoneNumberL.setText("");
        }

        if (!emailCheck) {
            emailL.setText("Invalid field!");
        } else {
            emailL.setText("");
        }

        if (passwordId.getText() == null) {
            passwordL.setText("Invalid Field!");
        } else {
            passwordL.setText("");
        }

        if (!confirmPasswordCheck) {
            cPasswordL.setText("Invalid field!");
        } else {
            cPasswordL.setText("");
        }
    }

}
