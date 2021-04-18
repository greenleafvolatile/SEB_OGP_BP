package mario;

import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;

import java.io.File;

import static mario.TileTypeLoader.loadTileTypes;

public class Game {

    private MainApp app;
    private Player player;
    private TileMap tileMap;

    private final File[] mapFiles = { new File(MainApp.MEDIA_URL.concat("maps/lvl1.csv"))};

    public Game(MainApp app) {
        this.app = app;
        this.tileMap = initMap();
        this.app.setTileMap(tileMap);
    }

    public void display() {
        createObjects();
        initMap();
        createView();
    }

    private void createObjects() {
        this.player = new Player(this.app);
        this.app.addGameObject(player, 0, 481);
    }

    private TileMap initMap() {
        TileType[] tileTypes =  loadTileTypes();
        int[][] tilesMap = MapLoader.loadMap(mapFiles[0]);
        return new TileMap(64, tileTypes, tilesMap);
    }

    private CenterFollowingViewport centerViewport() {
        int screenWidth = this.app.getWidth();
        int screenHeight = this.app.getHeight();

        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, screenWidth, screenHeight, 0, 100);
        viewPort.setTolerance(50, 0, 50, 50);
        return viewPort;
    }

    private void createView() {
        int worldWidth = this.tileMap.getMapWidth();
        int worldHeight = this.tileMap.getMapHeight();

        View view = new View(centerViewport(), worldWidth, worldHeight);
        this.app.setView(view);
    }
}
