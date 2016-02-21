package lesson2.newPac;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.webkit.dom.NodeListImpl;
import jdk.nashorn.internal.parser.JSONParser;
import lesson2.JSON;
import org.w3c.dom.NodeList;
import sun.reflect.generics.tree.ArrayTypeSignature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by macbookair on 16.02.16.
 */
public class Parser {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/macbookair/IdeaProjects/JavaPro/src/lesson2/json.txt"));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }


        Gson gson = new GsonBuilder().create();
        Visitca json = (Visitca) gson.fromJson(sb.toString(), Visitca.class);


        System.out.println(json.name + "\n" + json.surname + "\n" + Arrays.toString(json.phones) + "\n" + Arrays.toString(json.sites) + "\n");
    }

}
