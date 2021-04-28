package chess.cli;

import java.util.Scanner;

import chess.controller.CliController;
import chess.view.View;

/**
 * The command line interface
 */
public class Cli extends View{
	
	/**
	 * 
	 */
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * The CLIController
	 */
	protected CliController controller;
	

	
    /**
     * The constructor of the view of the CLI application.
     */
    public Cli() {
    	super();
    }


	/**
	 * @return the input
	 */
	public void readInputFromPlayer() {
		
		String input = scanner.nextLine();
		while(true) {
			if(!controller.isValidInput(input)) {
				System.out.println("!Invalid move");
				input = scanner.nextLine();
				continue;
			}
			
			if(!controller.isValidMove(input)){
				System.out.println("!Move not allowed");
				input = scanner.nextLine();
				continue;
			}
			
			else System.out.println("!" + input);
			break;
		}
		
	}
	
	/**
	 * 
	 * @param controller
	 */
	public void assignController(CliController controller) {
		this.controller = controller;
	}
	
}
