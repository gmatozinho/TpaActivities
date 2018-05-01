package MainTest;

import DocumentLib.Document;

import java.io.IOException;

public class DocumentTest {
    public static void main(String[] args) throws IOException {
        Document document = Document.create("document.txt",false);
        Document document2 = Document.create("document.txt",true);
        document.makeTabFreq("portuguesestopwords.txt");
        document2.makeTabFreq("portuguesestopwords.txt");
        document.save("tabfreq.txt");
        document2.save("tabfreq2.txt");
    }
}
