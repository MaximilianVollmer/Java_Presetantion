package org.example.Generators;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.*;

import static java.time.temporal.ChronoField.*;

public class TimeGenerator {

    /* TimeGenerators generateDate() Method can be called statically
    to return a LocalDateTime Object based on the user Input
    This is used for the "Stundenplan" Class to get a time for a lesson */

    private static DayOfWeek weekdayToEnum(String weekday){

        return switch (weekday.toLowerCase()) {
            case "montag" -> DayOfWeek.MONDAY;
            case "dienstag" -> DayOfWeek.TUESDAY;
            case "mittwoch" -> DayOfWeek.WEDNESDAY;
            case "donnerstag" -> DayOfWeek.THURSDAY;
            case "freitag" -> DayOfWeek.FRIDAY;
            case "samstag" -> DayOfWeek.SATURDAY;
            case "sonntag" -> DayOfWeek.SUNDAY;
            default -> throw new StackOverflowError("Kein Wochentag!");
        };
    }

    private static DateTimeFormatter formatterProvider(){
        return new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(".")
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral(".")
                .appendValue(YEAR, 4)
                .appendLiteral(" ")
                .optionalStart()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(":")
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalEnd()
                .optionalStart()
                .appendValue(HOUR_OF_DAY,2 )
                .appendLiteral(".")
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalEnd()
                .toFormatter();
    }

    private static double doubleValidator(double time) throws Exception {

        int hour = (int)time;
        int minute = ((int)Math.round(time*100)) % 100;
        double twoDigitTime = (Math.floor(time*100))/100;

        if( (hour <= 23 && hour >= 0) && (minute <= 59 && minute >= 0) ){
            return twoDigitTime;
        }

        throw new Exception("Keine zulässige Zeit!");
    }

    private static String validateDate(String input) throws Exception {
        if(input.matches("\\d{2}.\\d{2}.\\d{4}")){
           return input;
        }
        else{
            throw new Exception("Kein gültiges Datum!");
        }
    }

    private static String validateTime(String input) throws Exception {
        if(input.matches("\\d{2}.\\d{2}") || input.matches("\\d{2}:\\d{2}")){
            return input;
        }
        else{
            throw new Exception("Keine gültige Zeit!");
        }
    }


    public static LocalDateTime generateWeekdayTime(){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);


            DayOfWeek weekday = inputWeekday(scanner);
            double time = inputTime(scanner);

            LocalDate date = LocalDate.now();

            boolean weekdayBeforeDate = date.with(weekday).isBefore(LocalDate.now());

            return LocalDateTime.of(!weekdayBeforeDate ? date.with(weekday) : date.with(weekday).plusWeeks(1) ,LocalTime.of((int)time,((int)Math.round(time*100)) % 100));

    }

    public static LocalDateTime generateDateTime(){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        DateTimeFormatter newFormatter = formatterProvider();

            String inputDate = inputDate(scanner);
            String inputTime = inputTimeString();

            return LocalDateTime.parse(inputDate + " " + inputTime, newFormatter);
    }


    public static LocalDateTime generateDateTime(String date, String time){

        //Use this to generate custom Input Prompts

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        DateTimeFormatter newFormatter = formatterProvider();


            String inputDate = inputDate(scanner,date);
            String inputTime = String.valueOf(inputTime(scanner, time));

            return LocalDateTime.parse(inputDate + " " + inputTime, newFormatter);

    }

    private static double inputTime(Scanner scanner){
        try {
            System.out.println("Um welche Uhrzeit? (hh.mm)");
            return TimeGenerator.doubleValidator(scanner.nextDouble());
        }
        catch(Exception e){
            System.out.println("Keine zulässige Zeit!");
            scanner.nextLine();
            return inputTime(scanner);
        }
    }

    private static String inputTimeString(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Um welche Uhrzeit? (hh.mm)");
            return TimeGenerator.validateTime(scanner.nextLine());
        }
        catch(Exception e){
            System.out.println("Keine zulässige Zeit!");
            return inputTimeString();
        }
    }
    private static double inputTime(Scanner scanner, String input){
        try {
            System.out.println(input);
            return TimeGenerator.doubleValidator(scanner.nextDouble());
        }
        catch(Exception e){
            System.out.println("Keine zulässige Zeit!");
            scanner.nextLine();
            return inputTime(scanner, input);
        }
    }

    private static String inputDate(Scanner scanner){
        try {
            System.out.println("An welchem Datum? (dd.mm.yyyy)");
            return validateDate(scanner.nextLine());

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return inputDate(scanner);
        }
    }
    private static String inputDate(Scanner scanner, String input){
        try {
            System.out.println(input);
            return validateDate(scanner.nextLine());

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return inputDate(scanner);
        }
    }




    private static DayOfWeek inputWeekday(Scanner scanner){
        try{
            System.out.println("An welchem Wochentag?");
            DayOfWeek weekday = weekdayToEnum(scanner.nextLine());
            System.out.println(weekday);
            return weekday;
        }
        catch(StackOverflowError e){
            System.out.println(e.getMessage());
            return inputWeekday(scanner);
        }
    }


 


    public static void main(String[] args){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        LocalDateTime date = generateDateTime();

        System.out.println(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN)+ " "+date.format(formatter));

    }
}
