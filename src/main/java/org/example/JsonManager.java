package org.example;

import org.example.Listen.Listentypen.Einkaufsitems;
import org.example.Listen.Listentypen.Listentyp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonManager {

    static ArrayList<JSONObject> value = new ArrayList<JSONObject>();
    static ArrayList<String> lines = new ArrayList<String>();
    static String line = null;

    public static ArrayList<JSONObject> readJson(String file_path, String table_name) throws IOException{
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader (file_path)){
            Object obj = parser.parse(reader);
            JSONArray a = (JSONArray) obj;
            a.forEach( object -> {
                JSONObject test = (JSONObject) object;
                try{
                    JSONObject data = (JSONObject) test.get(table_name);
                    switch(table_name){
                        case "shopping_list":
                            value.add(data);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            });
        }catch (Exception e){
            System.out.println("File not found");
        }

        return value;
    }

    // public static void parseJson(String file_path, String table_name, String data) throws IOException{
        
    //     JSONParser parser = new JSONParser();
    //     try(FileReader reader = new FileReader (file_path)){
    //         Object obj = parser.parse(reader);
    //         File file = new File(file_path);
    //         FileWriter filewriter = new FileWriter(file_path);
    //         String content = obj.toString();
    //         content.replace(content, data);
    //         filewriter.write(content);
    //     }
    //     catch(Exception e){
    //         System.out.println(e);
    //     }
    // }
    public static void doIt(String file_path, String old_content, String new_content) {
        try {
            File f1 = new File(file_path);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.println(old_content);
                if (line.contains(old_content))
                    line = line.replace(old_content, new_content);
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                 out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void test(String file_path, String old_content, String new_content) throws IOException{
        FileWriter write = new FileWriter(file_path);
        FileReader read = new FileReader(file_path);
        BufferedReader br = new BufferedReader(read);
        String to_change = br.readLine();
        write.append(new_content, to_change.indexOf(old_content),  to_change.indexOf(old_content)+old_content.length());
    }
    // public static void main(String[] args) {
    //     JsonManager jsonman = new JsonManager();
    //     jsonman.doIt();
    // }
}
