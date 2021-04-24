package mario;

import mario.view.menu.StartMenu;

public class StateManager {

    private MainApp app;
    private StartMenu menu;
    private GameState gameState = GameState.START;



    public StateManager(MainApp app) {
        this.app = app;
        this.drawState();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        drawState();

    }

    public void drawState() {

        this.app.deleteAllGameOBjects();

        switch (gameState) {

            case START:
                //new StartMenu(this.app, this);
                break;


            case GAME:
                //this.game = new Game(this.app, String playerName);
                //this.game.display();
                break;
        }

    }

}
