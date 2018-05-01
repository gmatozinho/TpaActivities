package MainTest;

import TranslationDictionary.TranslationBrEn;
import TranslationDictionary.TranslationEnPt;

import java.io.IOException;

public class TranslationTest {
    public static void main(String[] args) throws IOException {
        TranslationEnPt translationEnPt = TranslationEnPt.create();
        System.out.printf(translationEnPt.Translate("you")+"\n");
        TranslationBrEn translationBrEn = TranslationBrEn.create();
        System.out.printf(translationBrEn.Translate("as"));
    }

}
