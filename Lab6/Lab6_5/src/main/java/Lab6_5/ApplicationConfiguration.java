package Lab6_5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Value("${id}")
    private Integer id;
    @Value("${name}")
    private String name;
    @Value("${price}")
    private Double price;
    @Value("${description}")
    private String description;
    @Bean
    @Scope("prototype")
    public Product Product1(){
        return new Product(this.id,this.name,this.price,this.description);
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
