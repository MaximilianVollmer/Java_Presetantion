package Kalender.Stundenplan;

import Kalender.KalenderElements.KalenderElement;
import org.example.Generators.TimeGenerator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Vorlesung extends KalenderElement {

    private String dozent;

    private LocalDateTime endTime;
    public Vorlesung(String name, String dozent){
        date = generateTime();
        this.name = name;
        this.dozent = dozent;
    }
    public Vorlesung(String name){
        date = generateTime();
        super.name = name;
    }
    public Vorlesung(String name, LocalDateTime date){
        this.name = name;
        this.date = date;
    }

    public void length(int minutes){
        int hours = minutes/60;
        int endMinutes = minutes%60;

        //this.endTime = super.date;
        endTime = date.plusHours(hours).plusMinutes(endMinutes);
    }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - HH:mm");
        return this.date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN) + this.date.format(formatter);
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDozent(){
        return dozent;
    }

    public String getEndDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - HH:mm");
        return this.endTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN) + this.endTime.format(formatter);
    }

     public LocalDateTime generateTime(){

        return TimeGenerator.generateWeekdayTime();
    }

    private static final Map<String, DayOfWeek> germanDaysOfWeek =
            Arrays.stream(DayOfWeek.values()).collect(
                    Collectors.toMap(
                            d -> d.getDisplayName(TextStyle.FULL, Locale.GERMAN), d -> d));
}
