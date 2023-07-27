package org.example;

import org.example.Generators.TimeGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!, Hi Hasan");
        LocalDateTime date = TimeGenerator.generateDateTime();

    }
}