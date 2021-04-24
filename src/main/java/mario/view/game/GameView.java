package mario.view.game;

import mario.MainApp;
import mario.model.enemy.FlyingTurtle;
import mario.model.enemy.Goomba;
import mario.model.enemy.PiranhasPlant;
import mario.model.map.MapLoader;
import mario.model.Player;
import mario.view.Screen;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.view.Viewport;

import java.io.File;

import static mario.model.map.TileTypeLoader.loadTileTypes;

/**
 * The type Game view.
 */
public class GameView extends Screen {

    private final Player player;
    private final TileMap tileMap;

    private final File[] mapFiles = { new File(MainApp.MEDIA_URL.concat("maps/level1.csv"))};

    /**
     * Instantiates a new Game view.
     *
     * @param app        the app
     * @param playerName the player name
     */
    public GameView(MainApp app, String playerName) {
        super(app);
        this.tileMap = this.initMap();
        this.app.setTileMap(this.tileMap);
        this.player = new Player(app, playerName);
        this.createDashboard();
    }

    private void createDashboard() {
        this.app.addDashboard(
                new GameDashboard(
                        this.app,
                        0, 0,
                        this.app.getWidth(),
                        this.app.getHeight(),
                        this.player
                ));
    }

    private TileMap initMap() {

        final int tileSize = 64;

        @SuppressWarnings("unchecked")
        TileType<Tile>[] tileTypes =  loadTileTypes();

        int level = 1;
        int[][] tilesMap = MapLoader.loadMap(mapFiles[level - 1]);
        return new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void addObjects() {
        this.app.addGameObject(this.player, 580, 802);

        this.app.addGameObject(new Goomba(this.app), 1856, 834);
        this.app.addGameObject(new Goomba(this.app), 3733, 834);
        this.app.addGameObject(new FlyingTurtle(this.app), 1200, 545);
        this.app.addGameObject(new FlyingTurtle(this.app), 10, 300);
        this.app.addGameObject(new FlyingTurtle(this.app), 4669, 609);
        this.app.addGameObject(new PiranhasPlant(this.app), 2410, 835);
        this.app.addGameObject(new PiranhasPlant(this.app), 6066, 835);
    }

    private Viewport centerViewport() {
        int screenWidth = this.app.getWidth();
        int screenHeight = this.app.getHeight();

        CenterFollowingViewport viewPort = new CenterFollowingViewport(player, screenWidth, screenHeight, 0, 100);
        viewPort.setTolerance(50, 280, 50, 50);
        return viewPort;
    }

    @Override
    public View createView() {
        int worldWidth = this.tileMap.getMapWidth();
        int worldHeight = this.tileMap.getMapHeight();

        View view = new View(centerViewport(), worldWidth, worldHeight);
        view.setBackground(153, 217, 234);
        return view;
    }
}
