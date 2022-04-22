import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Pokerdle
 */
public class Pokerdle {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("pokemon.txt")));
        Map<String, String> integerDateMap = new HashMap<>(); // Map to put data
        String line = null;
        line = bufferedReader.readLine(); // neglect first line
        while((line = bufferedReader.readLine())!= null){
             String[] split = line.split(":");
             System.out.println(split[1]);
             integerDateMap.put(split[0], split[1]);
        }
        System.out.println(integerDateMap);

    }
}