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

    static Boolean won = false;

    static String feedback = "Welcome to pok-rdle";
    static JLabel feedbackLabel = new JLabel(feedback);
    static ImageIcon img;
    static String randomPokemon;

    static String guessLabelText = "__";
    static JLabel label1 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label2 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label3 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label4 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label5 = new JLabel(guessLabelText , JLabel.CENTER);
    static JLabel label6 = new JLabel(guessLabelText , JLabel.CENTER);

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
        String[] correctValues = pokemonMap.get(randomPokemon).split(",");

        // try {
        //     img = new ImageIcon(ImageIO.read(new File("blastoise.png")));
        // } catch (IOException e) {
        //     System.out.println("Read in image error...");
        //     feedback = "Read in image error...";
        //     feedbackLabel.setText(feedback);
        // }
        // pic.setIcon(img);
        
        
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        
        int count = 0;
        String[] correctComp = {"equal", "true", "true", "equal"};
        int y = 150;
        System.out.println(pokemonMap);
        System.out.println(randomPokemon);

        while(count < 5){
            System.out.println("Guess a Pokemon");
            feedback = "Guess a Pokemon";
            feedbackLabel.setText(feedback);

            String guess = ")";
            if (count == 0) {
                while(label2.getText().equals("__")) {
                    System.out.println("waiting.");
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

        
        JLabel pic = new JLabel(img, JLabel.CENTER);
        // img = new ImageIcon(ImageIO.read(new File("PokePics/blastoise.png")));
        String pokeLink = "PokePics/" + randomPokemon + ".png";
        img = new ImageIcon(new ImageIcon("PokePics/blastoise.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        pic.setIcon(img);



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





        JTextField textField = new JTextField("Guess here, then press enter.");
        textField.setColumns(50);
        textField.setSize(300, 40);
        textField.setVisible(true);
        textField.setLocation(100, 600);
    
    
    
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

        frame.setContentPane(contentPane);
        frame.setSize(600, 800);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.requestFocus();
        frame.addWindowListener(null);
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