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
                .optionalStart()
                .appendPattern("EEEE")
                .appendLiteral(" ")
                .optionalEnd()
                .optionalStart()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(".")
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral(".")
                .appendValue(YEAR, 4)
                .appendLiteral(" ")
                .optionalEnd()
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

        DateTimeFormatter formatter = formatterProvider();

            DayOfWeek weekday = inputWeekday(scanner);
            String time = inputTimeString();

            LocalDate date = LocalDate.now();

             date = date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()) ? date.with(weekday).plusWeeks(1) : date.with(weekday);


            return LocalDateTime.parse(date.format(formatter)+time, formatter);


    }

    public static LocalDateTime generateDateTime(){

        DateTimeFormatter newFormatter = formatterProvider();

            String inputDate = inputDate();
            String inputTime = inputTimeString();

            return LocalDateTime.parse(inputDate + " " + inputTime, newFormatter);
    }

    private static String inputTimeString(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Um welche Uhrzeit? (hh:mm)");
            return TimeGenerator.validateTime(scanner.nextLine());
        }
        catch(Exception e){
            System.out.println("Keine zulässige Zeit!");
            return inputTimeString();
        }
    }

    private static String inputDate(){

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("An welchem Datum? (dd.mm.yyyy)");
            return validateDate(scanner.nextLine());

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return inputDate();
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

}
