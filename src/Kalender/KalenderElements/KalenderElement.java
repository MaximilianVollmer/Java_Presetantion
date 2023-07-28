package Kalender.KalenderElements;

import org.example.Generators.TimeGenerator;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class KalenderElement{

    protected LocalDateTime date;
    protected String name;

    public abstract LocalDateTime generateTime();

    public abstract String getName();


    public abstract String getDozent();

    public abstract String getDate();
}
