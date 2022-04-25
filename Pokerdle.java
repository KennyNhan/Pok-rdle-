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
        Map<String, String> pokemonMap = new HashMap<>(); // Map to put data
        ArrayList<String> pokemon = new ArrayList<>();
        String line = null;
        line = bufferedReader.readLine(); // neglect first line
        while((line = bufferedReader.readLine())!= null){
             String formatLine = line.replaceAll(" ", "");
             String[] split = formatLine.split(":");
             pokemon.add(split[0]);
             pokemonMap.put(split[0], split[1]);
        }
        Random r = new Random();
        int upperBound = pokemon.size();
        String randomPokemon = pokemon.get(r.nextInt(upperBound));
        String[] correctValues = pokemonMap.get(randomPokemon).split(",");
        
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println(pokemonMap);
        int count = 1;
        while(count < 5){
            System.out.println("Guess a Pokemon");

            String guess = sc.nextLine();  // Read user input
            //guess.toLowerCase();
            String[] guessValues = pokemonMap.get(guess).split(",");

            String[] isItCorrect = new String [guessValues.length]; 

            for( int i = 0; i < correctValues.length - 1; i ++){
                if ( i == 0 || i == 3){
                    String tempGuess = guessValues[i];
                    System.out.println(tempGuess);
                    String tempCorrect = correctValues[i];
                    int intValue = Integer.valueOf(tempGuess);
                    int correctInt = Integer.valueOf(tempCorrect);
                    if (intValue < correctInt){
                        isItCorrect[i] = "less";
                    }
                    else if ( intValue > correctInt){
                        isItCorrect[i] = "more";
                    }
                    else{
                        isItCorrect[i] = "equal";
                    }
                }
                else{
                    if( correctValues[i] == guessValues[i]){
                        isItCorrect[i] = "true";
                    }
                    else{
                        isItCorrect[i] = "false";
                    }
                }
            }
            System.out.println("correct: " + correctValues);
            System.out.println("guess: " + guessValues);
            System.out.println(isItCorrect);
            count++; 
        }
        System.out.println("out of guesses");
    }


}