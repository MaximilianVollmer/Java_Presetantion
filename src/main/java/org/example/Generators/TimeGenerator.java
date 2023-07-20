package org.example.Generators;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
        DateTimeFormatter newFormatter = new DateTimeFormatterBuilder()
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


        try {
            System.out.println("An welchem Datum? (dd.mm.yyyy)");
            String inputDate = scanner.nextLine();

            System.out.println("Welche Uhrzeit? (hh:mm)");
            String inputTime = scanner.nextLine();

            return LocalDateTime.parse(inputDate + " " + inputTime, newFormatter);
        }
        catch(StackOverflowError | Exception e){
            System.out.println(e.getMessage());
            return generateDateTime();
        }
    }

    public static void main(String[] args){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy hh:mm");

        System.out.println(generateDateTime().get(HOUR_OF_DAY));

    }
}
