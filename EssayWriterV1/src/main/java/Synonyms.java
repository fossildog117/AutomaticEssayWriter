import com.sun.tools.javadoc.Start;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Synonyms {

    private static final String priorUrl = "http://www.thesaurus.com/browse/";
    private static final String endingKey = "<exit>";
    private static final String welcomeMessage = "Enter the word to search or type ".concat(endingKey).concat(" to finalize the program: ");
    private static final String iOExceptionMessage = "Impossible to read.";
    private static final String noSuchElementExceptionMessage = "Word not found.";

    private static final WebDriver driver = new HtmlUnitDriver();

    public static String getSynonym(String word) {

        char possibleStartCharacter = StartGrammarCharacter(word);
        char possibleEndCharacter = EndGrammarCharacter(word);

        System.out.println(possibleStartCharacter + " : " + possibleEndCharacter);

        String retWord = word;

        try {
            driver.get(priorUrl.concat(word));
            WebElement description = driver.findElement(By.className("synonym-description"));
            if (description != null) {
                // Get the type of word e.g. verb, adjective, noun, etc.
                WebElement des = description.findElement(By.tagName("em"));
                System.out.printf("%s", des.getText());
                // if word is adjective or noun
                if (des.getText().equals("adj") || des.getText().equals("noun")) {
                    // Get list of synonyms
                    WebElement list = driver.findElement(By.className("relevancy-list"));
                    if (list != null) {
                        List<WebElement> spans = list.findElements(By.tagName("span"));
                        // Create array of synonyms
                        ArrayList<String> synonymList = new ArrayList<String>();
                        for (WebElement span : spans) {
                            if (span.getAttribute("class").equals("text")) {
//                                System.out.printf("%s  ", span.getText());
                                synonymList.add(span.getText());
                            }
                        }
                        // Return random synonym in the list
                        Random randomGenerator = new Random();
                        int randomInt = randomGenerator.nextInt(synonymList.size());
                        retWord = Parse(synonymList.get(randomInt), possibleStartCharacter, possibleEndCharacter);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(iOExceptionMessage);
            System.out.println("--------------------");
        } catch (NoSuchWindowException e) {
            System.out.println(noSuchElementExceptionMessage);
            System.out.println("--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return retWord;
        }
    }

    private static String Parse(String line, char start, char end) {
        if (start != '\0') {
            line = start + line;
        }

        if (end != '\0') {
            line = line + end;
        }

        return line;
    }

    private static char EndGrammarCharacter(String line) {
        char[] characters = {'.', ',', ';', ':', '!', '?'};
        for (int i = 0; i < characters.length; i++) {
            if (line.charAt(line.length() - 1) == characters[i]) {
                return characters[i];
            }
        }
        return '\0';
    }

    private static char StartGrammarCharacter(String line) {
        char[] characters = {'£', '€', '$'};
        for (int i = 0; i < characters.length; i++) {
            if (line.charAt(0) == characters[i]) {
                return characters[i];
            }
        }
        return '\0';
    }
}


