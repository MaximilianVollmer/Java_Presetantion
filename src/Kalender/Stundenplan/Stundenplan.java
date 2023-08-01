package Kalender.Stundenplan;

import Kalender.Kalender;
import Kalender.KalenderElements.KalenderElement;


import java.util.ArrayList;
import java.util.Date;

public class Stundenplan extends Kalender {
    private ArrayList<KalenderElement> vorlesungen = new ArrayList<KalenderElement>();

    public KalenderElement newLesson(String fach, Date date){

        return null;
    }

import java.util.*;

public class Stundenplan extends Kalender {

    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private ArrayList<KalenderElement> vorlesungen = new ArrayList<KalenderElement>();


    public void newLesson(){

        Vorlesung lesson;
        try{
            System.out.println("Wie heißt die Vorlesung?");
            String inputName = scanner.nextLine();
            System.out.println("Wie lang geht die Vorlesung? (in Minuten)");
            int length = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Hat die Vorlesung einen Dozenten? (Y/N)");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Wie heißt der Dozent?");
                String inputDozent = scanner.nextLine();
                lesson = new Vorlesung(inputName, inputDozent);
            } else {
                lesson = new Vorlesung(inputName);
            }

            vorlesungen.add(lesson);
            lesson.length(length);
        }
        catch(InputMismatchException e){
            System.out.println("Ungültige Eingabe");
            newLesson();
        }
    }



    public Vorlesung getVorlesung(int index){
        return (Vorlesung) vorlesungen.get(index);
    }

    public static void main(String[] args){
        Stundenplan stundenplan = new Stundenplan();
        stundenplan.newLesson();
        System.out.println(stundenplan.getVorlesung(0).getDate());
        System.out.println(stundenplan.getVorlesung(0).getEndDate());
        System.out.println(stundenplan.getVorlesung(0).getName());
    }


}
