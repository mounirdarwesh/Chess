/**
 * 
 */
package chess;

/**
 * @author Ahmad Mohammad
 *
 */
public class Attributes {
	
	/**
	 * 
	 * @author Gr.45
	 *
	 */
	public enum Color {
		WHITE() {
			
			public boolean isWhite() {
				return true;
			}
			
			public boolean isBlack() {
				return false;
			}

			@Override
			public int getDirection() {
				return 1;
			}
		},
		
		BLACK() {
			
			public boolean isWhite() {
				return false;
			}
			
			public boolean isBlack() {
				return true;
			}

			@Override
			public int getDirection() {
				return -1;
			}
		};
		
	    public abstract boolean isWhite();

	    public abstract boolean isBlack();
	    
	    public abstract int getDirection();


	}

	/**
	 * 
	 * @author Gr.45
	 *
	 */
	public enum MoveType {
		
		NORMAL,
		
		DOUBLE_PAWN,
		
		CAPTURE,
		
		EN_PASSANT,
		
		PROMOTION()
		
	}
	// Indexes of the first Column
	public static final int[] FIRST_COLUMN = {0, 8, 16, 24, 32, 40, 48, 56};

	// Indexes of the Last Column
	public static final int[] LAST_COLUMN = {7, 15, 23, 31, 39, 47, 55, 63};

	// Indexes of the second Column
	public static final int[] SECOND_COLUMN = {1, 9, 17, 25, 33, 41, 49, 57};

	// Indexes of the seventh Column
	public static final int[] SEVENTH_COLUMN = {6, 14, 22, 30, 38, 46, 54, 62};
}


