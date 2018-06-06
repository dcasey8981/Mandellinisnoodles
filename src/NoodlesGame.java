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
    int previousColumn = 10;
    int previousRow = 10;
    int currentRow = 0;
    int currentColumn = 0;
    boolean timerRunning = false;
    Timer timer;

    String[] pastas = {"Garganelli","Rigate","Penne","Rigatoni",
            "Fiocchi","Giarandole","Fusilli","Ziti","Buccatini",
            "Canalini","Gnocchi","Orecchiette","Lancette",
            "Ditaloni Rigati","Risoni","Peppardelle","Conchiglioni","Macaroni"};

    public static void main(String[] args) {
        new NoodlesGame();
    }


    public NoodlesGame() {
        frame.setSize(1000, 1000);
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

        for (int pastiNumber = 0; pastiNumber <= 17; pastiNumber++) {
            Random rand = new Random();
            int num1X = rand.nextInt(6);
            int num1Y = rand.nextInt(6);
            while(board[num1X][num1Y]!= null){
                num1X = rand.nextInt(6);
                num1Y = rand.nextInt(6);
            }
            if(board[num1X][num1Y]== null){board[num1X][num1Y]=pastas[pastiNumber];}
            int num2X = rand.nextInt(6);
            int num2Y = rand.nextInt(6);
            while(board[num2X][num2Y]!= null){
                num2X = rand.nextInt(6);
                num2Y = rand.nextInt(6);
            }
            if(board[num2X][num2Y]== null){board[num2X][num2Y]=pastas[pastiNumber];}
        }
    }
    /*public int NumberGoodX(int num1X,int num2X){
        Random rand = new Random();
        while(num1X==num2X){
            num2X = rand.nextInt(6);
        }
        return num2X;
    }
    public int NumberGoodY( int num2Y, int num1Y){
        Random rand = new Random();
        while(num1Y==num2Y){
            num2Y = rand.nextInt(6);
        }
        return num2Y;
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] flipped = new String[2];

        if((timer != null && timer.isRunning()==false) || timer==null){
            for (int row = 0; row < button.length; row++) {
                for (int column = 0; column < button[0].length; column++) {
                    if (e.getSource().equals(button[column][row])) {
                        if(flippedCount == 0){
                            button[column][row].setText(board[column][row]);
                            button[column][row].setEnabled(false);
                            flipped[0]=board[column][row];
                            previousColumn = column;
                            previousRow = row;
                            flippedCount=1;
                        }
                        else if(flippedCount == 1){
                            currentColumn = column;
                            currentRow = row;
                            button[column][row].setText(board[column][row]);
                            flipped[1]=board[column][row];
                            flipped[0]=board[previousColumn][previousRow];
                            System.out.println(flipped[0]);
                            System.out.println(flipped[1]);
                            if(flipped[1]==flipped[0]){
                                button[column][row].setEnabled(false);
                                flippedCount= 0;
                            }
                            else{

                                button[column][row].setEnabled(false);
                                button[column][row].setText(board[column][row]);
                                ActionListener taskPerformer = new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                        button[previousColumn][previousRow].setEnabled(true);
                                        button[previousColumn][previousRow].setText("");
                                        button[currentColumn][currentRow].setEnabled(true);
                                        button[currentColumn][currentRow].setText("");
                                    }
                                };
                                timer = new Timer(2000,taskPerformer);
                                timer.start();
                                timer.setRepeats(false);
                                timerRunning=timer.isRunning();
                                flippedCount= 0;
                            }

                        }
                        else{
                            button[previousColumn][previousRow].setEnabled(false);
                            button[previousColumn][previousRow].setText("");
                            button[column][row].setEnabled(false);
                            button[column][row].setText("");

                        }

                    }
                }
            }


        }

    }
}
