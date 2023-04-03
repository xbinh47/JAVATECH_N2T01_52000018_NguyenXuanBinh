package Lab6_3;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public interface TextWriter {
    public void write(String fileName, String text) throws IOException;
}
