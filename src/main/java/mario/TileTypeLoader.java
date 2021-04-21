package mario;

import mario.tiles.DoorTile;
import mario.tiles.FloorTile;
import mario.tiles.KeyTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileType;

public final class TileTypeLoader {

    private TileTypeLoader(){}

    public static TileType<Tile>[] loadTileTypes() {

        Sprite boxSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/box.png"); // 0
        Sprite groundSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/ground.png"); // 1
        Sprite lavaSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/lava.png"); // 2
        Sprite doorYellowTopSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/locked_door_yellow_top.png"); // 3
        Sprite doorYellowBottomSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/locked_door_yellow_bottom.png"); // 4
        Sprite noLockDoorBottomSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/no_lock_door_bottom.png"); // 5
        Sprite noLockDoorTopSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/no_lock_door_top.png"); // 6
        Sprite platformBlueSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/platform_blue.png"); // 7
        Sprite keySprite = new Sprite(MainApp.MEDIA_URL + "sprites/items/keyYellow.png"); // 8
        Sprite groundLayerSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/ground_layer.png"); // 9
        Sprite lavaLayerSprite = new Sprite(MainApp.MEDIA_URL + "sprites/ground/lava_layer.png"); // 10

        TileType<FloorTile> boxTileType = new TileType<>(FloorTile.class, boxSprite); // 0
        TileType<FloorTile> groundTileType = new TileType<>(FloorTile.class, groundSprite); // 1
        TileType<LavaTile> lavaTileType = new TileType<>(LavaTile.class, lavaSprite); // 2
        TileType<FloorTile> doorYellowTopTileType = new TileType<>(FloorTile.class, doorYellowTopSprite); // 3
        TileType<FloorTile> doorYellowBottomTileType = new TileType<>(FloorTile.class, doorYellowBottomSprite); // 4
        TileType<DoorTile> noLockDoorBottomTileType = new TileType<>(DoorTile.class, noLockDoorBottomSprite); // 5
        TileType<DoorTile> noLockDoorTopTileType = new TileType<>(DoorTile.class, noLockDoorTopSprite); // 6
        TileType<FloorTile> platformBlueTileType = new TileType<>(FloorTile.class, platformBlueSprite); // 7
        TileType<KeyTile> keyTileType = new TileType<>(KeyTile.class, keySprite); // 8
        TileType<FloorTile> groundLayerType = new TileType<>(FloorTile.class, groundLayerSprite); // 9
        TileType<LavaTile> lavaLayerType = new TileType<>(LavaTile.class, lavaLayerSprite); // 9



        @SuppressWarnings("rawtypes")
        TileType[] tileTypes = new TileType[]{
                boxTileType,
                groundTileType,
                lavaTileType,
                doorYellowTopTileType,
                doorYellowBottomTileType,
                noLockDoorBottomTileType,
                noLockDoorTopTileType,
                platformBlueTileType,
                keyTileType,
                groundLayerType,
                lavaLayerType
        };

        return tileTypes;
    }
}
