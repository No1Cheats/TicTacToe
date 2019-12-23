//Jan Imhof made with a Youtube Tutorial by Alex Lee (https://www.youtube.com/watch?v=gQb3dE-y1S4)

import java.util.*;

public class Main {

    static ArrayList<Integer> playerPostions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPostions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ' , '|' , ' ' , '|' , ' '},
                {'-' , '+' , '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-' , '+' , '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while(true){ //Main game Loop
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement 1-9: ");
            int playerPos = scan.nextInt();
            while (playerPostions.contains(playerPos) || cpuPostions.contains(playerPos)){
                System.out.println("Position has already been taken, enter a correct one please.");
                playerPos = scan.nextInt();
            }

            move(gameBoard, playerPos, "player");

            Random rand = new Random();
            int cpuPos = rand.nextInt(9)+1;
            while (playerPostions.contains(cpuPos) || cpuPostions.contains(cpuPos)){
                cpuPos = rand.nextInt(9)+1;
            }

            move(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            String result = checkWinner();
            System.out.println(result);
            if(result.equals("Congratulations you've won.") || result.equals("Sorry CPU wins, better luck next time.") || result.equals("Board full.")){
                break;
            }
        }

    }

    public static String checkWinner(){
        //Lists all the winning Conditions
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);
        //List of winning conditions
        List<List> winningCondition = new ArrayList<List>();
        winningCondition.add(topRow);
        winningCondition.add(middleRow);
        winningCondition.add(bottomRow);
        winningCondition.add(leftCol);
        winningCondition.add(midCol);
        winningCondition.add(rightCol);
        winningCondition.add(cross1);
        winningCondition.add(cross2);

        //Iterator to go and check for any winning conditions
        for(List l : winningCondition){
            if(playerPostions.containsAll(l)){
                return "Congratulations you've won."; //Player won
            } else if(cpuPostions.containsAll(l)){
                return "Sorry CPU wins, better luck next time."; //CPU won
            } else if(playerPostions.size() + cpuPostions.size() == 9){
                return "Board full."; //Board full
            }
        }

        return "";

    }

    public static void printGameBoard(char[][] gameBoard){ //Prints the GameBoard
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }

    public static void move(char[][] gameBoard, int pos, String user){
        char symbol;

        if(user.equals("player")){
            symbol = 'X';
            playerPostions.add(pos);
        } else {
            symbol = 'O';
            cpuPostions.add(pos);
        }

        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

}
