package chess.player;
import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.Game;
import chess.model.Piece;

import java.util.ArrayList;

/**
 * @author Ahmad Mohammad
 *
 */
public class Computer extends Player{
	
	
	/*
	 * The constructor of the computer class
	 * @param color  the color that the computer is left with
	 */
	public Computer(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeMove(Move move) {
		//TODO
	}

	@Override
	public ArrayList<Move> calculateEnemyLegalMoves(ArrayList<Piece> pieces) {
		return null;
	}

	@Override
	public boolean isKingInCheck(int kingPosition, ArrayList<Move> enemyMoves) {
		return false;
	}

	@Override
	public Piece getKing(ArrayList<Piece> myPieces) {
		return null;
	}

	@Override
	public boolean canProtectKing(Piece piece, int destination, Game game) {
		return false;
	}

}
