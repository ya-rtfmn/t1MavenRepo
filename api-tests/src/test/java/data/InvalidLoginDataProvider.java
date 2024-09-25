package data;

public class InvalidLoginDataProvider implements LoginDataProvider {
    @Override
    public String getUsername() {
        return "invalidUser";
    }

    @Override
    public String getPassword() {
        return "invalidPassword";
    }
}
