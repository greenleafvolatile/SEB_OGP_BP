package mario.model.map;

import mario.MainApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class MapLoader {

    private MapLoader(){}

    /***
     * Used to import a TileMap in CSV format
     * @return integer 2D array
     */
    public static int[][] loadMap(File file) {

        int[][] map = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Auto close input stream with try-with-resources.

            map = new int[getNumberOfLines(file)][];

            for (int i = 0; i < map.length; i++) {
                map[i] = toIntArray(reader.readLine().split(","));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }

    public static int[][] loadEmptyMap() {

        return loadMap(new File(MainApp.MEDIA_URL.concat("data/maps/empty.csv")));
    }

    private static int[] toIntArray(String[] array) {

        final int[] result = new int[array.length];

            for (int i = 0; i < array.length; i++) {
                result[i] = Integer.parseInt(array[i]);
            }

        return result;

    }

    private static int getNumberOfLines(File file) throws IOException {

        int numberOfLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while (reader.readLine() != null) {
                numberOfLines++;
            }

        }

        return numberOfLines;

    }

}
