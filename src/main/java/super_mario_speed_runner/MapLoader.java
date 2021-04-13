package super_mario_speed_runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class MapLoader {

    private static final String PATH_TO_FILE = MainApp.MEDIA_URL + "maps/map.csv";

    /***
     * Used to import a TileMap in CSV format
     * @return integer 2D array
     */
    public static int[][] loadMap() {

        int[][] map = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE))) { // Auto close input stream with try-with-resources.

            map = new int[getNumberOfLines()][];

            for (int i = 0; i < map.length; i++) {
                map[i] = toIntArray(reader.readLine().split(","));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }

    private static int[] toIntArray(String[] array) {

        final int[] result = new int[array.length];

            for (int i = 0; i < array.length; i++) {
                result[i] = Integer.parseInt(array[i]);
            }

        return result;

    }

    private static int getNumberOfLines() throws IOException {

        int numberOfLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE))) {

            while (reader.readLine() != null) {
                numberOfLines++;
            }

        }

        return numberOfLines;

    }

}
