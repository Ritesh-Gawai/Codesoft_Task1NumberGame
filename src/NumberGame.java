
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class NumberGame extends JFrame implements ActionListener {
    JButton next;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 10;

    private int roundsWon;
    private int totalAttempts;


    NumberGame() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(Color.white);
        this.setSize(630, 430);
        this.setLocation(280, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(null);
        this.setVisible(true);
        //this.setBackground(Color.white);
        this.add(panel);

        ImageIcon ic = new ImageIcon("thinkin1.png");
        JLabel label = new JLabel(ic);
        label.setBounds(-10, 50, 250, 300);
        panel.add(label);

        JLabel headline = new JLabel("Welcome To The Number Guessing Game!!");
        headline.setBounds(80, 40, 450, 30);
        headline.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        panel.add(headline);


        JLabel paragraph = new JLabel("I've selected a number between 1 and 100,");
        paragraph.setBounds(260, 110, 450, 25);
        paragraph.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        panel.add(paragraph);


        JLabel paragraph1 = new JLabel("can you guess what it is");
        paragraph1.setBounds(280, 140, 450, 25);
        paragraph1.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        panel.add(paragraph1);


        JLabel Enter = new JLabel("Let's Start The Game");
        Enter.setBounds(260, 200, 450, 25);
        Enter.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        panel.add(Enter);

        next = new JButton("Next");
        next.setBounds(320, 260, 100, 30);
        next.setFocusable(false);
        next.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        next.setBackground(new Color(88, 88, 217));
        next.addActionListener(this);
        panel.add(next);


        //NumberGui();
    }
    // public void NumberGui(){
    // JLabel thinking=new JLabel(loadImage("src/image/thinkin1.png"));
    //thinking.setBounds(0,70,200,300);
    //thinking.setBackground(Color.white);
    //add(thinking);

    // }
 /*   private ImageIcon loadImage(String resourcePath){
        try{
            // read the image file from the path given
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // returns an image icon so that our component can render it
            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }*/
    private void playRound() {
        int randomNumber = generateRandomNumber();
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            String guessString = JOptionPane.showInputDialog(this, "Enter your guess (between 1 and 100):");
            if (guessString == null) {
                // User canceled input dialog
                return;
            }

            try {
                int guess = Integer.parseInt(guessString);
                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100.",
                            "Invalid Guess", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                attempts++;
                totalAttempts++;

                if (guess == randomNumber) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You've guessed the correct number in " + attempts + " attempts.",
                            "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                    roundsWon++;
                    break;
                } else if (guess < randomNumber) {
                    JOptionPane.showMessageDialog(this, "Too low! Try again.",
                            "Incorrect Guess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Too high! Try again.",
                            "Incorrect Guess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.",
                        "Invalid Guess", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Check if user exceeded maximum attempts
        if (attempts >= MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(this, "Sorry, you've used all your attempts. The correct number was: " + randomNumber,
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play another round?", "New Round", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            playRound();
        } else {
            displayScore();
        }
    }

    // Generate a random number within the specified range
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    // Display the user's score
    private void displayScore() {
        JOptionPane.showMessageDialog(this, "Rounds won: " + roundsWon + "\nTotal attempts: " + totalAttempts,
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
        new NumberGame();
    }
    public static void main(String args[]) {

        new NumberGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
            setVisible(false);
            playRound();



        }
    }
}