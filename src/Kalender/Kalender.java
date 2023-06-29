package Kalender;

import java.util.ArrayList;
import Kalender.KalenderElements.*;

public class Kalender{
    private ArrayList<KalenderElement> kalenderList = new ArrayList<KalenderElement>();

    public void addElement(KalenderElement e){
        kalenderList.add(e);
    }

}