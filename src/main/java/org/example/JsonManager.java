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

    /**
     * Gets all the data fro the file_path.
     * @param file_path {String} ".\\Data\\dataname.json"
     * @return {ArrayList<JSONObject>} The data fron the json from filr_path
     * @throws IOException
     */
    public static ArrayList<JSONObject> readJson(String file_path) throws IOException{
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader (file_path)){
            Object obj = parser.parse(reader);
            JSONArray a = (JSONArray) obj;
            a.forEach( object -> {
                JSONObject test = (JSONObject) object;
                try{
                    value.add(test);
                    
                }catch(Exception e){
                    System.out.println(e);
                }

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


        return value;
    }

    /**
     * Deletes the old file and creates the new file with the new_content in it.
     * @param file_path {String} ".\\Data\\dataname.json"
     * @param new_content {String} "[{"attribute1":"1","attribute2":"Test1"},{"attribute1":"2","attribute2":"Test2"}]"
     * @throws IOException
     */
    public static void test(String file_path, String new_content) throws IOException{
        try {
            File file = new File(file_path);
            file.delete();
            if (file.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(file_path);
                    writer.write(new_content);
                    writer.close();
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
            } else {
              System.out.println("Something went wrong");
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
<<<<<<< HEAD
=======

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

>>>>>>> 03a3702d5344840ee5e60eaf0c1083e38b1ce899
}
