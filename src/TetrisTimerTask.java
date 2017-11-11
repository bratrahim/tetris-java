import java.util.TimerTask;

/**
 * Created by Tomas Kovtun on 12/1/2016.
 * Blueprint of tetris timer task
 */
class TetrisTimerTask extends TimerTask {
    private TetrisView tv;
    private boolean newShape=true;
    private TetrisFrame eF;
    private TetrisShape newShapeM=new TetrisShape();
    private TetrisShape tetrisShape;
    //constructor
    TetrisTimerTask(TetrisView tv, TetrisFrame eF)
    {
        this.tv=tv;
        this.eF=eF;
    }
    @Override
    //on each rotation of the timer
    public void run()
    {
        if(newShape)
        {
            tetrisShape=newShapeM;
            newShapeM=new TetrisShape();
            tv.addShape(tetrisShape);
            newShape=false;
            eF.repaint();
        }
        else
        {
            if(!tv.nextGridCollide())
                tv.nextGrid();
            else
            {
                tv.attachConstruction();
                newShape=true;
            }
            tv.removeFullLine();
            //if the game continues
            if(!tv.gameOver)
            eF.setScore(tv.score);
        }
        //if game is over
        if(tv.gameOver) eF.setGameOver();
        tv.repaint();
        eF.repaint();
    }
}
