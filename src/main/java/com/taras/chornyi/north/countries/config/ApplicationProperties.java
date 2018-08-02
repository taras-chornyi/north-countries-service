package com.taras.chornyi.north.countries.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application properties
 *
 * @author Taras Chornyi
 */
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {

    private String vigilanteApi;

    private Swagger swagger = new Swagger();

    @Data
    public class Swagger {
        private String basePackage;
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }

}
