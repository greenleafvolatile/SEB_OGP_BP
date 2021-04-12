package super_mario_speed_runner;

public class StateManager {

    private MainApp app;
    private Menu menu;
    private GameState gameState;
    //private int gameState = 1;


    public StateManager(MainApp app) {
        this.app = app;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    public void drawState() {

        switch (gameState) {

            case START:
                new Menu(this.app);
                break;


            case END:
                new Play(this.app);
                break;
        }

    }

}
