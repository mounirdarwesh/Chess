/**
 * 
 */
package chess;

/**
 * @author Ahmad Mohammad
 *
 */
public enum Attributes {
	
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
