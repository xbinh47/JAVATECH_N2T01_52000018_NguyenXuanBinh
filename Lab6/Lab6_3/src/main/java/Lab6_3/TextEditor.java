package Lab6_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class TextEditor {
    private String text;
    @Autowired
    @Qualifier("plainTextWriter")
    private TextWriter writer;
    public void input(String text){
        this.text = text;
    }
    public void save(String fileName) throws IOException{
        writer.write(fileName,this.text);
    }

}
