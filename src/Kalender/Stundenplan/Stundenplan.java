package Kalender.Stundenplan;

import Kalender.Kalender;
import Kalender.KalenderElements.KalenderElement;

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

    public void commandLoop(){
        System.out.println("Stundenplan Konsole");
        Scanner scanner = new Scanner(System.in);
        String input ="";
        while(!input.equals("exit")){
            System.out.println("Was willst du im Stundenplan machen?");
            input = scanner.nextLine();
            switch(input){
                case "neu" -> newLesson();

            }
        }
    }

}
