package mario;

import nl.han.ica.oopg.engine.GameEngine;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";
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
