package super_mario_speed_runner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

import java.awt.event.KeyEvent;

public class MainApp extends GameEngine {

    private StateManager stateManager;

    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSketch();
    }


    @Override
    public void setupGame() {
        int screenWidth = 1024;
        int screenHeight = 768;

        this.stateManager = new StateManager(this);

        size(screenWidth, screenHeight);

        View view = new View(screenWidth, screenHeight);
        setView(view);

        stateManager.drawState();
    }

    @Override
    public void update() {

    }



    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (keyCode == LEFT) {
            loop();
            deleteAllGameOBjects();
            stateManager.setGameState(2);
            stateManager.drawState();

        } else if (keyCode == RIGHT) {
            deleteAllGameOBjects();
            stateManager.setGameState(1);
            stateManager.drawState();
        }
    }

}
