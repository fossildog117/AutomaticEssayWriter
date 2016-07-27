
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.sql.*;
import java.util.ArrayList;

public class EssayWriterV1 {

    private static ArrayList<String> words;

    public static void print(String printString) {
        System.out.println(printString);
    }

    public static void main(String args[]) throws  SQLException {

        EssayWrite myEssay = new EssayWrite("Rome");
        myEssay.ParseEssay();
        myEssay.printEssay();

    }



}