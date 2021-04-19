package mario;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.view.Viewport;
import org.w3c.dom.Text;

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
        createDashboard();
    }

    private void createObjects() {
        this.player = new Player(this.app);
        this.app.addGameObject(player, 0, 481);
    }

    private void createDashboard() {
        int screenWidth = this.app.getWidth();
        int screenHeight = this.app.getHeight();

        final int xMargin = 40;
        final int yMargin = 20;

        Dashboard dashboard = new Dashboard(0, 0, screenWidth, screenHeight);

        // TEXT LEFT
        TextObject playerName = new TextObject("Player", 32);
        playerName.setForeColor(255, 255, 255, 255);
        dashboard.addGameObject(playerName, xMargin, yMargin);

        // TEXT MIDDLE
        TextObject time = new TextObject("00:00:00", 32);
        time.setForeColor(255, 255, 255, 255);
        dashboard.addGameObject(time, screenWidth / 2 - 50, yMargin);

        // TEXT RIGHT
        TextObject keysCollected = new TextObject("Keys", 32);
        keysCollected.setForeColor(255, 255, 255, 255);
        dashboard.addGameObject(keysCollected, screenWidth - 110, yMargin);

        this.app.addDashboard(dashboard);
    }

    private TileMap initMap() {
        TileType[] tileTypes =  loadTileTypes();
        int[][] tilesMap = MapLoader.loadMap(mapFiles[0]);
        return new TileMap(64, tileTypes, tilesMap);
    }

    private Viewport centerViewport() {
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
