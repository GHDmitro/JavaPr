package dklmv.task2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by macbookair on 18.02.16.
 */
@XmlRootElement(name = "rate")
public class Rate {

    @XmlElement
    public String id;
    @XmlElement
    public String name;
    @XmlElement
    public double rate;
    @XmlElement
    public String date;
    @XmlElement
    public String time;
    @XmlElement
    public String ask;
    @XmlElement
    public String bid;

    public Rate() {

    }

//    public Rate(String id, String name, double rate, String date, String time, String ask, String bid) {
//        this.id = id;
//        this.name = name;
//        this.rate = rate;
//        this.date = date;
//        this.time = time;
//        this.ask = ask;
//        this.bid = bid;
//    }
//
//    @XmlAttribute
//    public void setId(String id) {
//        this.id = id;
//    }
//    @XmlElement
//    public void setName(String name) {
//        this.name = name;
//    }
//    @XmlElement
//    public void setRate(double rate) {
//        this.rate = rate;
//    }
//    @XmlElement
//    public void setDate(String date) {
//        this.date = date;
//    }
//    @XmlElement
//    public void setTime(String time) {
//        this.time = time;
//    }
//    @XmlElement
//    public void setAsk(String ask) {this.ask = ask;}
//
//    @XmlElement
//    public void setBid(String bid) {
//        this.bid = bid;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getRate() {
//        return rate;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public String getAsk() {
//        return ask;
//    }
//
//    public String getBid() {
//        return bid;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //!!!!!!!сдесь выбивает nullPointer т к нет данных но демаршелятор написан так же как и у всех
        //правда в xml файле тег <query> подчеркнут красным и подписан что компилятор не может найти такой тег
        //что мне стоит изменить ???
        String s = sb.append("Comparing : ").append(name.toUpperCase()).append("\n").append("Rate : ").
                append(rate).append("\n").append("Date : ").append(date).append("\nTime : ").
                append(time).append("\nAsk : ").append(ask).append("\nBid : ").append(bid).toString();
//        String s = sb.append("Comparing : ").append(getName().toUpperCase()).append("\n").append("Rate : ").
//                append(getRate()).append("\n").append("Date : ").append(getDate()).append("\nTime : ").
//                append(getTime()).append("\nAsk : ").append(getAsk()).append("\nBid : ").append(getBid()).toString();
        return s;
    }
}
