package chess;

import boardgame.Board;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] chessPieceMatrix = new ChessPiece[this.board.getRows()][this.board.getColumns()];
            for(int i = 0; i< board.getRows(); i++){
                for(int j = 0; j< board.getColumns(); j++ ){
                    chessPieceMatrix[i][j] = (ChessPiece) board.piece(i,j);
                }
            }
            return chessPieceMatrix;
    }
}
