package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {

    private static final Dotenv dotenv = Dotenv.load();

    public static String getValidUsername() {
        return dotenv.get("VALID_USERNAME");
    }

    public static String getValidPassword() {
        return dotenv.get("VALID_PASSWORD");
    }
}