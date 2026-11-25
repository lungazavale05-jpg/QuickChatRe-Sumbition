package chatapp;

public class Login {
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean length = password.length() >= 8;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        return length && hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        String regex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber.matches(regex);
    }

    public boolean loginUser(String username, String password, String newUsername, String newPassword) {
        return username.equals(newUsername) && password.equals(newPassword);
    }
}
