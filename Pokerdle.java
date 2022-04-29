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
import java.awt.Graphics.*;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Pokerdle
 */
public class Pokerdle {
    Graphics g1;

    Graphics g2;

    Graphics g3;

    Graphics g4;

    public static void main(String[] args) throws IOException {
        JTextField userInput = new JTextField("Enter input here", 1);
        JTextPane inputDisplay = new JTextPane();
        Pokerdle pokerdle = new Pokerdle();
        Frame frame = pokerdle.new Frame(userInput, inputDisplay);
        

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
        String randomPokemon = pokemon.get(r.nextInt(upperBound));
        String[] correctValues = pokemonMap.get(randomPokemon).split(",");

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("PokePics/"+randomPokemon+".webp"));
        } catch (IOException e) {
            System.out.println("Read in image error...");
        }
        frame.setIconImage(img);
        
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        
        int count = 0;
        String[] correctComp = {"equal", "true", "true", "equal"};
        int y = 150;
        System.out.println(pokemonMap);
        System.out.println(randomPokemon);

        while(count < 5){
            System.out.println("Guess a Pokemon");

            String guess = sc.nextLine();  // Read user input
            
            while(pokemonMap.get(guess.toLowerCase()) == null){
                System.out.println("Not a valid guess. Please guess another Pokemon");
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
            int x = 150;
            for(String value : isItCorrect){
                
                if(value == "equal"){
                    Color colorBall = Color.GREEN;

                }
                if(value == "more"){
                    Color colorBall = Color.BLUE;
                }
                if(value == "less"){
                    Color colorBall = Color.BLUE;
                }
                if(value == "true"){
                    Color colorBall = Color.GREEN;
                }
                if(value == "false"){
                    Color colorBall = Color.RED;
                }
                x += 100;
                System.out.println(value);

            }
            y += 100;
            if(Arrays.equals(isItCorrect, correctComp)){
                System.out.println("You guessed the right pokemon!");
                break;
            }

            count++; 
        }
        System.out.println("out of guesses");
    }
 

    public class Frame extends JFrame{
        JFrame frame;

        Frame(JTextField input, JTextPane display) {
            JFrame frame = new JFrame("Pok-rdle");
            // Canvas canvas = new Canvas();
            
            // frame.add(canvas);
            // frame.setBackground(Color.LIGHT_GRAY);
            Icon pokemon = new ImageIcon("POKEMON");
            // frame.setIconImage(pokemon);
            frame.setBounds(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-300, 0, 600, 800);

            input.setSize(100, 30);
            input.setLocation(300, 300);
            frame.add(input);
            frame.add(display);

            frame.setContentPane(new MainPanel());

            frame.setVisible(true);
        }
    }

    class MainPanel extends JPanel
    {
      MainPanel()
      {
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
      }
    
      @Override
      protected void paintComponent(Graphics g)
      {
        super.paintComponent(g);
        g.setFont(g.getFont().deriveFont(20.0F)); //unneccessary?
        g.setColor(Color.BLUE);
        g.drawString("Sample text", 50, 50);

        
        g1 = (Graphics) g;
        g1.drawOval(150, 400, 50, 50);
        g1.fillOval(150, 400, 50, 50);
        g1.setColor(Color.GREEN);
        g2 = (Graphics) g;
        g2.drawOval(250, 400, 50, 50);
        g2.fillOval(250, 400, 50, 50);
        g2.setColor(Color.RED);
        g3 = (Graphics) g;
        g3.drawOval(350, 400, 50, 50);
        g3.fillOval(350, 400, 50, 50);
        g3.setColor(Color.BLUE);
        g4 = (Graphics) g;
        g4.drawOval(450, 400, 50, 50);
        g4.fillOval(450, 400, 50, 50);
        g4.setColor(Color.GREEN);
      }
    }

}