package lesson3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListenThread extends Thread {
    private int port;
    private String path;

    //Класс слушатель
    public ListenThread(int port, String path) {
        this.port = port;
        this.path = path;
    }

    public void run() {
        try {

            ServerSocket srv = new ServerSocket(port);
            //фиксированный пулл потоков в данном случае 4 штуке
            ExecutorService pool = Executors.newFixedThreadPool(4);
            try {
                //работает пока не присоедениться клиент
                while (!isInterrupted()) {//пока поток не остановлен
                    //соедининие
                    Socket socket = srv.accept();//метод соединения сокетов
                    //создаем клиента
                    Client client = new Client(socket, path);
                    //запускает thread client
                    //в одельный пул кидается клиент и запускается поток
                    pool.submit(client);
                    //задержка потока чтобы не было наложения информациии
                    //для запуска второго потока
                    Thread.sleep(50);
                }
            } finally {
                srv.close();
                pool.shutdown();//пул удалить
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            return;
        }
    }
}
