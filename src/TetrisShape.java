import java.util.Random;

/**
 * Created by Tomas Kovtun on 12/1/2016.
 * Blueprint of a Tetris shape
 */
class TetrisShape {
    int [][] shapeMatrix=new int[4][4];
    private int shapeIndex;
    private int colorIndex;
    int axisY=0;
    int axisX=3;
    //initializing
    TetrisShape()
    {
        //generating a random shape of random color
        Random shapeRandom= new Random();
        shapeIndex=shapeRandom.nextInt(6);
        Random colorRandom= new Random();
        colorIndex=colorRandom.nextInt(6)+1;
        switch (shapeIndex)
        {
            //J tetris shape
            case 0:
            {
                shapeMatrix[1][0]=colorIndex;
                shapeMatrix[2][0]=colorIndex;
                shapeMatrix[1][1]=colorIndex;
                shapeMatrix[1][2]=colorIndex;
                break;
            }
            //L tetris shape
            case 1:
            {
                shapeMatrix[1][0]=colorIndex;
                shapeMatrix[2][0]=colorIndex;
                shapeMatrix[2][1]=colorIndex;
                shapeMatrix[2][2]=colorIndex;
                break;
            }
            //S tetris shape
            case 2:
            {
                shapeMatrix[1][0]=colorIndex;
                shapeMatrix[1][1]=colorIndex;
                shapeMatrix[2][1]=colorIndex;
                shapeMatrix[2][2]=colorIndex;
                break;
            }
            //I tetris shape
            case 3:
            {
                shapeMatrix[1][0]=colorIndex;
                shapeMatrix[1][1]=colorIndex;
                shapeMatrix[1][2]=colorIndex;
                shapeMatrix[1][3]=colorIndex;
                break;
            }
            //T tetris shape
            case 4:
            {
                shapeMatrix[1][0]=colorIndex;
                shapeMatrix[1][1]=colorIndex;
                shapeMatrix[1][2]=colorIndex;
                shapeMatrix[2][1]=colorIndex;
                break;
            }
            //Z tetris shape
            case 5:
            {
                shapeMatrix[1][1]=colorIndex;
                shapeMatrix[1][2]=colorIndex;
                shapeMatrix[2][1]=colorIndex;
                shapeMatrix[2][2]=colorIndex;
                break;
            }
        }
    }
    //rotates received 4x4 matrix by 90 degrees clockwise and outputs the result
    static int[][] rotateShape(int[][] shapeMatrix)
    {
        int temp = shapeMatrix[0][3];
        //rotate corners
        shapeMatrix[0][3]=shapeMatrix[0][0];
        shapeMatrix[0][0]=shapeMatrix[3][0];
        shapeMatrix[3][0]=shapeMatrix[3][3];
        shapeMatrix[3][3]=temp;
        //rotate adjacent to top left corner
        temp= shapeMatrix[1][3];
        shapeMatrix[1][3]=shapeMatrix[0][1];
        shapeMatrix[0][1]=shapeMatrix[2][0];
        shapeMatrix[2][0]=shapeMatrix[3][2];
        shapeMatrix[3][2]=temp;
        //rotate adjacent to top right corner
        temp=shapeMatrix[2][3];
        shapeMatrix[2][3]=shapeMatrix[0][2];
        shapeMatrix[0][2]=shapeMatrix[1][0];
        shapeMatrix[1][0]=shapeMatrix[3][1];
        shapeMatrix[3][1]=temp;
        //rotate middle square
        temp=shapeMatrix[1][2];
        shapeMatrix[1][2]=shapeMatrix[1][1];
        shapeMatrix[1][1]=shapeMatrix[2][1];
        shapeMatrix[2][1]=shapeMatrix[2][2];
        shapeMatrix[2][2]=temp;
        return shapeMatrix;
    }
    //returns 4x4 matrix of the shape
    int[][] getShapeMatrix()
    {
        return shapeMatrix;
    }
    //sets 4x4 matrix of the shape
    void setShapeMatrix(int [][] array)
    {
        shapeMatrix=array;
    }
}
