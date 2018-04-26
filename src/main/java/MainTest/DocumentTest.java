package MainTest;

import DocumentLib.Document;
import HashLib.Core.MyHash;

import java.io.IOException;

public class DocumentTest {
    public static void main(String[] args) throws IOException {
        Document document = Document.create("document.txt",false);
        Document document2 = Document.create("document.txt",true);
        document.makeTabFreq("portuguesestopwords.txt");
        document2.makeTabFreq("portuguesestopwords.txt");
        MyHash v = document2.getTabFreq();
        System.out.println(document2.getTabFreq().findElements("toronto"));
        document.save("tabfreq.txt");
        document2.save("tabfreq2.txt");
    }
}
