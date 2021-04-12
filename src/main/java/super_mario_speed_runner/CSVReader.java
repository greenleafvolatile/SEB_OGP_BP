package super_mario_speed_runner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private final String path = MainApp.MEDIA_URL + "sprites/map.csv";


    public void readFile() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;

            int lineCount = 0;
            List<String[]> lines = new ArrayList<>();
            int[][] data = new int[0][];

            while ((line = reader.readLine()) != null) {
                ++lineCount;

                int[][] map = new int[lineCount][line.length()];

                for (String s : line.split(",")) {
                    map[lineCount - 1][Integer.parseInt(s)] = Integer.parseInt(s);
                }

                System.arraycopy(data, 0, map, 0, lineCount - 1);//copy previously read values to new array
                data = map;//set new array as csv data

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
