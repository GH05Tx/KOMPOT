package zad2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SudokuBoard {
    private List<List<SudokuField>> board;

    public SudokuBoard() {
        board = Arrays.asList(new List[9]);
        for (int i = 0; i <board.size() ; i++) {
                board.set(i,Arrays.asList(new SudokuField[9]));
            }
            for(int i=0;i<board.size();i++){
                for(int j=0;j<board.get(i).size();j++){
                    board.get(i).set(j,new SudokuField());
                }
            }
    }

    public List<List<SudokuField>> getBoard() {
        List<List<SudokuField>> pom;
        pom = board;
        return pom;
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean containsInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row).get(i).getFieldValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(col).getFieldValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.get(i).get(j).getFieldValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkBoard(int row, int col, int number) {
        return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }

    //Wypelnia cala plansze zerami i losuje pierwszy rzad
    public void generateBoard() {
        List<Integer> pom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            pom.add(i);
        }
        Collections.shuffle(pom);
        for (int i = 0; i < 9; i++) {
            board.get(0).get(i).setFieldValue(pom.get(i) + 1);
        }
    }

    public int get(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) throw new IllegalArgumentException("liczby poza zakresem");
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        if (x < 0 || x > 9 || y < 0 || y > 9 || value > 9 || value < 0)
            throw new IllegalArgumentException("liczby poza zakresem");
        board.get(x).get(y).setFieldValue(value);
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i).get(j).getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    public SudokuRow getRow(int y){
        if(y>9||y<0) throw new IllegalArgumentException("liczba poza zakresem");
        List<SudokuField> sudokuField = Arrays.asList(new SudokuField[9]);
        for(int i=0; i<9; i++)
        {
            sudokuField.set(i,this.board.get(y).get(i));
        }
        SudokuRow row = new SudokuRow(sudokuField);
        return row;
    }

    public SudokuColumn getColumn(int x){
        if(x>9||x<0) throw new IllegalArgumentException("liczby poza zakresem");
        List<SudokuField> sudokuField = Arrays.asList(new SudokuField[9]);
        for(int i = 0;i<9;i++){
            sudokuField.set(i, this.board.get(i).get(x));
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuField);
        return sudokuColumn;
    }

    public SudokuBox getBox(int x,int y){
        if(x>8||x<0||y>8||y<0) throw new IllegalArgumentException("liczby poza zakresem");
        int r = x - x % 3;
        int c = y - y % 3;
        List<SudokuField> sudokuField = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i <3; i++) {
            for (int j = 0; j <3; j++) {
                sudokuField.set(i, board.get(i + r).get(j + c));
            }
        }
        SudokuBox sudokuBox= new SudokuBox(sudokuField);
        return sudokuBox;
    }
}