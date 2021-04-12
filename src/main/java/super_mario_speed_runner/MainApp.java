package super_mario_speed_runner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;


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

        //stateManager.drawState();
    }

    @Override
    public void update() {

        stateManager.drawState();

    }


    @Override
    public void keyPressed(int keyCode, char key) {

        if (keyCode == this.LEFT) {

            System.out.println("Pressed left");
            stateManager.setGameState(GameState.START);

        }

        if (keyCode == this.RIGHT) {

            System.out.println("Pressed right");
            stateManager.setGameState(GameState.GAME);
        }

    }

    /*@Override
    public void keyPressed(KeyEvent e) { // Wrong method override

        if (keyCode == this.LEFT) {

            System.out.println("Pressed left");
            stateManager.setGameState(GameState.START);

        }

        if (keyCode == this.RIGHT) {

            System.out.println("Pressed right");
            stateManager.setGameState(GameState.GAME);
        }
    }*/

}
