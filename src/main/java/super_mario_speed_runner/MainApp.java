package super_mario_speed_runner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tutorials.tiles.FloorTile;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/super_mario_speed_runner/media/";

    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSketch();
    }


    @Override
    public void setupGame() {
        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        initializeTileMap();

        View view = new View(screenWidth, screenHeight);
        setView(view);
    }

    @Override
    public void update() {

    }


    /**
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite(MEDIA_URL + "sprites/ground/ground.png");
        TileType<FloorTile> boardTileType = new TileType<>(FloorTile.class, boardsSprite);

        TileType[] tileTypes = {boardTileType};
        int tileSize = 64;
        int[][] tilesMap = MapLoader.loadMap();
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}
