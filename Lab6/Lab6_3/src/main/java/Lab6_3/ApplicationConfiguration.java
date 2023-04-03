package Lab6_3;

import org.springframework.context.annotation.Bean;

public class ApplicationConfiguration {
    @Bean
    public TextWriter plainTextWriter(){
        return new PlainTextWriter();
    }
    @Bean
    public TextWriter pdfTextWriter(){
        return new PdfTextWriter();
    }
    @Bean
    public TextEditor textEditor(){
        return new TextEditor();
    }
}
