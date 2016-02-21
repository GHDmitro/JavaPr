package homeWork2.task2Simple;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by macbookair on 19.02.16.
 */

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
        String url = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select * from yahoo." +
                "finance.xchange where pair in (\"USDEUR\", \"USDUAH\")&env=store://datatables.org/alltableswithkeys";


        File file = new File("yql.xml");
        String d = performData(url);
        System.out.println();

        JAXBContext context = JAXBContext.newInstance(Query.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(context,file);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Query query = (Query) unmarshaller.unmarshal(file);

        System.out.println(Arrays.deepToString(query.results.rate));
//        myXmlDoc.getQuery().getResults().toString();

    }

    private static String performData(String url) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection http = (HttpURLConnection) url1.openConnection();
        FileOutputStream fr = new FileOutputStream("yql.xml");
        StringBuilder sb;

        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(url)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
            BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
            sb = new StringBuilder();
            String s;
            while ((s = rd.readLine()) != null) {
                sb.append(s);
            }
        } finally {
            http.disconnect();
        }
        fr.write(sb.toString().getBytes());
        fr.flush();
        return sb.toString();
    }
//    private static String performRe
}

