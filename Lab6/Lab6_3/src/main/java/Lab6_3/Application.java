package Lab6_3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Application
{
    public static void main( String[] args )
    {
            ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
            TextEditor textEditor = (TextEditor) context.getBean("textEditor");
            textEditor.input("Spring is comming");
            try{
                textEditor.save("spring1.txt");
            }catch (IOException e){
                System.out.println(e);
            }

    }
}
