package Kalender.KalenderElements;

import org.example.Generators.TimeGenerator;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class KalenderElement{

    protected LocalDateTime date;
    protected String name;

    public abstract LocalDateTime generateTime();

}