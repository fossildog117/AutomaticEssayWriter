//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EssayWrite {
    private static Scanner input;

    public static void main(String[] args) {
        System.out.print("Enter a topic: ");
        input = new Scanner(System.in);
        String var1 = input.nextLine();
        System.out.println(var1);

        try {
            Document var2 = Jsoup.connect("https://en.wikipedia.org/wiki/" + var1).get();
            Elements var3 = var2.getElementsByTag("p");
            System.out.println("Essay on " + var1);
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                Element var5 = (Element)var4.next();
                System.out.println(var5.text());
            }

            System.out.println("");
            System.out.println("References");
            Elements var9 = var2.select("span.reference-text");
            int var10 = 1;

            for(Iterator var6 = var9.iterator(); var6.hasNext(); ++var10) {
                Element var7 = (Element)var6.next();
                System.out.println(var10 + ". " + var7.text());
            }

            boolean var11 = false;
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}
