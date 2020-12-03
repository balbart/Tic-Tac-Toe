import javax.swing.*;
import java.util.Random;

public class Backend {
    private JButton[][] matrix;
    private int amountOfMoves;
    private int mode;

    Backend(JButton[][] matrix) {
        this.matrix = matrix;
        amountOfMoves = 0;
        this.mode = setMode();
        clearButtonMatrix();
    }

    private int setMode(){
        String[] options = {"PvP", "PvC"};
        return JOptionPane.showOptionDialog (null, "Choose mode:", "Title",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    private void clearButtonMatrix(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                this.matrix[i][j].setText("");
            }
        }
    }

    private boolean isWin(){
        for (int i = 0; i < 3; i++) {
            if(matrix[i][0].getText().equals(matrix[i][1].getText()) && matrix[i][1].getText().equals(matrix[i][2].getText()) && !matrix[i][0].getText().equals("")) return true;
            if(matrix[0][i].getText().equals(matrix[1][i].getText()) && matrix[1][i].getText().equals(matrix[2][i].getText()) && !matrix[0][i].getText().equals("")) return true;
        }
        if(matrix[0][0].getText().equals(matrix[1][1].getText()) && matrix[1][1].getText().equals(matrix[2][2].getText()) && !matrix[1][1].getText().equals("")) return true;
        if(matrix[0][2].getText().equals(matrix[1][1].getText()) && matrix[1][1].getText().equals(matrix[2][0].getText()) && !matrix[1][1].getText().equals("")) return true;

        return false;
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrix[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    private boolean isUsable(JButton button){
        return button.getText().equals("");
    }

    public void onClick(JButton button){
        if(isUsable(button)){
            if(mode == 0){//чел против чела
                if(amountOfMoves % 2 == 0) button.setText("X");
                else button.setText("O");
                amountOfMoves++;
                if(isWin()) JOptionPane.showMessageDialog(null, "Winner is " + winner());
                if(!isWin() && isFull()) JOptionPane.showMessageDialog(null, "Tie!");
                if(isFull() || isWin()){
                    amountOfMoves = 0;
                    clearButtonMatrix();
                    mode = setMode();
                }
            }
            else if(mode == 1){// player vs. pc
                button.setText("X");
                if(isWin()) JOptionPane.showMessageDialog(null, "You win");
                if(isFull() && !isWin()) JOptionPane.showMessageDialog(null, "Tie!");
                if(isFull() || isWin()){
                    amountOfMoves = 0;
                    clearButtonMatrix();
                    mode = setMode();
                }
                else{
                    int xB, yB;
                    do{
                        xB = Math.abs(new Random().nextInt()) % 3;
                        yB = Math.abs(new Random().nextInt()) % 3;
                    } while(!isUsable(matrix[xB][yB]));
                    matrix[xB][yB].setText("O");
                    if(isWin()) JOptionPane.showMessageDialog(null, "Bot win");
                    if(isFull() && !isWin()) JOptionPane.showMessageDialog(null, "Tie!");
                    if(isFull() || isWin()){
                        amountOfMoves = 0;
                        clearButtonMatrix();
                        mode = setMode();
                    }
                }

            }
        }
    }

    private String winner(){
        if(isWin()){
            if (amountOfMoves % 2 == 0) return "O";
            else return "X";
        }
        else return null;
    }

}
