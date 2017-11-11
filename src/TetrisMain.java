import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
/**
 * Created by Tomas Kovtun on 11/27/2016.
 * Blueprint of the main tetris controller
 */
public class TetrisMain {
    private static Timer timer;
    private final int w = 10;
    private final int h = 20;
    private int[][] a;
    private TetrisView tv;
    private TetrisFrame tetrisFrame;
    //constructor
    private TetrisMain() {
        a = new int[w][h];
        tv = new TetrisView(a);
        tetrisFrame = new TetrisFrame(tv, "Tomas Kovtun 1502245");
        tetrisFrame.setVisible(true);
        tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetrisFrame.setFocusable(true);
        tetrisFrame.requestFocusInWindow();
        //mouse listeners
        tv.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                if(SwingUtilities.isLeftMouseButton(me))
                {
                    tv.moveLeft();
                }
                if(SwingUtilities.isRightMouseButton(me))
                {
                    tv.moveRight();
                }
                if(SwingUtilities.isMiddleMouseButton(me))
                {
                    tv.rotateActiveShape();
                }
            }
        });
        //keyboard listeners
        tetrisFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myKeyEvt(e);

            }
            private void myKeyEvt(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT) {
                    tv.moveRight();
                } else if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT) {
                    tv.moveLeft();

                }
                else if (key == KeyEvent.VK_KP_DOWN|| key == KeyEvent.VK_DOWN) {
                    if(!tv.nextGridCollide())tv.nextGrid();

                }
                else if (key == KeyEvent.VK_SPACE) {
                    if(!tv.nextGridCollide())tv.rotateActiveShape();

                }
                else if (key == KeyEvent.VK_R) {
                    timer.cancel();
                    timer=new Timer(true);
                    timer.schedule(new TetrisTimerTask(tv,tetrisFrame),0,200);
                    tv.wipe();
                    tetrisFrame.setScore(0);
                    }
        }
        });
    }

    public static void main(String [] args)
    {
        TetrisMain tetrisMain=new TetrisMain();
        tetrisMain.timer=new Timer(true);
        TetrisTimerTask TTT=new TetrisTimerTask(tetrisMain.tv, tetrisMain.tetrisFrame);
        tetrisMain.timer.schedule(TTT,0,200);
        tetrisMain.tv.wipe();
    }
}
