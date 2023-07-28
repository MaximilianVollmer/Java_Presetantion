package Kalender.Stundenplan;

import Kalender.Kalender;
import Kalender.KalenderElements.KalenderElement;

import java.time.LocalDateTime;
import java.util.*;

public class Stundenplan extends Kalender {

    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private ArrayList<KalenderElement> vorlesungen = new ArrayList<>();


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

    private void createTestSample(){
        for(int i = 0; i < 5; i++) {
            vorlesungen.add(new Vorlesung(Integer.toString(i), LocalDateTime.now()));
        }
    }



    public Vorlesung getVorlesung(int index){
        return (Vorlesung) vorlesungen.get(index);
    }

    public void printVorlesung(int index){
            System.out.println("\n"+index);
            System.out.println(vorlesungen.get(index).getName());
            System.out.println(vorlesungen.get(index).getDozent());
            System.out.println(vorlesungen.get(index).getDate()+"\n");
    }

    private void elementCommands(int index){
        commandloop:
        while(true) {
            System.out.println("[0]Zurück\n[1]Anzeigen\n[2]Bearbeiten\n[3]Löschen");
            switch(scanner.nextLine()){
                case "0" -> {break commandloop;}
                case "1" -> printVorlesung(index);
                case "2" -> System.out.println("WIP"); //TODO: Method in Vorlesung to keep name and edit other parameters or select which parameters to edit
                case "3" -> {
                    vorlesungen.remove(index);
                    break commandloop;
                }
            }
        }

    }

    public void showVorlesungen(){
        String input = "";
        commandloop:
        while(true){
            System.out.println("\n[0]Zurück");
            vorlesungen.forEach(v -> {
                System.out.println("["+(vorlesungen.indexOf(v)+1)+"]"+v.getName());
            });
            input = scanner.nextLine();
        }
    }

    private void allCommands(){
        System.out.println("[0]Zurück\n[1]Neue Vorlesung\n[2]Zeige Vorlesungen");
    }

    @Override
    public void commandLoop(){
        System.out.println("Stundenplan Konsole");
        outerloop:
        while(true){
            System.out.println("Was willst du im Stundenplan machen?");
            allCommands();
            switch(scanner.nextLine()){
                case "0" -> {break outerloop;}
                case "1" -> newLesson();
                case "2" -> showVorlesungen();
                case "3" -> createTestSample();
                default -> System.out.println("Kein gültiger Befehl!");

            }
        }
    }

}
