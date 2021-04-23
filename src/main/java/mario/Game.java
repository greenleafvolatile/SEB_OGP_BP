package mario;

import mario.dashboard.GameDashboard;
import mario.enemies.PiranhasPlant;
import mario.tiles.MarioTile;
import mario.enemies.Enemy;
import mario.enemies.FlyingTurtle;
import mario.enemies.Goomba;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.view.Viewport;

import java.io.File;

import static mario.TileTypeLoader.loadTileTypes;

public class Game {

    private final MainApp app;
    private final String playerName;

    private Player player;
    private int level = 1;
    private TileMap tileMap;

    private final File[] mapFiles = { new File(MainApp.MEDIA_URL.concat("maps/level1.csv"))};

    public Game(MainApp app, String playerName) {
        this.app = app;
        this.playerName = playerName;
        this.tileMap = this.initMap();
        this.app.setTileMap(this.tileMap);
        createDashboard();
        createObjects();
        initMap();
        createView();
    }

    /*public void display() {
        createDashboard();
        createObjects();
        initMap();
        createView();
    }*/

    private void createObjects() {
        player = new Player(this.app);
        this.app.addGameObject(player, 580, 802);

        this.app.addGameObject(new Goomba(this.app), 1856, 834);
        this.app.addGameObject(new Goomba(this.app), 3733, 834);
        this.app.addGameObject(new FlyingTurtle(this.app), 1200, 545);
        this.app.addGameObject(new FlyingTurtle(this.app), 10, 300);
        this.app.addGameObject(new FlyingTurtle(this.app), 4669, 609);
        this.app.addGameObject(new PiranhasPlant(this.app), 2410, 835);
        this.app.addGameObject(new PiranhasPlant(this.app), 6066, 835);

    }

    private void createDashboard() {
        this.app.addDashboard(new GameDashboard(this.app,0, 0, this.app.getWidth(), this.app.getHeight(), this.playerName));
    }

    private TileMap initMap() {

        final int tileSize = 64;

        @SuppressWarnings("unchecked")
        TileType<Tile>[] tileTypes =  loadTileTypes();

        int[][] tilesMap = MapLoader.loadMap(mapFiles[level - 1]);
        return new TileMap(tileSize, tileTypes, tilesMap);
    }

    private Viewport centerViewport() {
        int screenWidth = this.app.getWidth();
        int screenHeight = this.app.getHeight();

        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, screenWidth, screenHeight, 0, 100);
        viewPort.setTolerance(50, 280, 50, 50);
        return viewPort;
    }

    private void createView() {
        int worldWidth = this.tileMap.getMapWidth();
        int worldHeight = this.tileMap.getMapHeight();

        View view = new View(centerViewport(), worldWidth, worldHeight);
        view.setBackground(153, 217, 234);
        this.app.setView(view);
    }
}
