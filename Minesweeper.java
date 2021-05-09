
/**
 * Linda Turkmen
 * This program generates a minesweeper board
 */

import java.util.Random;


public class Minesweeper {
    boolean [][] mineField; //Verilen mayin sayisi kullanilarak uretilen mayinli tarla
    int rows; //Satir sayisi
    int cols; //Sutun sayisi
    int numMines; //Mayin sayisi




    Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        mineField = new boolean[rows][cols];

        generateBoard();
    }

    void generateBoard() { //mayinlari rastgele pozisyona yerlestirecek

        int mine = numMines;
        if(mine > rows * cols) {
            mine = rows * cols;
        }
        while(mine > 0) {
            Random index = new Random();
            int x = index.nextInt(rows);
            int y = index.nextInt(cols);

            if(mineField[x][y] == false) {
                mineField[x][y] = true;
                --mine;
            }
        }

    }

    //Bu metod mineFieldde komsu pozisyonlarda kac tane mayin oldugunu gosteren clues arrayinin elemanlarini belirler.

    int [][] generateClues() {
        int [][] clues = new int[rows][cols];
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(mineField[r][c] == false) {
                    clues[r][c] = countMines(r, c);
                } else {
                    clues[r][c] = -1;
                }
            }
        }
        return clues;
    }

    int countMines (int r, int c) { // (r, c)nin etrafindaki mayin sayisini return eder
        int countMine = 0;
        for(int x = -1; r + x < r + 2; ++x) {
            if((r + x < 0 ) || ( r + x >= rows) ) {
                continue;
            }

            for(int y = -1; c + y < c + 2; ++y) {
                if((c + y < 0) || (c + y >= cols) ) {
                    continue;
                }

                if(mineField[r + x][c + y] == true) {
                    ++countMine;
                }
            }
        }
        return countMine;
    }

    static void printClues(int[][] clues) {
        for(int i = 0; i < clues.length; ++i) {
            for(int j = 0; j < clues[i].length; ++j) {
                if(clues[i][j] == -1) {
                    System.out.print("  *");
                    continue;
                }
                System.out.printf("%3d", clues[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Minesweeper m = new Minesweeper(3, 4, 6);
        int[ ][ ] clues = m.generateClues();
        Minesweeper.printClues(clues);
    }

}
