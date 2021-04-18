/**
 * 
 */
package chess;

/**
 * @author Ahmad Mohammad
 *
 */
public enum Constants {
	
	WHITE() {
		
		public boolean isWhite() {
			return true;
		}
		
		public boolean isBlack() {
			return false;
		}
	},
	
	BLACK() {
		
		public boolean isWhite() {
			return false;
		}
		
		public boolean isBlack() {
			return true;
		}
	};
	
    public abstract boolean isWhite();

    public abstract boolean isBlack();
	

}
