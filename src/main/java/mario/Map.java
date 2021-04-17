package mario;

import mario.tiles.*;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileType;

import java.io.File;

public class Map {

    private final int[][] tilesMap;
    private final int mapHeight;
    private final int mapWidth;

    public Map(File mapFile) {

        this.tilesMap = MapLoader.loadMap(mapFile);
        this.mapWidth = tilesMap[0].length * MarioTile.getTileSize();
        this.mapHeight = tilesMap.length * MarioTile.getTileSize();

    }

    public int[][] getTilesMap() {
        return tilesMap;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

}
