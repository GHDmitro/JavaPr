package dklmv.task2Simple;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")

public class Query {
    @XmlElement
    public int count;
    @XmlElement
    public String created;
    @XmlElement
    public String lang;
    @XmlElement
    public Results results;
}
