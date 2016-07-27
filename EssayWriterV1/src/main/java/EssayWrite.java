//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EssayWrite {

    private ArrayList<String> EssayBody;
    private ArrayList<String> References;

    private void writeEssay(String topic) {

        try {
            Document var2 = Jsoup.connect("https://en.wikipedia.org/wiki/" + topic).get();
            Elements var3 = var2.getElementsByTag("p");
            System.out.println("Essay on " + topic);
            Iterator var4 = var3.iterator();

            while (var4.hasNext()) {
                Element var5 = (Element) var4.next();
                EssayBody.add(var5.text());
            }

            Elements var9 = var2.select("span.reference-text");
            int var10 = 1;

            for (Iterator var6 = var9.iterator(); var6.hasNext(); ++var10) {
                Element var7 = (Element) var6.next();
                References.add(var10 + ". " + var7.text());
            }

        } catch (IOException var8) {
            var8.printStackTrace();
        }
    }

    public void printEssay() {
        for (String aEssayBody : EssayBody) {
            System.out.println(aEssayBody);
        }
        for (String reference : References) {
            System.out.println(reference);
        }
    }

    public EssayWrite(String topic) {
        this.EssayBody = new ArrayList<String>();
        this.References = new ArrayList<String>();
        writeEssay(topic);
    }

    public void ParseEssay() {

        for (int index = 0; index < EssayBody.size(); index++) {

            ArrayList<String> words = new ArrayList<>(Assistant.GetWords(Assistant.FilterBrackets(EssayBody.get(index))));

            for (int i = 0; i < words.size() / 2; i++) {

                int randomNumber = Assistant.RandomInteger(0, words.size() - 1);

                try {
                    words.set(randomNumber, Synonyms.getSynonym(words.get(randomNumber)));
                } catch (Exception e) {
                    System.out.println("Nothing found for " + words.get(randomNumber));
                }
            }

            EssayBody.set(index, formLine(words));
            words.clear();

        }
    }

    private String formLine(ArrayList<String> words) {
        String line = "";
        for (int i = 0; i < words.size(); i++) {
            line += words.get(i);
        }
        return line;
    }

    public ArrayList<String> getEssayBody() {
        return EssayBody;
    }

    public ArrayList<String> getReferences() {
        return References;
    }
}
