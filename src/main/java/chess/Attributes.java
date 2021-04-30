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
}


