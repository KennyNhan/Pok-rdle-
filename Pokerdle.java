import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Graphics.*; 
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pokerdle
 */
public class Pokerdle {
    JFrame frame;
    static final int GUESS_IMG_SIZE = 80;
    static Boolean won = false;

    // static JLabel corectPic;
    // static JLabel wrongPic;
    // static JLabel upPic;
    // static JLabel downPic;

    static JLabel generation1, generation2, generation3, generation4, generation5;
    static JLabel type11, type12, type13, type14, type15, type21, type22, type23, type24, type25;
    static JLabel evolution1, evolution2, evolution3, evolution4, evolution5;



    static String feedback = "Welcome to pok-rdle";
    static JLabel feedbackLabel = new JLabel(feedback);
    static ImageIcon img;
    static JLabel pic = new JLabel(img, JLabel.CENTER);
    static String randomPokemon;

    static String guessLabelText = "__";
    static JLabel label1 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label2 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label3 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label4 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label5 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label6 = new JLabel(guessLabelText , JLabel.CENTER);
    static ImageIcon correctImg;
    static ImageIcon wrongImg;
    static ImageIcon upImg;
    static ImageIcon downImg;

    Graphics g1;

    Graphics g2;

    Graphics g3;

    Graphics g4;
    

    public static void main(String[] args) throws IOException {
        Pokerdle pokerdle = new Pokerdle();
        pokerdle.displayGUI();
        // Frame frame = pokerdle.new Frame(userInput, inputDisplay);
        

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("pokemon.txt")));
        Map<String, String> pokemonMap = new HashMap<>(); // Map to put data
        ArrayList<String> pokemon = new ArrayList<>();
        String line = null;
        line = bufferedReader.readLine(); // neglect first line
        while((line = bufferedReader.readLine())!= null){
             String lowerLine = line.toLowerCase();
             String formatLine = lowerLine.replaceAll(" ", "");
             String[] split = formatLine.split(":");
             pokemon.add(split[0]);
             pokemonMap.put(split[0], split[1]);
        }

        Random r = new Random();
        int upperBound = pokemon.size() - 1;
        randomPokemon = pokemon.get(r.nextInt(upperBound));
        System.out.println(randomPokemon);
        String[] correctValues = pokemonMap.get(randomPokemon).split(",");
        getPokePic(randomPokemon);
        
        
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        
        int count = 0;
        String[] correctComp = {"equal", "true", "true", "equal"};
        int y = 150;
        System.out.println(pokemonMap);
        System.out.println(randomPokemon);
        String guess ="__";
        while(count < 5){
            System.out.println("Guess a Pokemon");
            feedback = "Guess a Pokemon";
            feedbackLabel.setText(feedback);

            if (count == 0) {
                while(label2.getText().equals("__")) {
                    System.out.println(randomPokemon);
                }
                guess = label2.getText();
                System.out.println(guess);
            }
            if (count == 1) {
                while(label3.getText().equals("__")) {
                    System.out.println("waiting.");
                }
                guess = label3.getText();
                System.out.println(guess);
            }
            if (count == 2) {
                while(label4.getText().equals("__")) {
                    System.out.println("waiting.");
                }
                guess = label4.getText();
                System.out.println(guess);
            }
            if (count == 3) {
                while(label5.getText().equals("__")) {
                    System.out.println("waiting.");
                }
                guess = label5.getText();
                System.out.println(guess);
            }
            if (count == 4) {
                while(label6.getText().equals("__")) {
                    System.out.println("waiting.");
                }
                guess = label6.getText();
                System.out.println(guess);
            }
            
            
            while(pokemonMap.get(guess.toLowerCase()) == null){
                System.out.println("Not a valid guess. Please guess another Pokemon");
                feedback = "Not a valid guess. Please guess another Pokemon";
                feedbackLabel.setText(feedback);
                guess = sc.nextLine();
            }
            
            String[] guessValues = pokemonMap.get(guess.toLowerCase()).split(",");

            String[] isItCorrect = new String [guessValues.length]; 

            for( int i = 0; i < correctValues.length; i ++){
                if ( i == 0 || i == 3){
                    String tempGuess = guessValues[i];
                    String tempCorrect = correctValues[i];
                    int intValue = Integer.valueOf(tempGuess);
                    int correctInt = Integer.valueOf(tempCorrect);
                    if (intValue == correctInt){
                        isItCorrect[i] = "equal";
                             

                    }
                    else if ( intValue < correctInt){
                        isItCorrect[i] = "more";
                    }
                    else{
                        isItCorrect[i] = "less";
                    }
                }
                else{
                    if( Arrays.asList(correctValues).contains(guessValues[i])){
                        isItCorrect[i] = "true";
                    }
                    else{
                        isItCorrect[i] = "false";
                    }
                }
            }
            int x = 250;
            for(String value : isItCorrect){
                
                if(value == "equal"){
                    //if value is the 0 index
                    if(value == isItCorrect[0] && count == 0) {
                        generation1.setIcon(correctImg);
                        generation1.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 1) {
                        generation2.setIcon(correctImg);
                        generation2.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 2) {
                        generation3.setIcon(correctImg);
                        generation3.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 3) {
                        generation4.setIcon(correctImg);
                        generation4.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 4) {
                        generation5.setIcon(correctImg);
                        generation5.setVisible(true);
                    }
                    //if value is the first index
                    if(value == isItCorrect[1] && count == 0) {
                        type11.setIcon(correctImg);
                        type11.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 1) {
                        type12.setIcon(correctImg);
                        type12.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 2) {
                        type13.setIcon(correctImg);
                        type13.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 3) {
                        type14.setIcon(correctImg);
                        type14.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 4) {
                        type15.setIcon(correctImg);
                        type15.setVisible(true);
                    }
                    //if value is the second index
                    if(value == isItCorrect[2] && count == 0) {
                        type21.setIcon(correctImg);
                        type21.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 1) {
                        type22.setIcon(correctImg);
                        type22.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 2) {
                        type23.setIcon(correctImg);
                        type23.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 3) {
                        type24.setIcon(correctImg);
                        type24.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 4) {
                        type25.setIcon(correctImg);
                        type25.setVisible(true);
                    }
                    //if value is the last index
                    if(value == isItCorrect[3] && count == 0) {
                        evolution1.setIcon(correctImg);
                        evolution1.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 1) {
                        evolution2.setIcon(correctImg);
                        evolution2.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 2) {
                        evolution3.setIcon(correctImg);
                        evolution3.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 3) {
                        evolution4.setIcon(correctImg);
                        evolution4.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 4) {
                        evolution5.setIcon(correctImg);
                        evolution5.setVisible(true);
                    }
                }
                if(value == "more"){
                    //if value is the 0 index
                    if(value == isItCorrect[0] && count == 0) {
                        generation1.setIcon(upImg);
                        generation1.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 1) {
                        generation2.setIcon(upImg);
                        generation2.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 2) {
                        generation3.setIcon(upImg);
                        generation3.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 3) {
                        generation4.setIcon(upImg);
                        generation4.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 4) {
                        generation5.setIcon(upImg);
                        generation5.setVisible(true);
                    }
                    //if value is the first index
                    if(value == isItCorrect[1] && count == 0) {
                        type11.setIcon(upImg);
                        type11.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 1) {
                        type12.setIcon(upImg);
                        type12.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 2) {
                        type13.setIcon(upImg);
                        type13.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 3) {
                        type14.setIcon(upImg);
                        type14.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 4) {
                        type15.setIcon(upImg);
                        type15.setVisible(true);
                    }
                    //if value is the second index
                    if(value == isItCorrect[2] && count == 0) {
                        type21.setIcon(upImg);
                        type21.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 1) {
                        type22.setIcon(upImg);
                        type22.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 2) {
                        type23.setIcon(upImg);
                        type23.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 3) {
                        type24.setIcon(upImg);
                        type24.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 4) {
                        type25.setIcon(upImg);
                        type25.setVisible(true);
                    }
                    //if value is the last index
                    if(value == isItCorrect[3] && count == 0) {
                        evolution1.setIcon(upImg);
                        evolution1.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 1) {
                        evolution2.setIcon(upImg);
                        evolution2.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 2) {
                        evolution3.setIcon(upImg);
                        evolution3.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 3) {
                        evolution4.setIcon(upImg);
                        evolution4.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 4) {
                        evolution5.setIcon(upImg);
                        evolution5.setVisible(true);
                    }
                }
                if(value == "less"){
                    //if value is the 0 index
                    if(value == isItCorrect[0] && count == 0) {
                        generation1.setIcon(downImg);
                        generation1.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 1) {
                        generation2.setIcon(downImg);
                        generation2.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 2) {
                        generation3.setIcon(downImg);
                        generation3.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 3) {
                        generation4.setIcon(downImg);
                        generation4.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 4) {
                        generation5.setIcon(downImg);
                        generation5.setVisible(true);
                    }
                    //if value is the first index
                    if(value == isItCorrect[1] && count == 0) {
                        type11.setIcon(downImg);
                        type11.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 1) {
                        type12.setIcon(downImg);
                        type12.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 2) {
                        type13.setIcon(downImg);
                        type13.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 3) {
                        type14.setIcon(downImg);
                        type14.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 4) {
                        type15.setIcon(downImg);
                        type15.setVisible(true);
                    }
                    //if value is the second index
                    if(value == isItCorrect[2] && count == 0) {
                        type21.setIcon(downImg);
                        type21.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 1) {
                        type22.setIcon(downImg);
                        type22.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 2) {
                        type23.setIcon(downImg);
                        type23.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 3) {
                        type24.setIcon(downImg);
                        type24.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 4) {
                        type25.setIcon(downImg);
                        type25.setVisible(true);
                    }
                    //if value is the last index
                    if(value == isItCorrect[3] && count == 0) {
                        evolution1.setIcon(downImg);
                        evolution1.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 1) {
                        evolution2.setIcon(downImg);
                        evolution2.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 2) {
                        evolution3.setIcon(downImg);
                        evolution3.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 3) {
                        evolution4.setIcon(downImg);
                        evolution4.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 4) {
                        evolution5.setIcon(downImg);
                        evolution5.setVisible(true);
                    }
                }
                if(value == "true"){
                    //if value is the 0 index
                    if(value == isItCorrect[0] && count == 0) {
                        generation1.setIcon(correctImg);
                        generation1.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 1) {
                        generation2.setIcon(correctImg);
                        generation2.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 2) {
                        generation3.setIcon(correctImg);
                        generation3.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 3) {
                        generation4.setIcon(correctImg);
                        generation4.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 4) {
                        generation5.setIcon(correctImg);
                        generation5.setVisible(true);
                    }
                    //if value is the first index
                    if(value == isItCorrect[1] && count == 0) {
                        type11.setIcon(correctImg);
                        type11.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 1) {
                        type12.setIcon(correctImg);
                        type12.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 2) {
                        type13.setIcon(correctImg);
                        type13.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 3) {
                        type14.setIcon(correctImg);
                        type14.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 4) {
                        type15.setIcon(correctImg);
                        type15.setVisible(true);
                    }
                    //if value is the second index
                    if(value == isItCorrect[2] && count == 0) {
                        type21.setIcon(correctImg);
                        type21.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 1) {
                        type22.setIcon(correctImg);
                        type22.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 2) {
                        type23.setIcon(correctImg);
                        type23.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 3) {
                        type24.setIcon(correctImg);
                        type24.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 4) {
                        type25.setIcon(correctImg);
                        type25.setVisible(true);
                    }
                    //if value is the last index
                    if(value == isItCorrect[3] && count == 0) {
                        evolution1.setIcon(correctImg);
                        evolution1.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 1) {
                        evolution2.setIcon(correctImg);
                        evolution2.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 2) {
                        evolution3.setIcon(correctImg);
                        evolution3.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 3) {
                        evolution4.setIcon(correctImg);
                        evolution4.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 4) {
                        evolution5.setIcon(correctImg);
                        evolution5.setVisible(true);
                    }
                }
                if(value == "false"){
                    //if value is the 0 index
                    if(value == isItCorrect[0] && count == 0) {
                        generation1.setIcon(wrongImg);
                        generation1.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 1) {
                        generation2.setIcon(wrongImg);
                        generation2.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 2) {
                        generation3.setIcon(wrongImg);
                        generation3.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 3) {
                        generation4.setIcon(wrongImg);
                        generation4.setVisible(true);
                    } else if(value == isItCorrect[0] && count == 4) {
                        generation5.setIcon(wrongImg);
                        generation5.setVisible(true);
                    }
                    //if value is the first index
                    if(value == isItCorrect[1] && count == 0) {
                        type11.setIcon(wrongImg);
                        type11.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 1) {
                        type12.setIcon(wrongImg);
                        type12.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 2) {
                        type13.setIcon(wrongImg);
                        type13.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 3) {
                        type14.setIcon(wrongImg);
                        type14.setVisible(true);
                    } else if(value == isItCorrect[1] && count == 4) {
                        type15.setIcon(wrongImg);
                        type15.setVisible(true);
                    }
                    //if value is the second index
                    if(value == isItCorrect[2] && count == 0) {
                        type21.setIcon(wrongImg);
                        type21.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 1) {
                        type22.setIcon(wrongImg);
                        type22.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 2) {
                        type23.setIcon(wrongImg);
                        type23.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 3) {
                        type24.setIcon(wrongImg);
                        type24.setVisible(true);
                    } else if(value == isItCorrect[2] && count == 4) {
                        type25.setIcon(wrongImg);
                        type25.setVisible(true);
                    }
                    //if value is the last index
                    if(value == isItCorrect[3] && count == 0) {
                        evolution1.setIcon(wrongImg);
                        evolution1.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 1) {
                        evolution2.setIcon(wrongImg);
                        evolution2.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 2) {
                        evolution3.setIcon(wrongImg);
                        evolution3.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 3) {
                        evolution4.setIcon(wrongImg);
                        evolution4.setVisible(true);
                    } else if(value == isItCorrect[3] && count == 4) {
                        evolution5.setIcon(wrongImg);
                        evolution5.setVisible(true);
                    }
                }
                x += 100;
                System.out.println(value);

            }
            y += 100;
            if(Arrays.equals(isItCorrect, correctComp)){
                System.out.println("You guessed the right pokemon!");
                feedback = "You guessed the right pokemon!";
                feedbackLabel.setText(feedback);
                won = true;
                break;
            }

            count++; 
        }
        if (!won) {
            System.out.println("out of guesses");
            feedback = "out of guesses";
            feedbackLabel.setText(feedback);
        } 
        pic.setIcon(img);
        pic.setVisible(true);
    }
 

    private void displayGUI() {
        frame = new JFrame("Pok-rdle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setLayout(null);

        feedbackLabel.setSize(300, 30);
        feedbackLabel.setLocation(200, 10);


        JLabel guess1 = new JLabel("Current guess");
        guess1.setSize(100, 30);
        guess1.setLocation(0, 250);
        JLabel guess2 = new JLabel("Guess 1:");
        guess2.setSize(100, 30);
        guess2.setLocation(0, 300);
        JLabel guess3 = new JLabel("Guess 2:");
        guess3.setSize(100, 30);
        guess3.setLocation(0, 350);
        JLabel guess4 = new JLabel("Guess 3:");
        guess4.setSize(100, 30);
        guess4.setLocation(0, 400);
        JLabel guess5 = new JLabel("Guess 4:");
        guess5.setSize(100, 30);
        guess5.setLocation(0, 450);
        JLabel guess6 = new JLabel("Guess 5:");
        guess6.setSize(100, 30);
        guess6.setLocation(0, 500);
        JLabel gen = new JLabel("Gen  |");
        gen.setSize(100, 30);
        gen.setLocation(320, 220);
        JLabel type1 = new JLabel("  Type 1  |");
        type1.setSize(100, 30);
        type1.setLocation(353, 220);
        JLabel type2 = new JLabel("  Type 2  |");
        type2.setSize(100, 30);
        type2.setLocation(410, 220);
        JLabel evol = new JLabel("  Evol");
        evol.setSize(100, 30);
        evol.setLocation(460, 220);


        label1.setSize(300, 30);
        label1.setLocation(120, 250);
        label2.setSize(300, 30);
        label2.setLocation(120, 300);
        label3.setSize(300, 30);
        label3.setLocation(120, 350);
        label4.setSize(300, 30);
        label4.setLocation(120, 400);
        label5.setSize(300, 30);
        label5.setLocation(120, 450);
        label6.setSize(300, 30);
        label6.setLocation(120, 500);
        pic.setSize(200, 200);
        pic.setLocation(85, 55);

        // corectPic = new JLabel(correctImg, JLabel.CENTER);
        // wrongPic = new JLabel(correctImg, JLabel.CENTER);
        // upPic = new JLabel(correctImg, JLabel.CENTER);
        // downPic = new JLabel(correctImg, JLabel.CENTER);

        generation1 = new JLabel(correctImg, JLabel.CENTER);
        generation1.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        generation1.setLocation(300, 280);
        generation1.setVisible(false);
        generation2 = new JLabel(correctImg, JLabel.CENTER);
        generation2.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        generation2.setLocation(300, 330);
        generation2.setVisible(false);
        generation3 = new JLabel(correctImg, JLabel.CENTER);
        generation3.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        generation3.setLocation(300, 380);
        generation3.setVisible(false);
        generation4 = new JLabel(correctImg, JLabel.CENTER);
        generation4.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        generation4.setLocation(300, 430);
        generation4.setVisible(false);
        generation5 = new JLabel(correctImg, JLabel.CENTER);
        generation5.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        generation5.setLocation(300, 480);
        generation5.setVisible(false);

        type11 = new JLabel(correctImg, JLabel.CENTER);
        type11.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type11.setLocation(360, 280);
        type11.setVisible(true);
        type12 = new JLabel(correctImg, JLabel.CENTER);
        type12.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type12.setLocation(360, 330);
        type12.setVisible(false);
        type13 = new JLabel(correctImg, JLabel.CENTER);
        type13.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type13.setLocation(360, 380);
        type13.setVisible(false);
        type14 = new JLabel(correctImg, JLabel.CENTER);
        type14.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type14.setLocation(360, 430);
        type14.setVisible(false);
        type15 = new JLabel(correctImg, JLabel.CENTER);
        type15.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type15.setLocation(360, 480);
        type15.setVisible(false);

        type21 = new JLabel(correctImg, JLabel.CENTER);
        type21.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type21.setLocation(420, 280);
        type21.setVisible(false);
        type22 = new JLabel(correctImg, JLabel.CENTER);
        type22.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type22.setLocation(420, 330);
        type22.setVisible(false);
        type23 = new JLabel(correctImg, JLabel.CENTER);
        type23.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type23.setLocation(420, 380);
        type23.setVisible(false);
        type24 = new JLabel(correctImg, JLabel.CENTER);
        type24.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type24.setLocation(420, 430);
        type24.setVisible(false);
        type25 = new JLabel(correctImg, JLabel.CENTER);
        type25.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        type25.setLocation(420, 480);
        type25.setVisible(false);

        evolution1 = new JLabel(correctImg, JLabel.CENTER);
        evolution1.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        evolution1.setLocation(480, 280);
        evolution1.setVisible(false);
        evolution2 = new JLabel(correctImg, JLabel.CENTER);
        evolution2.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        evolution2.setLocation(480, 330);
        evolution2.setVisible(false);
        evolution3 = new JLabel(correctImg, JLabel.CENTER);
        evolution3.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        evolution3.setLocation(480, 380);
        evolution3.setVisible(false);
        evolution4 = new JLabel(correctImg, JLabel.CENTER);
        evolution4.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        evolution4.setLocation(480, 430);
        evolution4.setVisible(false);
        evolution5 = new JLabel(correctImg, JLabel.CENTER);
        evolution5.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        evolution5.setLocation(480, 480);
        evolution5.setVisible(false);

        try {
            correctImg = new ImageIcon(ImageIO.read(new File("GuessPics/correct.png")));
            wrongImg = new ImageIcon(ImageIO.read(new File("GuessPics/wrong.png")));
            upImg = new ImageIcon(ImageIO.read(new File("GuessPics/up.png")));
            downImg = new ImageIcon(ImageIO.read(new File("GuessPics/down.png")));
        } catch (IOException e) {
            System.out.println("Read in image error...");
            feedback = "Read in image error...";
            feedbackLabel.setText(feedback);
        }
        // corectPic.setIcon(correctImg);
        // wrongPic.setIcon(wrongImg);
        // upPic.setIcon(upImg);
        // downPic.setIcon(downImg);




        JTextField textField = new JTextField("Guess here, then press enter.");
        textField.setColumns(50);
        textField.setSize(300, 40);
        textField.setVisible(true);
        textField.setLocation(100, 600);

        // // // Correct Pic
        // corectPic.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        // corectPic.setLocation(300, 230);

        // // // Wrong Pic
        // wrongPic.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        // wrongPic.setLocation(360, 230);

        // // // Up pic
        // upPic.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        // upPic.setLocation(420, 230);

        // // // Down Pic
        // downPic.setSize(GUESS_IMG_SIZE, GUESS_IMG_SIZE);
        // downPic.setLocation(480, 230);
    
    
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
    
                String text = textField.getText();
                label1.setText(text);
                
                if (label1.getText().equals(guessLabelText)) {
                    label1.setText(text);
                } else if (label2.getText().equals(guessLabelText)) {
                    label2.setText(text);
                } else if (label3.getText().equals(guessLabelText)) {
                    label3.setText(text);
                } else if (label4.getText().equals(guessLabelText)) {
                    label4.setText(text);
                } else if (label5.getText().equals(guessLabelText)) {
                    label5.setText(text);
                } else if (label6.getText().equals(guessLabelText)) {
                    label6.setText(text);
                } else {
                    //out of guesses
                    feedbackLabel.setText("OUT OF GUESSES");
                }
                
                

            }
        };
        textField.addActionListener(al);


        contentPane.add(feedbackLabel);
        contentPane.add(guess1);
        contentPane.add(guess2);
        contentPane.add(guess3);
        contentPane.add(guess4);
        contentPane.add(guess5);
        contentPane.add(guess6);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(textField);
        contentPane.add(pic);
        contentPane.add(gen);
        contentPane.add(type1);
        contentPane.add(type2);
        contentPane.add(evol);

        // Adding guess img
        // contentPane.add(upPic);
        // contentPane.add(corectPic);
        // contentPane.add(wrongPic);
        // contentPane.add(downPic);

        contentPane.add(generation1);
        contentPane.add(generation2);
        contentPane.add(generation3);
        contentPane.add(generation4);
        contentPane.add(generation5);

        contentPane.add(type11);
        contentPane.add(type12);
        contentPane.add(type13);
        contentPane.add(type14);
        contentPane.add(type15);

        contentPane.add(type21);
        contentPane.add(type22);
        contentPane.add(type23);
        contentPane.add(type24);
        contentPane.add(type25);

        contentPane.add(evolution1);
        contentPane.add(evolution2);
        contentPane.add(evolution3);
        contentPane.add(evolution4);
        contentPane.add(evolution5);



        frame.setContentPane(contentPane);
        frame.setSize(600, 800);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.requestFocus();
        frame.addWindowListener(null);
    }

    public void addGuessImage(JPanel jPanel, String[] isItCorrect) {
        System.out.println(isItCorrect);
        JLabel guess1 = new JLabel("This is a test");
        guess1.setSize(100, 30);
        guess1.setLocation(5, 650);
        jPanel.add(guess1);
    }

    public static void getPokePic(String randomPokemon) {
        if (randomPokemon.equals("bayleef")) {
            img = new ImageIcon(new ImageIcon("PokePics/bayleef.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("blastoise")) {
            img = new ImageIcon(new ImageIcon("PokePics/blastoise.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("blaziken")) {
            img = new ImageIcon(new ImageIcon("PokePics/blaziken.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("brionne")) {
            img = new ImageIcon(new ImageIcon("PokePics/brionne.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("bulbasaur")) {
            img = new ImageIcon(new ImageIcon("PokePics/bulbasaur.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("charizard")) {
            img = new ImageIcon(new ImageIcon("PokePics/charizard.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("charmander")) {
            img = new ImageIcon(new ImageIcon("PokePics/charmander.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("charmeleon")) {
            img = new ImageIcon(new ImageIcon("PokePics/charmeleon.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("chikorita")) {
            img = new ImageIcon(new ImageIcon("PokePics/chikorita.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("chimchar")) {
            img = new ImageIcon(new ImageIcon("PokePics/chimchar.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("cinderace")) {
            img = new ImageIcon(new ImageIcon("PokePics/cinderace.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("combusken")) {
            img = new ImageIcon(new ImageIcon("PokePics/combusken.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("croconaw")) {
            img = new ImageIcon(new ImageIcon("PokePics/croconaw.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("cyndaquil")) {
            img = new ImageIcon(new ImageIcon("PokePics/cyndaquil.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("datrix")) {
            img = new ImageIcon(new ImageIcon("PokePics/dartrix.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("decidueye")) {
            img = new ImageIcon(new ImageIcon("PokePics/decidueye.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("dewott")) {
            img = new ImageIcon(new ImageIcon("PokePics/dewott.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("drizzile")) {
            img = new ImageIcon(new ImageIcon("PokePics/drizzile.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("emboar")) {
            img = new ImageIcon(new ImageIcon("PokePics/emboar.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("empoleon")) {
            img = new ImageIcon(new ImageIcon("PokePics/empoleon.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("feraligatr")) {
            img = new ImageIcon(new ImageIcon("PokePics/feraligatr.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("grookey")) {
            img = new ImageIcon(new ImageIcon("PokePics/grookey.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("grotle")) {
            img = new ImageIcon(new ImageIcon("PokePics/grotle.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("grovyle")) {
            img = new ImageIcon(new ImageIcon("PokePics/grovyle.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("incineroar")) {
            img = new ImageIcon(new ImageIcon("PokePics/incineroar.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("infernape")) {
            img = new ImageIcon(new ImageIcon("PokePics/infernape.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("inteleon")) {
            img = new ImageIcon(new ImageIcon("PokePics/inteleon.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("ivysaur")) {
            img = new ImageIcon(new ImageIcon("PokePics/ivysaur.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("litten")) {
            img = new ImageIcon(new ImageIcon("PokePics/litten.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("marshtomp")) {
            img = new ImageIcon(new ImageIcon("PokePics/marshtomp.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("meganium")) {
            img = new ImageIcon(new ImageIcon("PokePics/meganium.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        else if (randomPokemon.equals("monferno")) {
            img = new ImageIcon(new ImageIcon("PokePics/monferno.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("mudkip")) {
            img = new ImageIcon(new ImageIcon("PokePics/mudkip.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("oshawott")) {
            img = new ImageIcon(new ImageIcon("PokePics/oshawott.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("pichu")) {
            img = new ImageIcon(new ImageIcon("PokePics/pichu.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("pignite")) {
            img = new ImageIcon(new ImageIcon("PokePics/pignite.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("pikachu")) {
            img = new ImageIcon(new ImageIcon("PokePics/pikachu.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("piplup")) {
            img = new ImageIcon(new ImageIcon("PokePics/piplup.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("popplio")) {
            img = new ImageIcon(new ImageIcon("PokePics/popplio.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("primarina")) {
            img = new ImageIcon(new ImageIcon("PokePics/primarina.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("prinplup")) {
            img = new ImageIcon(new ImageIcon("PokePics/prinplup.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("raboot")) {
            img = new ImageIcon(new ImageIcon("PokePics/raboot.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("raichu")) {
            img = new ImageIcon(new ImageIcon("PokePics/raichu.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("rillaboom")) {
            img = new ImageIcon(new ImageIcon("PokePics/rillaboom.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("rowlet")) {
            img = new ImageIcon(new ImageIcon("PokePics/rowlet.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("samurott")) {
            img = new ImageIcon(new ImageIcon("PokePics/samurott.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("sceptile")) {
            img = new ImageIcon(new ImageIcon("PokePics/sceptile.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("scorbunny")) {
            img = new ImageIcon(new ImageIcon("PokePics/scorbunny.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("serperior")) {
            img = new ImageIcon(new ImageIcon("PokePics/serperior.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("servine")) {
            img = new ImageIcon(new ImageIcon("PokePics/servine.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("snivy")) {
            img = new ImageIcon(new ImageIcon("PokePics/snivy.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("squirtle")) {
            img = new ImageIcon(new ImageIcon("PokePics/squirtle.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("swampert")) {
            img = new ImageIcon(new ImageIcon("PokePics/swampert.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("tepig")) {
            img = new ImageIcon(new ImageIcon("PokePics/tepig.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("thwackey")) {
            img = new ImageIcon(new ImageIcon("PokePics/thwackey.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("torchic")) {
            img = new ImageIcon(new ImageIcon("PokePics/torchic.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("torracat")) {
            img = new ImageIcon(new ImageIcon("PokePics/torracat.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("torterra")) {
            img = new ImageIcon(new ImageIcon("PokePics/torterra.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("totodile")) {
            img = new ImageIcon(new ImageIcon("PokePics/totodile.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("treecko")) {
            img = new ImageIcon(new ImageIcon("PokePics/treecko.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("turtwig")) {
            img = new ImageIcon(new ImageIcon("PokePics/turtwig.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("chespin")) {
            img = new ImageIcon(new ImageIcon("PokePics/chespin.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("quilladin")) {
            img = new ImageIcon(new ImageIcon("PokePics/quilladin.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("chesnaught")) {
            img = new ImageIcon(new ImageIcon("PokePics/chesnaught.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("fennekin")) {
            img = new ImageIcon(new ImageIcon("PokePics/fennekin.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("braixen")) {
            img = new ImageIcon(new ImageIcon("PokePics/braixen.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("delphox")) {
            img = new ImageIcon(new ImageIcon("PokePics/delphox.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("froakie")) {
            img = new ImageIcon(new ImageIcon("PokePics/froakie.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("frogadier")) {
            img = new ImageIcon(new ImageIcon("PokePics/frogadier.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
        if (randomPokemon.equals("greninja")) {
            img = new ImageIcon(new ImageIcon("PokePics/greninja.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        }
    }


    // public class Frame extends JFrame{
    //     JFrame frame;

    //     Frame(JTextField input, JTextPane display) {
    //         JFrame frame = new JFrame("Pok-rdle");
    //         // Canvas canvas = new Canvas();
            
    //         // frame.add(canvas);
    //         // frame.setBackground(Color.LIGHT_GRAY);
    //         Icon pokemon = new ImageIcon("POKEMON");
    //         // frame.setIconImage(pokemon);
    //         frame.setBounds(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-300, 0, 600, 800);

            
    //         input.setSize(100, 30);
    //         input.setLocation(300, 300);
    //         display.setSize(100, 50);
    //         display.setLocation(300, 400);
    //         frame.add(input);
    //         frame.add(display);
    //         frame.setContentPane(new MainPanel());

    //         frame.setVisible(true);
    //     }
    // }

    // class MainPanel extends JPanel {


    //     MainPanel() {
    //     setOpaque(true);
    //     setBackground(Color.LIGHT_GRAY);
    //   }
    
    //     @Override
    //     protected void paintComponent(Graphics g) {
    //         super.paintComponent(g);
    //         g.setFont(g.getFont().deriveFont(20.0F));
    //         g.setColor(Color.BLUE);
    //         g.drawString("Pok-rlde", 265, 50);

    //         g.setColor(Color.GRAY);
    //         g.drawString(message, 170, 100);


            
    //         g1 = (Graphics) g;
    //         g1.drawOval(150, 400, 50, 50);
    //         g1.fillOval(150, 400, 50, 50);
    //         g1.setColor(Color.GREEN);
    //         g2 = (Graphics) g;
    //         g2.drawOval(250, 400, 50, 50);
    //         g2.fillOval(250, 400, 50, 50);
    //         g2.setColor(Color.RED);
    //         g3 = (Graphics) g;
    //         g3.drawOval(350, 400, 50, 50);
    //         g3.fillOval(350, 400, 50, 50);
    //         g3.setColor(Color.BLUE);
    //         g4 = (Graphics) g;
    //         g4.drawOval(450, 400, 50, 50);
    //         g4.fillOval(450, 400, 50, 50);
    //         g4.setColor(Color.GREEN);
    //   }
    // }

}