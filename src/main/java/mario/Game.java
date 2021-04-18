package mario;

import mario.MainApp;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.PGraphicsCreator;
import nl.han.ica.oopg.view.View;

import java.io.File;

import static mario.TileTypeLoader.loadTileTypes;

public class Game {

    private MainApp app;
    private Player player;

    private final File[] mapFiles = { new File(MainApp.MEDIA_URL.concat("maps/lvl1.csv"))};

    public Game(MainApp app) {
        this.app = app;
        this.app.setTileMap(initMap());
    }

    public void display() {
        createObjects();
        initMap();
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
}
