import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class NoodlesGame implements ActionListener {
    Scanner pastiScanner = new Scanner(System.in);
    JFrame frame = new JFrame("Mandellini's Noodles");
    String[][] board = new String[6][6];
    JLabel pairsLabel = new JLabel("Pairs Found: 0");
    Container north = new Container();
    Container center = new Container();
    JButton button[][] = new JButton[6][6];
    int flippedCount = 0;
    String[] pastas = {"Garganelli","Rigate","Penne","Rigatoni",
            "Fiocchi","Giarandole","Fusilli","Ziti","Buccatini",
            "Canalini","Gnocchi","Orecchiette","Lancette",
            "Ditaloni Rigati","Risoni","Peppardelle","Conchiglioni"};

    public static void main(String[] args) {
        new NoodlesGame();
    }


    public NoodlesGame() {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(6, 6));
        for (int row = 0; row < button.length; row++) {
            for (int column = 0; column < button.length; column++) {
                button[column][row] = new JButton();
                center.add(button[column][row]);
                button[column][row].addActionListener(this);
            }
        }
        frame.add(center, BorderLayout.CENTER);
        north.setLayout(new GridLayout(1, 1));
        north.add(pairsLabel);
        frame.add(north, BorderLayout.NORTH);
        frame.setVisible(true);
        BoardSetter();


    }

    public void BoardSetter() {

        for (int pastiNumber = 0; pastiNumber <= 16; pastiNumber++) {
            Random rand = new Random();
            int num1X = rand.nextInt(6);
            int num1Y = rand.nextInt(6);
            int num2X = rand.nextInt(6);
            int num2Y = rand.nextInt(6);
            while (num1X == num2X && num1Y == num2Y) {
                num2X = rand.nextInt(6);
                num2Y = rand.nextInt(6);
            }
            board[num1X][num1Y] = pastas[pastiNumber];
            board[num2X][num2Y] = pastas[pastiNumber];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] flipped = new String[2];

        int previousColumn = 10;
        int previousRow = 10;

        for (int row = 0; row < button.length; row++) {
            for (int column = 0; column < button[0].length; column++) {
                if (e.getSource().equals(button[column][row])) {
                    previousColumn = 10;
                    previousRow = 10;
                    System.out.println(flippedCount);
                    if(flippedCount==0){
                        previousColumn = column;
                        previousRow = row;
                    }
                    if(flippedCount == 0){
                        button[column][row].setText(board[column][row]);
                        button[column][row].setEnabled(false);
                        flipped[0]=board[column][row];
                        flippedCount=1;
                    }
                    if(flippedCount == 1){
                        button[column][row].setText(board[column][row]);
                        flipped[1]=board[column][row];
                        if(flipped[1]==flipped[0]){
                            button[column][row].setEnabled(false);
                            flippedCount= 0;
                        }
                        else{
                            button[column][row].setText("");

                            flippedCount= 0;
                        }
                    }
                    if(previousColumn != 10 && previousRow != 10){
                        button[previousColumn][previousRow].setText("");
                    }
                
                
                }
            }
        }
    }
}
