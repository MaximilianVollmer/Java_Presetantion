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
            case "monday" -> DayOfWeek.MONDAY;
            case "tuesday" -> DayOfWeek.TUESDAY;
            case "wednesday" -> DayOfWeek.WEDNESDAY;
            case "thursday" -> DayOfWeek.THURSDAY;
            case "friday" -> DayOfWeek.FRIDAY;
            case "saturday" -> DayOfWeek.SATURDAY;
            case "sunday" -> DayOfWeek.SUNDAY;
            default -> throw new StackOverflowError("Invalid weekday!");
        };
    }

    private static double doubleValidator(double time) throws Exception {

        int hour = (int)time;
        int minute = ((int)Math.round(time*100)) % 100;

        if( (hour <= 23 && hour >= 0) && (minute <= 59 && minute >= 0) ){
            return (Math.floor(time*100))/100;
        }

        throw new Exception("Invalid time!");
    }

    public static LocalDateTime GenerateDate(){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {
            System.out.println("What weekday?");
            DayOfWeek weekday = weekdayToEnum(scanner.nextLine());

            System.out.println("What time?");
            double time = TimeGenerator.doubleValidator(scanner.nextDouble());

            LocalDate date = LocalDate.now();
            return LocalDateTime.of(date.with(weekday).plusWeeks(1),LocalTime.of((int)time,((int)Math.round(time*100)) % 100));
        }
        catch(StackOverflowError | Exception e){
            System.out.println(e.getMessage());
            return GenerateDate();
        }
    }

    public static void main(String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime date = GenerateDate();

        System.out.print(date.getDayOfWeek()+" ");
        System.out.println(date.format(formatter));
    }
}
