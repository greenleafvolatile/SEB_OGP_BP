package tutorials;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tutorials.tiles.FloorTile;

public class TutorialWorld extends GameEngine {

    private Sound jumpSound, backgroundMusic;
    private Player player;
    private LightningBall lightningBall;

    // Deze regel maakt het makkelijker om te refereren naar je plaatjes.
    public static String MEDIA_URL = "src/main/java/media/";

    public static void main(String[] args) {
        TutorialWorld tw = new TutorialWorld();
        tw.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 500;
        int worldHeight = 500;

        loadSounds();
        initializeTileMap();

        // Als je geluiden wilt gebruiken bij een game object dan moet je
        // ze maken voordat je de gameobjecten initialiseert.

        // In setupGame() moeten de game objecten toegevoegd worden.
        // Uiteraard kan je het toevoegen van
        // nieuwe game objects misschien het beste
        // in een aparte methode doen
        // i.p.v. de update zo groot te maken.
        player = new Player(this, jumpSound);
        lightningBall = new LightningBall(this);

        addGameObject(player, 200, 0);
        addGameObject(lightningBall, 0, 50);

        View view = new View(worldWidth, worldHeight);
        setView(view); // Als deze niet aangeroepen wordt, crasht de applicatie.

        size(worldWidth, worldHeight);
    }

    public void loadSounds() {

        jumpSound = new Sound(this, TutorialWorld.MEDIA_URL.concat("/sounds/jump_11.wav"));
        backgroundMusic = new Sound(this, TutorialWorld.MEDIA_URL.concat("/sounds/8BitMenuMusicSlow.wav"));
        backgroundMusic.loop(-1);
    }

    @Override
    public void update() {

        // Dit doet nog helemaal niks.
    }

    private void initializeTileMap() {

        // load sprites
        Sprite floorSprite = new Sprite(TutorialWorld.MEDIA_URL.concat("sprites/tiles/platformPack_tile001.png"));

        TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);

        int tileSize = 64;

        TileType[] tileTypes = {floorTileType};

        int tilesMap[][] = {
                {-1, -1, -1, -1, -1, -1, -1,},
                {-1, -1, -1, -1, -1, -1, -1,},
                {-1, -1, -1, -1, -1, -1, -1,},
                {-1, -1, -1, -1, -1, -1, -1,},
                {0, 0, 0, 0, 0, 0, 0,},
                {-1, -1, -1, -1, -1, -1, -1,},
                {-1, -1, -1, -1, -1, -1, -1,},
        };

        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}