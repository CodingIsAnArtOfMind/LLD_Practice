package io.lld_practice.connectfour.entity;

public class Board {
public static final int row =6;
public static final int col =7;
GridColor[][] gridColor;

public Board() {
    gridColor = new GridColor[row][col];
}

    public int canWePlace(int column,Player player) {
        if (column < 0 || column >= col) {
            return -1;
        }
        for(int i=row-1;i>=0;i--) {
            if(gridColor[i][column]==null){
                gridColor[i][column]=player.getPlayerColor();
                return i;
            }
        }
        return -1;
    }

    public boolean checkWinner(Player currentPlayer, int row, int column) {
    int[][] dir = new int[][]{
            {0,1},
            {1,0},
            {1,1},
            {1,-1},
    };
    for (int[] dire : dir) {
        int count=1;
        count+=checkWinnerInDirection(row,column,dire[0],dire[1],currentPlayer);
        count+=checkWinnerInDirection(row,column,-dire[0],-dire[1],currentPlayer);
        if (count>=4) {
            return true;
        }
    }
        return false;
    }

    private int checkWinnerInDirection(int row, int column, int dirx, int diry, Player playerColor) {
       int count = 0;
        int r=row+dirx;
        int c=column+diry;
       while (checkInBonds(r,c) && gridColor[r][c]==playerColor.getPlayerColor()){
           count++;
           r=r+dirx;
           c=c+diry;
           
       }
       return count;
    }

    private boolean checkInBonds(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }

    public boolean isBoardFull() {
    for(int i=0;i<row;i++) {
        for(int j=0;j<col;j++) {
            if(gridColor[i][j]==null){
                return false;
            }
        }
    }
    return true;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GridColor cell = gridColor[i][j];
                char ch = cell == null ? '.' : (cell == GridColor.RED ? 'R' : 'B');
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < col; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
