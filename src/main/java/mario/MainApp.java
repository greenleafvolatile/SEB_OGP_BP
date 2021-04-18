package mario;

import mario.tiles.MarioTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import mario.tiles.FloorTile;

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

    /**
     * Create view with center viewport
     *
     * @param worldWidth   Game level total width
     * @param worldHeight  Game level total Height
     * @param screenWidth  Total width screen
     * @param screenHeight Total height screen
     *
     * Based on code from Waterworld
     */
    public void createViewCenterViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight) {

        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, screenWidth, screenHeight, 0, 100);
        viewPort.setTolerance(50, 0, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
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
