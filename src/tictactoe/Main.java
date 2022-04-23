package tictactoe;

import java.util.Scanner;

public class Main {
    private static boolean currentSymbolIsX;

    public static void main(String[] args) {
        String[][] field = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        currentSymbolIsX = true;
        printGame(field);
        makeMove(field);
    }

    public static void makeMove(String[][] field) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        boolean coordinatesAreCorrect = x > 0 && x < 4 && y > 0 && y < 4;
        if (coordinatesAreCorrect) {
            if (field[x-1][y-1].equals(" ")) {
                field[x-1][y-1] = currentSymbolIsX ? "X" : "O";
                currentSymbolIsX = !currentSymbolIsX;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                makeMove(field);
                return;
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            makeMove(field);
            return;
        }
        printGame(field);
        analyzeGame(field);
    }

    public static void analyzeGame(String[][] field) {
        int xs = 0;
        int os = 0;
        for (String[] strings : field) {
            for (String string : strings) {
                if (string.equals("X")) {
                    xs++;
                } else if (string.equals("O")) {
                    os++;
                }
            }
        }
        int[][] winMatrix = {
                {field[0][0].charAt(0), field[0][1].charAt(0), field[0][2].charAt(0)},
                {field[1][0].charAt(0), field[1][1].charAt(0), field[1][2].charAt(0)},
                {field[2][0].charAt(0), field[2][1].charAt(0), field[2][2].charAt(0)},
                {field[0][0].charAt(0), field[1][0].charAt(0), field[2][0].charAt(0)},
                {field[0][1].charAt(0), field[1][1].charAt(0), field[2][1].charAt(0)},
                {field[0][2].charAt(0), field[1][2].charAt(0), field[2][2].charAt(0)},
                {field[0][0].charAt(0), field[1][1].charAt(0), field[2][2].charAt(0)},
                {field[0][2].charAt(0), field[1][1].charAt(0), field[2][0].charAt(0)},
        };
        int xWins = 0;
        int oWins = 0;
        for (int[] matrix : winMatrix) {
            int xWinsMax = 264;
            if (matrix[0] + matrix[1] + matrix[2] == xWinsMax) {
                xWins++;
            }
            int oWinsMax = 237;
            if (matrix[0] + matrix[1] + matrix[2] == oWinsMax) {
                oWins++;
            }
        }
        if (Math.abs(xs - os) > 1 || xWins + oWins > 1) {
            System.out.println("Impossible");
            return;
        }
        if (xWins == 1) {
            System.out.println("X wins");
            return;
        }
        if (oWins == 1) {
            System.out.println("O wins");
            return;
        }
        if (xs + os == 9) {
            System.out.println("Draw");
            return;
        }
        makeMove(field);
    }

    public static void printGame(String[][] field) {
        System.out.println("---------");
        System.out.println("| " + field[0][0] + " " + field[0][1] + " " + field[0][2] + " |");
        System.out.println("| " + field[1][0] + " " + field[1][1] + " " + field[1][2] + " |");
        System.out.println("| " + field[2][0] + " " + field[2][1] + " " + field[2][2] + " |");
        System.out.println("---------");
    }
}

