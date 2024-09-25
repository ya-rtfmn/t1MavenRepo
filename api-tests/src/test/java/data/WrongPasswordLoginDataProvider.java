package data;

import config.EnvConfig;

public class WrongPasswordLoginDataProvider implements LoginDataProvider {
    @Override
    public String getUsername() {
        return EnvConfig.getValidUsername();
    }

    @Override
    public String getPassword() {
        return "wrongPassword";
    }
}