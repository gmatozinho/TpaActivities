package MainTest;

import DocumentLib.Document;

import java.io.IOException;

public class DocumentTest {
    public static void main(String[] args) throws IOException {
        Document document = Document.create("document.txt");
        document.makeTabFreq("portuguesestopwords.txt");
        document.save("tabfreq.txt");
    }
}
