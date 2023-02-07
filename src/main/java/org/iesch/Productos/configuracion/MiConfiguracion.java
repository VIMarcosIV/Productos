package org.iesch.Productos.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // Cutre
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//               // WebMvcConfigurer.super.addCorsMappings(registry);
//                registry.addMapping("/**");
//            }
//        };
//    }

    // Mejor
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               // WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/api/productoDTO/**")
                        .allowedOrigins("http://localhost:9999")
                        .allowedMethods("GET") // "POST", "DELETE", ...
                        .maxAge(3600);

            }
        };
    }


}
