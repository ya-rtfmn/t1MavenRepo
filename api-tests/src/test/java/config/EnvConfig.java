package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:config.properties"})
public interface EnvConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("valid.username")
    String getValidUsername();

    @Key("valid.password")
    String getValidPassword();

    @Key("invalid.username")
    String getInvalidUsername();

    @Key("invalid.password")
    String getInvalidPassword();

    EnvConfig cfg = ConfigFactory.create(EnvConfig.class);
}
