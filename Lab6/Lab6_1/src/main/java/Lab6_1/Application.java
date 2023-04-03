package Lab6_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");
        Product product = (Product) context.getBean("product");
        Product product1 = (Product) context.getBean("product1");
        Product product2 = (Product) context.getBean("product2");
        System.out.println(product.getName());
        System.out.println(product1.getName());
        System.out.println(product2.getName());


    }
}
