package mario;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import mario.tiles.FloorTile;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";

    private Player player;


    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSketch();
    }


    @Override
    public void setupGame() {
        int screenWidth = 1204;
        int screenHeight = 640;

        size(screenWidth, screenHeight);
        createGameObjects();

        createViewCenterViewport(6400, 640, screenWidth, screenHeight);
        initializeTileMap();


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
        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, (int) Math.ceil(screenWidth), (int) Math.ceil(screenHeight), 0, 100);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }

    @Override
    public void update() {

    }

    public void createGameObjects() {
        player = new Player(this);
        addGameObject(player, 0, 481);
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
