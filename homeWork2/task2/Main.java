package homeWork2.task2;

import javax.xml.bind.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by macbookair on 18.02.16.
 */
public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
        String url = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";


        File file = new File("/Users/macbookair/IdeaProjects/JavaPro/yql.xml");


//        String res = performData(url);
//        System.out.println(res);
        String str = performData(url);
        JAXBContext context = JAXBContext.newInstance(Query.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(context,file);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Query query = (Query) unmarshaller.unmarshal(file);
        System.out.println(query.results.toString());

//        System.out.println(query.results.getList().get(1).getDate());
//        query.results.getList().forEach((e)->{
//            System.out.println(e.toString());
//        });
//        myXmlDoc.getQuery().getResults().toString();

    }

    private static String performData(String url) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection http = (HttpURLConnection) url1.openConnection();
        FileWriter fr = new FileWriter("/Users/macbookair/IdeaProjects/JavaPro/yql.xml");
        StringBuilder sb = new StringBuilder();

        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(url)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
            BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String s;
            while ((s = rd.readLine()) != null) {
                System.out.println(s);
                sb.append(s);
                fr.write(s.toCharArray());
            }
        } finally {
            http.disconnect();
        }
//        fr.write(sb.toString().getBytes());
        fr.flush();
        return sb.toString();
    }
//    private static String performRe
}
