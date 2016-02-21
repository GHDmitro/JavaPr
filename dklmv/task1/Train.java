package dklmv.task1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import java.sql.Time;
//import java.text.DateFormat;
//import java.time.LocalDate;
//import java.util.Calendar;

//import java.sql.Time;
//import java.util.Date;

/**
 * Created by macbookair on 17.02.16.
 */
@XmlRootElement(name = "train")
public class Train {

    private boolean flug = true;
    private int id;
    private String from;
    private String to;
    private String date;
    private String time;

    public Train() {
    }

    //    , Time time
    public Train(String from, String to, String date, String time) {

        this.from = from;
        this.to = to;

        this.date = date;
        this.time = time;
//        this.time = time.toString();


    }

    @XmlAttribute
    public void setId(int id) {
        if (flug) {
            this.id = id;
        } else {
            flug = false;
            throw new IllegalAccessError("Id has already set;");
        }
    }

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "departure")
    public void setTime(String time) {

        this.time = time;
    }

    //    }
//        this.time = time.toString();
//    public void setTime(Time time) {
//    @XmlElement(name = "departure")

    public String getTime() {
        return time;
    }


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    //    @XmlJavaTypeAdapter(value = java.sql.Date.class)
    public String getDate() {
        return date;
    }

//    public Time getTime() {
//        return Time.valueOf(time);
//    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "From : " + getFrom() + ";\nTo :" + getTo() + ";\nDate :" + getDate() + ";\nDeparture :" + getTime() + ";";
    }
}















