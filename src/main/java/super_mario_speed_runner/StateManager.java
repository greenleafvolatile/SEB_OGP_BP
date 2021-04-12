package super_mario_speed_runner;

public class StateManager {

    private MainApp app;
    private Menu menu;
    private int gameState = 1;


    public StateManager(MainApp app) {
        this.app = app;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }


    public void drawState() {

        switch (gameState) {

            case 1:
                new Menu(this.app);
                break;


            case 2:
                new Play(this.app);
                break;
        }

    }

}
