package MainTest;

import DocumentLib.Document;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Document document = Document.create("document.txt");

        System.out.println(document.getWordsList());

    }


}