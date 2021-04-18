package mario;

public class StateManager {

    private MainApp app;
    private StartMenu menu;
    private GameState gameState = GameState.START;
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

        this.app.deleteAllGameOBjects();

        switch (gameState) {

            case START:
                new StartMenu(this.app);
                break;


            case GAME:
                new Game(this.app);
                break;
        }

    }

}
