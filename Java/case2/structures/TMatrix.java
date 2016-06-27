package structures;

public class TMatrix {
    private int[][] matrix;

    public TMatrix(int rows, int columns){
        matrix = new int[rows][columns];
    }

    public int getRowsCount(){
        return matrix.length;
    }

    public int getColumnsCount(){
        return matrix[0].length;
    }

    public int getElement(int row, int column){
        return matrix[row][column];
    }

    public void setElement(int row, int column, int element){
        matrix[row][column] = element;
    }

    public void multiply(TMatrix multiplier){
        int myRowsCount = getRowsCount();
        int myColumnsCount = getColumnsCount();
        int multiplierColumnsCount = multiplier.getColumnsCount();
        int multiplierRowsCount = multiplier.getRowsCount();

        if (myColumnsCount != multiplierRowsCount){
            throw new IllegalArgumentException();
        }

        int[][] result = new int[myRowsCount][multiplierColumnsCount];

        for (int i = 0; i < myRowsCount; i++){
            for (int j = 0; j < multiplierColumnsCount; j++){
                for (int k = 0; k < myColumnsCount; k++){
                    result[i][j] += getElement(i, k) * multiplier.getElement(k, j);
                }
            }
        }

        matrix = result;
    }

    public void transpose(){
        int[][] newMatrix = new int[getColumnsCount()][getRowsCount()];
        for (int i = 0; i < getRowsCount(); i++){
            for (int j = 0; j < getColumnsCount(); j++ ){
                newMatrix[j][i] = matrix[i][j];
            }
        }

        matrix = newMatrix;
    }



}
