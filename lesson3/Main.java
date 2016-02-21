package lesson3;

import java.lang.Thread;

//localhost:8080/
public class Main {
    public static void main(String[] args) {


        final HTTPServer server = new HTTPServer(8080, "/Users/macbookair/IdeaProjects/JavaPro/src/lesson3");//каталог где у нас лежит index.html
        server.start();

        System.out.println("Server started...");
//прі завершеніі работи сервера включается поток закритія сервера
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.stop();
                System.out.println("Server stopped!");
            }
        });
    }
}
