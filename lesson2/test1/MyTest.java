package lesson2.test1;

import com.sun.org.apache.xml.internal.security.transforms.Transform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by macbookair on 15.02.16.
 */
public class MyTest {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element element = document.createElement("catalog");
            document.appendChild(element);

            //Book1
            Element book1 = document.createElement("book");
            element.appendChild(book1);

            Element author1 = document.createElement("author");
            author1.setTextContent("Author 1");
            book1.appendChild(author1);
            Element title = document.createElement("title");
            title.setTextContent("Title first");
            book1.appendChild(title);

            //book2

            Element book2 = document.createElement("book");
            element.appendChild(book2);

            Element author2 = document.createElement("author");
            author2.setTextContent("Author 2");
            book2.appendChild(author2);

            Element title2 = document.createElement("title");
            title2.setTextContent("Title second");
            book2.appendChild(title2);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transform = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("2.xml"));

            transform.transform(source, streamResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
