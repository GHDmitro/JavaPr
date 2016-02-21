package homeWork2.task1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
//import java.util.Time;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by macbookair on 17.02.16.
 */
public class Main {

    public static void main(String[] args) {
        Trains trains = new Trains();

        trains.add(new Train("Kyev", "Donetsk", "19.12.2013", "15:05"));
        trains.add(new Train("Lviv", "Donetsk", "19.12.2013", "19:05"));


        try {
//            /Users/macbookair/IdeaProjects/JavaPro/src/homeWork2/
            File file = new File("homeWork2");
            JAXBContext context = JAXBContext.newInstance(Trains.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(trains, file);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            trains = (Trains) unmarshaller.unmarshal(file);

            List<Train> list = trains.getList();

            list.sort((e1, e2) -> e1.getTime().compareTo(e2.getTime()));

            list.forEach((e) -> {
                StringBuilder sb = new StringBuilder();
                sb.append(e.getTime()).setCharAt(sb.indexOf(":"), '.');
                Double time = Double.valueOf(sb.toString());
                if ((time >= 15.00) && (time <= 19.00)) {
                    System.out.println(e.toString());
                }
            });

            trains.add(new Train("Odessa", "Donetsk", "18.01.2014", "17:05"));

            marshaller.marshal(trains, file);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
