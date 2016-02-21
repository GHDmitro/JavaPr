package dklmv.task2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Created by macbookair on 18.02.16.
 */
@XmlRootElement(name = "results")
public class Results {

    @XmlElement(name = "rate")
    public Rate[] rate;
//    private List<Rate> list = new ArrayList<>();

    public Results() {

    }

//    public void add(Rate rate){
//        list.add(rate);
//    }
//
//    public List<Rate> getList() {
//        return list;
//    }

    @Override
    public String toString() {
//        Arrays.deepToString(list.toArray());
        return Arrays.deepToString(rate);
    }
}

