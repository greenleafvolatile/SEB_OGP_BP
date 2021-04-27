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
 * This class represents the game screen.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.1
 * @since 27-04-2021
 */
public class GameScreen extends Screen {

    private final Player player;
    private final TileMap tileMap;
    private final File[] mapFiles = { new File(MainApp.MEDIA_URL.concat("data/maps/level1.csv"))};

    private int level = 1; // Future iterations of the game could have different levels. To reflect this, the field is currently not final.

    /**
     * The constructor
     * @param app an instance of the game engine.
     * @param player a player object.
     */
    public GameScreen(MainApp app, Player player) {
        super(app);
        this.tileMap = this.initMap();
        this.app.setTileMap(this.tileMap);
        this.player = player;
        this.addDashboard();
        this.render();
    }

    private void addDashboard() {
        this.app.addDashboard(
                new GameDashboard(
                        0, 0,
                        this.app.getWidth(),
                        this.app.getHeight(),
                        this.player
                ));
    }

    /**
     * This method initiates the tile map that underlies the game world.
     * @return TileMap a tile map.
     */
    private TileMap initMap() {
        final int tileSize = 64;

        TileType<Tile>[] tileTypes =  loadTileTypes();
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

    /**
     * This method creates a viewport on the game world.
     * @return Viewport a view port.
     */
    private Viewport centerViewport() {
        int screenWidth = this.app.getWidth();
        int screenHeight = this.app.getHeight();

        CenterFollowingViewport viewPort = new CenterFollowingViewport(this.player, screenWidth, screenHeight, 0, 100);
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
