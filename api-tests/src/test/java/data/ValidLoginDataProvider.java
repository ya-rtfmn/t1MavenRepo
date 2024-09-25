package data;

import config.EnvConfig;

public class ValidLoginDataProvider implements LoginDataProvider {
    @Override
    public String getUsername() {
        return EnvConfig.getValidUsername();
    }

    @Override
    public String getPassword() {
        return EnvConfig.getValidPassword();
    }
}
