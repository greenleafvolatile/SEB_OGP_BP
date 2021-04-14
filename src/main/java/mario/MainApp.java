package mario;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import mario.tiles.FloorTile;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";

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
        Sprite boxSprite = new Sprite(MEDIA_URL + "sprites/ground/box.png"); // 0
        Sprite groundSprite = new Sprite(MEDIA_URL + "sprites/ground/ground.png"); // 1
        Sprite lavaSprite = new Sprite(MEDIA_URL + "sprites/ground/lava.png"); // 2
        Sprite doorYellowTopSprite = new Sprite(MEDIA_URL + "sprites/ground/locked_door_yellow_top.png"); // 3
        Sprite doorYellowBottomSprite = new Sprite(MEDIA_URL + "sprites/ground/locked_door_yellow_bottom.png"); // 4
        Sprite noLockDoorBottomSprite = new Sprite(MEDIA_URL + "sprites/ground/no_lock_door_bottom.png"); // 5
        Sprite noLockDoorTopSprite = new Sprite(MEDIA_URL + "sprites/ground/no_lock_door_top.png"); // 6
        Sprite platformBlueSprite = new Sprite(MEDIA_URL + "sprites/ground/platform_blue.png"); // 7


        TileType<FloorTile> boxTileType = new TileType<>(FloorTile.class, boxSprite); // 0
        TileType<FloorTile> groundTileType = new TileType<>(FloorTile.class, groundSprite); // 1
        TileType<FloorTile> lavaTileType = new TileType<>(FloorTile.class, lavaSprite); // 2
        TileType<FloorTile> doorYellowTopTileType = new TileType<>(FloorTile.class, doorYellowTopSprite); // 3
        TileType<FloorTile> doorYellowBottomTileType = new TileType<>(FloorTile.class, doorYellowBottomSprite); // 4
        TileType<FloorTile> noLockDoorBottomTileType = new TileType<>(FloorTile.class, noLockDoorBottomSprite); // 5
        TileType<FloorTile> noLockDoorTopTileType = new TileType<>(FloorTile.class, noLockDoorTopSprite); // 6
        TileType<FloorTile> platformBlueTileType = new TileType<>(FloorTile.class, platformBlueSprite); // 7

        TileType[] tileTypes = {
                boxTileType,
                groundTileType,
                lavaTileType,
                doorYellowTopTileType,
                doorYellowBottomTileType,
                noLockDoorBottomTileType,
                noLockDoorTopTileType,
                platformBlueTileType
        };


        int tileSize = 64;
        int[][] tilesMap = MapLoader.loadMap();
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}
