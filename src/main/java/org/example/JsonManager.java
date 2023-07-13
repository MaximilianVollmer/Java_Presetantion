package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonManager {

    public void readJson() throws IOException{
        //File f = new File(".\\test\\test.json");
        JSONParser parser = new JSONParser();
        //System.out.println(f.canRead());
        try(FileReader reader = new FileReader (".\\test\\test.json")){
            Object obj = parser.parse(reader);
            JSONArray a = (JSONArray) obj;
            System.out.println(a);
            AtomicInteger i = new AtomicInteger();
            a.forEach(object -> {
                JSONObject test = (JSONObject) object;
                String tableName = "table";
                try{
                    JSONObject table = (JSONObject) test.get(tableName);
                    String name = (String) table.get("value");
                    i.addAndGet(Integer.parseInt(name));
                    System.out.println(name);
                }catch(Exception e){}
            });
            System.out.println(i.get());
        }catch (Exception e){
            System.out.println("File not found");
        }
    }
}
