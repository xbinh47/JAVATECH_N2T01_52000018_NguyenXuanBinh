package Lab6_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Product product1 = (Product) context.getBean("Product1");
        Product product2 = (Product) context.getBean("Product2");
        Product product3 = (Product) context.getBean("Product3");
        System.out.println(product1.getName());
        System.out.println(product2.getName());
        System.out.println(product3.getName());
    }

}
