package mario.tiles;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

public class MarioTile extends Tile {

    private static final int tileSize = 64;

    public MarioTile(Sprite sprite) {
        super(sprite);
    }

    public static int getTileSize() {
        return tileSize;
    }

}
