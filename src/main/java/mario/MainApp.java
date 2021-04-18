package mario;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;

import java.io.File;

public class MainApp extends GameEngine {

    public static final int JUMP = 32;
    public static String MEDIA_URL = "src/main/java/mario/media/";

    private Player player;
    private File[] mapFiles = { new File(MEDIA_URL.concat("maps/lvl1.csv"))};

    private StateManager stateManager;


    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int screenWidth = 1204;
        int screenHeight = 640;
        size(screenWidth, screenHeight);

        this.stateManager = new StateManager(this);
        stateManager.drawState();
    }

    @Override
    public void update() {}

    @Override
    public void keyPressed() {

        if (keyCode == this.LEFT) {

            System.out.println("Pressed left");
            this.stateManager.setGameState(GameState.START);

        }

        if (keyCode == this.RIGHT) {

            System.out.println("Pressed right");
            this.stateManager.setGameState(GameState.GAME);
        }

        this.stateManager.drawState();
    }
}
