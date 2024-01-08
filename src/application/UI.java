package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import boardgame.Piece;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    private static ChessMatch chessMatch;

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String s = scanner.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("error reading chess position");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        
        chessMatch = ChessMatch.getInsantance();
        
        if(chessMatch.getCapturedWhitePieces().size() >=1){
            System.out.println("Captured white pieces:");
            printPieces(chessMatch.getCapturedWhitePieces(), ANSI_WHITE);
        }
        
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        
        System.out.println("  a b c d e f g h");
        
        if(chessMatch.getCapturedBlackPieces().size() >= 1){
            System.out.println("Captured black pieces:");
            printPieces(chessMatch.getCapturedBlackPieces(), ANSI_YELLOW);
        }
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        
        chessMatch = ChessMatch.getInsantance();
        
        if(chessMatch.getCapturedWhitePieces().size() >=1){
            System.out.println("Captured white pieces:");
            printPieces(chessMatch.getCapturedWhitePieces(), ANSI_WHITE);
        }
        
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        
        System.out.println("  a b c d e f g h");

        if(chessMatch.getCapturedBlackPieces().size() >= 1){
            System.out.println("Captured black pieces:");
            printPieces(chessMatch.getCapturedBlackPieces(), ANSI_YELLOW);
        }
        
    }

    private static void printPiece(ChessPiece piece, boolean possibleMove) {
        if (possibleMove) {
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("- " + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
    }

    public static void clearConsole() {
        try {
            // For Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // For Unix/Linux/Mac
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();

            }
        } catch (Exception e) {
            // Handle exceptions as needed
            e.printStackTrace();
        }
    }

    public static void printMatch(ChessMatch chessMatch) {
        String currentPlayer = chessMatch.getCurrentPlayer() == Color.WHITE ? "White" : "Black";
        System.out.println("Player: " + currentPlayer);
    }

    private static void printPieces(ArrayList<Piece> pieces, String colorCode) {
        for (int i = 0; i < pieces.size(); i++) {
            System.out.print(colorCode + pieces.get(i).toString() + ANSI_RESET+" ");
        }
        System.out.println();
    }

    private static void printPossibleMoves(boolean[][] possibleMoves) {
        for (int i = 0; i < possibleMoves.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < possibleMoves.length; j++) {
                if (possibleMoves[i][j]) {
                    System.out.print(" T ");
                } else {
                    System.out.print(" F ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

}
