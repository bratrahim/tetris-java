import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomas Kovtun on 12/5/2016.
 * Blueprint for a tetris frame
 */
public class TetrisFrame extends JFrame {
    private JLabel label;
    private int score=0;
    //constructor
    TetrisFrame(Component comp, String title) {
        super(title);
        JPanel main=new JPanel(new GridLayout());
        JPanel gameView=new JPanel(new GridLayout());
        gameView.add(comp);
        JPanel panel=new JPanel();
        label=new JLabel("");
        label.setText(convertToMultiline("Score: "+score+"\n Press 'R' to restart"));
        label.setFont(new Font("Impact", Font.PLAIN, 20));
        panel.add(label);
        main.add(gameView);
        main.add(panel);
        add(main);
        pack();
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }
    //set score on the score boad
    void setScore(int score)
    {
        this.score=score;
        label.setText(convertToMultiline("Score: "+score+"\n Press 'R' to restart"));
    }
    //set the game over message on the score board
    void setGameOver()
    {
        label.setText(convertToMultiline("Game over! \n Your score is "+score+"\n Press 'R' to restart"));
    }
    //multiline adapter for a label
    private static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

}

