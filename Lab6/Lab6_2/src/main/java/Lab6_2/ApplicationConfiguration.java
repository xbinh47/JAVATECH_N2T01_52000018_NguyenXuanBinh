package Lab6_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Scope("prototype")
    public Product Product1(){
        return new Product(1,"Iphone 11 Pro Max",1000.0,"64GB");
    }

    @Bean
    @Scope("prototype")
    public Product Product2(){
        return new Product(Product1());
    }
    @Bean
    @Scope("singleton")
    public Product Product3(){
        return new Product(1,"Samsung S22 Ultra",2000.0,"64GB");
    }
}
