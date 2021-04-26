package mario.model.map;

import mario.MainApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is a utility class for loading an int[][] array
 * from a file.
 */
public final class MapLoader {

    private MapLoader(){}

    /**
     * This method loads a map from a file.
     * @param file a file
     * @return int[][]
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

    /**
     * This method loads an empty map from a file.
     * @return int[][]
     */
    public static int[][] loadEmptyMap() {
        return loadMap(new File(MainApp.MEDIA_URL.concat("/maps/empty.csv")));
    }


    /**
     * This method is a helper method for converting a numerical string array
     * to an integer array.
     * @param array an array of numerical strings.
     * @return int[]
     */
    private static int[] toIntArray(String[] array) {
        final int[] result = new int[array.length];

            for (int i = 0; i < array.length; i++) {
                result[i] = Integer.parseInt(array[i]);
            }
            return result;
    }

    /**
     * This is a helper method for retrieving the number of lines in a file.
     * @param file a file
     * @return int the number of lines.
     * @throws IOException an IOException.
     */
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
