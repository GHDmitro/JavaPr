package homeWork3.Task1;

/**
 * Created by macbookair on 21.02.16.
 */
public class ControlCash {

    public static void main(String[] args) throws InterruptedException {
        String pathOfPage = "/Users/macbookair/IdeaProjects/JavaPro/src/homeWork3/Task1";

        FileManager fm = new FileManager(pathOfPage);
        for (int i = 0; i < 5; i++) {
            fm.get("/index.html");
            Thread.sleep(3000);
        }
    }

}
