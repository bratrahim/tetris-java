/**
 * Created by Tomas Kovtun 1502245 on 12/1/2016.
 * Blueprint of Tetris grid, extends JComponent
**/

import javax.swing.*;
import java.awt.*;
// import all the Colors
import static java.awt.Color.*;

public class TetrisView extends JComponent
{
    int score=0;
    boolean gameOver=false;
    //setting colors list
    private static Color[] colors =
            {black, green, blue, red,
                    yellow, magenta, pink, cyan,green, blue, red,
                    yellow, magenta, pink, cyan};
    private int[][] a;
    private int w, h;
    private static int size = 20;
    private TetrisShape activeShape;
    //initializing
    TetrisView(int[][] a)
    {
        this.a = a;
        w = a.length;
        h = a[0].length;
    }

public void paintComponent(Graphics g)
{
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                    g.setColor(colors[a[i][j]]);
                    g.fill3DRect(i * size, j * size,
                            size, size, true);
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(w * size, h * size);
    }
    //checks if after nex timer tick the falling shape will collide
    boolean nextGridCollide()
    {
        boolean collision=false;
        for(int x=0;x<10;x++)
            for(int y=19;y>=0;y--)
            {
                if(a[x][y]<=6&&a[x][y]!=0)
                {
                    if(y==19||a[x][y+1]>6)
                        collision=true;
                }
            }
        return collision;
    }
    //generate a grid after next timer tick
    void nextGrid()
    {
        for(int x=0;x<10;x++)
            for(int y=19;y>=0;y--)
            {
                if(a[x][y]<=6&&a[x][y]!=0)
                {
                    int temp= a[x][y];
                    a[x][y]=0;
                    a[x][y+1]=temp;

                }
            }
        activeShape.axisY++;
        repaint();
    }
    //add new shape to the grid
    void addShape(TetrisShape tetrisShape)
    {
        gameOver=false;
        activeShape=tetrisShape;
        for(int i=3;i<7;i++)
            for(int j=0;j<4;j++)
            {
                int bitOfTetrisShape=tetrisShape.shapeMatrix[i-3][j];
                if(bitOfTetrisShape!=0)
                {
                    if(a[i][j]!=0)
                    {
                        gameOver=true;
                        break;
                    }
                }
                if(gameOver) break;
            }
            if(!gameOver)
        for(int i=3;i<7;i++)
            for(int j=0;j<4;j++)
            {
                int bitOfTetrisShape=tetrisShape.shapeMatrix[i-3][j];
                if(bitOfTetrisShape!=0)
                {
                    a[i][j]=bitOfTetrisShape;
                }
            }
    }
    //turn all collided shapes into one construction
    void attachConstruction()
    {
        for(int x=0;x<10;x++)
            for(int y=19;y>=0;y--)
            {
                if(a[x][y]!=0&&a[x][y]<7)
                {
                    a[x][y]+=7;
                }
            }
    }
    void wipe()
    {
        for(int x=0;x<10;x++)
            for(int y=0;y<20;y++)
            {
                a[x][y]=0;
                score=0;
            }
        repaint();
    }
    //move the active shape to the left
    void moveLeft()
    {
        int yo=activeShape.axisY;
        boolean collision = false;
        for(int x=0;x<10;x++)
            for(int y=19;y>=yo;y--)
            {
                if(a[x][y]<=6&&a[x][y]!=0)
                {
                    if(x==0||a[x-1][y]>6)
                    {
                        collision=true;
                        break;
                    }

                }
            }
        if(!collision)
        {
            for(int x=0;x<10;x++)
                for(int y=19;y>=0;y--)
                {
                    if(a[x][y]<=6&&a[x][y]!=0)
                    {
                        int temp=a[x][y];
                        a[x][y]=0;
                        a[x-1][y]=temp;
                    }
                }
            activeShape.axisX--;
            this.repaint();
        }
    }
    //move the active shape to the right
    void moveRight()
    {
        boolean collision = false;
        for(int x=9;x>-1;x--)
            for(int y=19;y>=0;y--)
            {
                if(a[x][y]<=6&&a[x][y]!=0)
                {
                    if(x==9||a[x+1][y]>6)
                    {
                        collision=true;
                        break;
                    }
                }
            }
        if(!collision)
        {
            for(int x=9;x>=0;x--)
            for(int y=19;y>=0;y--)
            {
                if(x!=9&&a[x][y]<=6&&a[x][y]!=0)
                {
                    int temp=a[x][y];
                    a[x][y]=0;
                    a[x+1][y]=temp;
                }
            }
            activeShape.axisX++;
            this.repaint();
        }
    }
    //check if any of lines is full
    private int checkIfAnyLineIsFull(int[][] matrix)
    {
        for(int i=0;i<20;i++)
        {
            int counter=0;
            for(int j=0;j<10;j++)
            {
                if(matrix[j][i]>6)
                {
                    counter++;
                }
            }
            if(counter==10) return i;
        }
        return -1;
    }
    //remove a construction line if full
    void removeFullLine()
    {
        int fullLineIndex=checkIfAnyLineIsFull(a);
        if(fullLineIndex!=-1)
        {
            for(int i=0;i<10;i++)
                a[i][fullLineIndex]=0;
                for(int x=0;x<10;x++)
                    for(int y=fullLineIndex-1;y>=0;y--)
                    {
                        if(a[x][y]>6)
                        {
                            int temp= a[x][y];
                            a[x][y]=0;
                            a[x][y+1]=temp;
                        }
                    }
            score+=10;
        }
    }
    //rotate active shape if possible
    void rotateActiveShape()
    {
        int x=0;
        int y;
        boolean collide=false;
        int[][] shapeMatrix=TetrisShape.rotateShape(activeShape.getShapeMatrix());
        //check if does not collide after the rotation
        for(int i=activeShape.axisX;i<activeShape.axisX+4;i++)
        {
            y=0;
            for(int j=activeShape.axisY;j<activeShape.axisY+4;j++)
            {
                try{
                    if(a[i][j]>6)
                    {
                        collide=true;
                    }
                    y++;
                }
                catch (ArrayIndexOutOfBoundsException e) {collide=true;}
            }
            x++;
        }
        x=0;
        if (!collide)
        {
            for(int i=activeShape.axisX;i<activeShape.axisX+4;i++)
            {
                y=0;
                for(int j=activeShape.axisY;j<activeShape.axisY+4;j++)
                {
                    if(a[i][j]<7) a[i][j]=0;
                    if(shapeMatrix[x][y]>0)
                        a[i][j]=shapeMatrix[x][y];
                    y++;
                }
                x++;
                activeShape.setShapeMatrix(shapeMatrix);
            }
            repaint();
        }
    }
}
