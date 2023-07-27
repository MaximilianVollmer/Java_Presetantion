package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonManager {

    public void readJson() throws IOException{
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader (".\\Data\\DATA.json")){
            Object obj = parser.parse(reader);
            JSONArray a = (JSONArray) obj;
            System.out.println(a);
            a.forEach(object -> {
                JSONObject test = (JSONObject) object;
                String tableName = "table";
                try{
                    JSONObject table = (JSONObject) test.get(tableName);
                    String name = (String) table.get("value");
                    System.out.println(name);
                }catch(Exception e){}
            });
        }catch (Exception e){
            System.out.println("File not found");
        }
    }
}
