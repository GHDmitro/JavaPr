package dklmv.task2Simple;

import dklmv.task2Simple.Rate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "results")
public class Results {
    @XmlElement(name = "rate")
    public Rate[] rate;
}
