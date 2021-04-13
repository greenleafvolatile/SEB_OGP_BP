package super_mario_speed_runner;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CSVReader {

    private static final String PATH_TO_FILE = MainApp.MEDIA_URL + "maps/map.csv";

    public static int[][] readFile() {

        int[][] map = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE))) { // Auto close input stream with try-with-resources.

            map = new int[getNumberOfLines()][];

            //String line;
            for (int i = 0; i < map.length; i++) {
                map[i] = toIntArray(reader.readLine().split(","));
            }

            /* int lineCount = 0;
            List<String[]> lines = new ArrayList<>();
            int[][] data = new int[0][];

            while ((line = reader.readLine()) != null) {

                /*++lineCount;

                int[][] map = new int[lineCount][line.length()];

                System.out.println("count: " + lineCount);
                for (String s : line.split(",")) {
                    System.out.println("s: " + s);
                    map[lineCount - 1][Integer.parseInt(s)] = Integer.parseInt(s);
                }

                System.arraycopy(data, 0, map, 0, lineCount - 1);//copy previously read values to new array
                data = map;//set new array as csv data

            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }

    private static int[] toIntArray(String[] array) {
        int[] result = new int[array.length];
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

    public static void main(String[] args) {
        int[][] map = CSVReader.readFile();
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }

    }
}
