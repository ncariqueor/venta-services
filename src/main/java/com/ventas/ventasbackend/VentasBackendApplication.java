package com.ventas.ventasbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:/opt/tomcat/properties/ventas.properties")
public class VentasBackendApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VentasBackendApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(VentasBackendApplication.class, args);
    }
}
