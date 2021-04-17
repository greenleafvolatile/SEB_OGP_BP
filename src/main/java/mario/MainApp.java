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
    private Map map;
    private File[] mapFiles = { new File(MEDIA_URL.concat("maps/lvl1.csv"))};
    private TileType[] tileTypes;
    private int level = 1;

    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int screenWidth = 1204;
        int screenHeight = 640;
        size(screenWidth, screenHeight);

        this.tileTypes = TileTypeLoader.loadTileTypes();

        createGameObjects();

        initMap();

        createViewCenterViewport(this.map.getMapWidth(), this.map.getMapHeight() , screenWidth, screenHeight);
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
    private void createViewCenterViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight) {

        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, screenWidth, screenHeight, 0, 100);
        viewPort.setTolerance(50, 0, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }

    @Override
    public void update() {}

    public void createGameObjects() {

        player = new Player(this);
        addGameObject(player, 0, 481);
    }


    private void initMap() {

        this.map = new Map(mapFiles[level - 1]);
        TileMap tileMap = new TileMap(MarioTile.getTileSize(), this.tileTypes, map.getTilesMap());
        this.setTileMap(tileMap);
    }
}
