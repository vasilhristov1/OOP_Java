package hw8.problem1;

public class ValidateInput {
    public static boolean validateUsername(String username) {
        return username.matches("[a-zA-Z]{2,}");
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("[(][1-9]{4}[)] [(][1-9]{7}[)]");
    }

    public static boolean validateEmailAddress(String email) {
        return email.matches("^(.+)@(.+)$");
    }

    public static boolean validatePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
