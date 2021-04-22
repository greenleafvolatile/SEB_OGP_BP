package mario;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.persistence.FilePersistence;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";


    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {

        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        FilePersistence filePersistence = new FilePersistence("/main/java/mario/media/highscores/highscores.txt");
        String[] data = filePersistence.loadDataStringArray("\n");
        System.out.println(data.length);
        for (String string : data) {
            String[] split = string.split(" ");
            String name = split[0];
            String time = split[1];


            System.out.println(name);

            System.out.println(time);
        }
        String newEntry = "Piet "


        new StateManager(this);
    }

    @Override
    public void update() {}
}
