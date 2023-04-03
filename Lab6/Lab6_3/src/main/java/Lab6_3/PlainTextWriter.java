package Lab6_3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlainTextWriter implements TextWriter{
    @Override
    public void write(String fileName, String text) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.println(text);
        printWriter.close();
    }
}
