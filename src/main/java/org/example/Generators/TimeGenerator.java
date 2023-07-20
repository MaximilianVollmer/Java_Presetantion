package org.example.Generators;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private static double doubleValidator(double time) throws Exception {

        int hour = (int)time;
        int minute = ((int)Math.round(time*100)) % 100;
        double twoDigitTime = (Math.floor(time*100))/100;

        if( (hour <= 23 && hour >= 0) && (minute <= 59 && minute >= 0) ){
            return twoDigitTime;
        }

        throw new Exception("Keine zulässige Zeit!");
    }

    public static LocalDateTime generateWeekdayTime(){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {
            System.out.println("An welchem Wochentag?");
            DayOfWeek weekday = weekdayToEnum(scanner.nextLine());

            System.out.println("Um welche Uhrzeit? (HH.mm)");
            double time = TimeGenerator.doubleValidator(scanner.nextDouble());

            LocalDate date = LocalDate.now();

            boolean weekdayBeforeDate = date.with(weekday).isBefore(LocalDate.now());

            return LocalDateTime.of(!weekdayBeforeDate ? date.with(weekday) : date.with(weekday).plusWeeks(1) ,LocalTime.of((int)time,((int)Math.round(time*100)) % 100));
        }
        catch(StackOverflowError | Exception e){
            System.out.println(e.getMessage());
            return generateWeekdayTime();
        }
    }

    public static LocalDateTime generateDateTime(){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

        try {
            System.out.println("An welchem Datum?");

            LocalDateTime date = LocalDateTime.parse(scanner.nextLine() + " 00:00", formatter);

            return date;
        }
        catch(StackOverflowError | Exception e){
            System.out.println(e.getMessage());
            return generateDateTime();
        }
    }

    public static void main(String[] args){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        System.out.println(generateDateTime().format(formatter));
    }
}
