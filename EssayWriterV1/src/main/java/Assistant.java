import java.util.*;
import java.util.logging.Filter;

/**
 * Created by nathanliu on 16/07/2016.
 */
abstract class Assistant {

    public static final String Line = "--------------------------------------";

    public static LinkedList<String> NameList = new LinkedList<String>();
    public static LinkedList<String> SubjectList = new LinkedList<String>();

    private static String[] dictionary = {"DEPARTMENT", "SUBJECTS", "FACULTY",
            "FACULTIES", "DEPARTMENTS", "SUBJECT", "INSTITUTE", "INSTITUTES"};

    public static String[] StaffSemantics = {"PEOPLE", "STAFF"};

    public static boolean IsName(String line) {
        ArrayList<String> nameFields = new ArrayList<>(GetWords(line));

        for (String part : nameFields) {
            // System.out.println(part + ":" + Collections.binarySearch(NameList, part.toUpperCase()));
            // if greater than 1 return true
            if (Collections.binarySearch(NameList, part.toUpperCase()) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsEmail(String line) {
        if (line.substring(0, 6).equals("mailto")) {
            return true;
        }
        return false;
    }

    public static boolean IsDepartmentsLink(String line) {
        ArrayList<String> fields = new ArrayList<>(GetWords(line));

        for (String field: fields) {
            field = field.toUpperCase();
            for (String word : dictionary) {
                if (word.equals(field)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Gets individual words of a line
    static ArrayList<String> GetWords(String line) {

        ArrayList<String> fields = new ArrayList<>();
        String temp = "";

        for (int  i = 0 ; i < line.length(); i++) {

            temp += line.charAt(i);

            if (line.charAt(i) == ' ' || i == line.length() - 1) {
                fields.add(temp);
                if (i == line.length() - 1) {
                    break;
                }
                temp = "";
            }
        }
        return fields;
    }

    // Removes brackets from a line
    public static String FilterBrackets(String line) {
        String retString = "";
        boolean withinBracket = false;
        int numberOfLeftBrackets = 0;
        int numberOfRightBrackets = 0;

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == '(') {
                withinBracket = true;
                numberOfLeftBrackets++;
            }

            if (line.charAt(i) == ')') {
                numberOfRightBrackets++;
                if (numberOfLeftBrackets - numberOfRightBrackets == 0) {
                    // because or ')' and ' '
                    i = i + 2;
                    withinBracket = false;
                    if (i > line.length() - 1) {
                        break;
                    }
                }
            }

            if (!withinBracket) {
                retString = retString + line.charAt(i);
            }
        }
        return retString;
    }

    // Breaks a line into a triangle
    public static ArrayList<String> Breaker(String line) {

        ArrayList<String> fields = new ArrayList<>();
        fields.add(line);

        int end = line.length();

        for (int i = 0; i < end; i++) {
            if (line.charAt(0) == ' ') {
                fields.add(line.substring(1));
            }
            line = line.substring(1);
        }

        return fields;

    }

    // generates a random number between x1 and x2
    public static int RandomInteger(int min, int max) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt((max - min) + 1) + min;
    }

    public static String FilterCharacters(String line) {
        String[] rchars = {";", ":", ".", ",", " "};
        for (String character : rchars) {
            line = line.replace(character, "");
        }
        return line;
    }

    public static void print(String x) {
        System.out.println(x);
    }
}

