package lesson2.test2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Date;


//JAXB для документаціі документа если более мене статический документы (статические теги))
public class Main {

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Book("Author1", "Title1", 14.55, new Date()));
        catalog.add(new Book("Author2", "Title2", 66, new Date()));

        try {
            File file = new File("output.xml");
            //указываем класс описывающий корневой элемент
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // читабельное форматирование с отстубами с табуляцией
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // пишем в файл
            marshaller.marshal(catalog, file);
            //выводиться сам xml файл
            marshaller.marshal(catalog, System.out);

            // читаем из файла
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            catalog = (Catalog) unmarshaller.unmarshal(file);// откуда (из файла ) куда в catalog
            //выводитсья из java класса catalog
            System.out.println(catalog);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}