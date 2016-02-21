package lesson3;

public class HTTPServer {
    private int port;
    private String path;
    private ListenThread listenThread;

    public HTTPServer(int port, String path) {
        this.port = port;
        this.path = path;
    }

    public void start() {
        //запускает все приложение наследник thread
        listenThread = new ListenThread(port, path);
        listenThread.start();
    }

    //выход из потока listener
    public void stop() {
        listenThread.interrupt();
    }
}