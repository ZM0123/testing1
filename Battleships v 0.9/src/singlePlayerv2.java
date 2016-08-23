import java.util.Random;
import java.util.Scanner;

public class singlePlayerv2 {

	public static void main(String[] args) {
		int[][] boardOne = new int [10][10];						//	Create the different arrays
		int[][] boardTwo = new int [10][10];						//	boardOne and Two are the displayed boards
		int[][] shipsOne = new int [10][10];						//	shipsOne and Two stores the ship locations
		int[][] shipsTwo = new int [10][10];
		int turnCounter = 1;										//	This is used to determine if it is the players turn or the AIs
		initBoard(boardOne);										//	Start by setting the boards value to -1
		initBoard(boardTwo);										//	This means they will display water unless the value is altered
		initShipsComp(shipsTwo);
		showBoard(shipsTwo);
		System.out.println("Place your ships!" + "\n");
		placeShips(shipsOne);
		System.out.println("As the game currently stands" + "\n");
		showBoard(boardOne);
		System.out.println("\n");
		showBoard(boardTwo);
		playerTurn(turnCounter, boardOne, boardTwo, shipsOne, shipsTwo);
	}
		
	public static void initBoard(int[][] board) {					//	Function to create game board
		for(int row=0; row < 10; row++)								//	Initially set all blocks to -1 - neutral
			for(int column=0; column < 10; column++)
					board[row][column]=-1;
	}
	
	public static void initBoardComp(int[][] board) {					//	Function to create game board
		for(int row=0; row < 10; row++)								//	Initially set all blocks to -1 - neutral
			for(int column=0; column < 10; column++)
					board[row][column]=0;
	}

	public static void showBoard(int[][] board){					//	Prints off board of choice
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10");
        System.out.println();
        
        for(int row=0 ; row < 10 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 10 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~~~~~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*****");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"XXXXX");
                }
                
            }
            System.out.println();
        }

    }
	
	
	public static void initShipsComp(int[][] ships) {
		int[] shipLengths = new int[5];
		int carrierLength = 5;
		int battleshipLength = 4;
		int submarineLength = 3;
		int cruiserLength = 3;
		int patrolLength = 2;
		shipLengths[0] = carrierLength;
		shipLengths[1] = battleshipLength;
		shipLengths[2] = submarineLength;
		shipLengths[3] = cruiserLength;
		shipLengths[4] = patrolLength;
		
		for(int c=0; c<5; c++) {
			Random randomNumber = new Random();
			int shipLength = shipLengths[c];
			int column = randomNumber.nextInt(10);
			int row = randomNumber.nextInt(10);
			int orientation = randomNumber.nextInt(2);
			if(column + shipLengths[c] > 9 || row + shipLengths[c] > 9) {
				c--;
				continue;
				//initShipsComp(ships);
			}
				else {
					if(orientation == 1) {
						for(int i=row; i<=((row+shipLength)-1); i++) {
							if(ships[i][column] == 1) {
								//i--;
								c--;
								break;
								//initBoardComp(ships);
								//initShipsComp(ships);
							}
							else {
								ships[i][column] = 1;
								}
							}
						}
					else {
						for(int j=column; j<=((column+shipLength) -1); j++) {
							if(ships[row][j] == 1) {
								//j--;
								c--;
								break;
								//initBoardComp(ships);
								//initShipsComp(ships);
							}
							else {
								ships[row][j] = 1;
							}
						}
					}
				}
			}
		}
    
	public static void placeShips(int[][] board) {					//	Function for deciding coordinates

		System.out.println("\n");
		System.out.println("You have 5 ships to place. A carrier, Battleship, Submarine,");
		System.out.println("			   Cruiser and Patrol.");
		int[] ships = new int[5];
		int carrier = 5;
		int battleship = 4;
		int submarine = 3;
		int cruiser = 3;
		int patrol = 2;
		ships[0] = carrier;
		ships[1] = battleship;
		ships[2] = submarine;
		ships[3] = cruiser;
		ships[4] = patrol;
		for(int c=0; c<5; c++) {
			System.out.println("\n");
			System.out.println("Ship " + (c+1) + " takes up " + ships[c] + " spaces");
			System.out.println("\n"+ "You are selecting where the ship begins ie. The top-left of the ship.");
			
			Scanner column = new Scanner(System.in);						//	First find the coordinates of placement
			int columnInput;
			
			do {
				System.out.println("Please enter your column: ");			//	Take column
				while (!column.hasNextInt()) {								//	Loop until a valid integer is entered
					column.next();
				}
				columnInput = column.nextInt();
			} while (columnInput > 10 || columnInput < 1);

			Scanner row = new Scanner(System.in);
			int rowInput;
			
			do {
				System.out.println("Please enter your row: ");				//	Take row
				while (!row.hasNextInt()) {									//	Loop until a valid integer is entered
					row.next();
				}
				rowInput = row.nextInt();
			} while (rowInput > 10 || rowInput < 1);
			
			orientateShips(board, columnInput, rowInput, ships[c]);			
		}
	}
	
	public static void orientateShips(int[][] board, int column, int row, int shipLength) {	//	Function for deciding orientation
		System.out.println("\n");
		System.out.println("Would you like to place the ship horizontally [h] or vertically [v]?");
		Scanner input = new Scanner(System.in);
		String orientation = input.nextLine();
		String verticalLow = "v";
		String horizontalLow = "h";
		String verticalCap = "V";
		String horizontalCap = "H";
		if(orientation.matches(verticalLow) || orientation.matches(verticalCap)) {		//	Determining if the ship is to go vertical or horizontal
			if((row + shipLength) <= board.length) {											//	Check to see if the ship will fit
				addShipsToBoard(board, column, row, shipLength, orientation);			//	If it does add it to the board
			}
				else {
					System.out.println("Invalid entry! Try again.");					//	Also check for entry validity
					placeShips(board);
				}
		}
			else if(orientation.matches(horizontalLow) || orientation.matches(horizontalCap)) {
				if((column + shipLength) <= board.length) {										//	Check to see if the ship will fit
					addShipsToBoard(board, column, row, shipLength, orientation);		//	If it does add it to the board
				}
					else {
						System.out.println("Invalid entry! Try again.");				//	Also check for entry validity
						placeShips(board);
					}
			}
				else {
					System.out.println("Invalid entry! Try again.");
					orientateShips(board, column, row, shipLength);
				}
	}
	
	public static void addShipsToBoard(int[][] board, int column, int row, int shipLength, String orientation) {	//	Function that modifies the board based on coordinates and orientation
		String verticalLow = "v";
		String horizontalLow = "h";
		String verticalCap = "V";
		String horizontalCap = "H";
		if(orientation.matches(verticalLow) || orientation.matches(verticalCap)) {		//	Determining if the ship is to go vertical or horizontal
			for(int c=(row); c<=(row+(shipLength-1)); c++){
				if(board[c-1][column-1] == 1){
					System.out.println("There is already a ship there!" + "\n" + "Start Again!");
					initBoard(board);
					placeShips(board);
				}
					else{
						board[c-1][column-1] = 1;
					}
				}
			}
			else if(orientation.matches(horizontalLow) || orientation.matches(horizontalCap)) {	
				for(int c=(column); c<=(column+(shipLength-1)); c++){
					if(board[row-1][c-1] == 1){
						System.out.println("There is already a ship there!" + "\n" + "Start Again!");
						initBoard(board);
						placeShips(board);
					}
						else{
							board[row-1][c-1] = 1;
						}
				}
			}
		showBoard(board);
	}

   	public static void playerTurn(int player, int[][] boardOne,		// 	Player turn function
   								int[][] boardTwo, int[][]shipsOne,
   										int[][]shipsTwo) {					
   		if(player % 2 == 0) {										//	If turn counter is divisible by 2, it's player 2's turn
   			System.out.println("\n");
   			System.out.println("Time for the computers turn!" + "\n");
   			showBoard(boardOne);
   			// ADD IN COMPUTER TURN HERE
   			Random randomNumber = new Random();
   			int columnComp = randomNumber.nextInt(10);
   			int rowComp = randomNumber.nextInt(10);
   			playerAttack(columnComp, rowComp, player, boardOne, boardTwo, shipsOne, shipsTwo);
   		}
   			else {
   				System.out.println("\n");
   				System.out.println("Player 1's turn!");
   				System.out.println("\n" + "Take your best shot Player 1: " + "\n");
   				showBoard(boardTwo);
   				Scanner column = new Scanner(System.in);
   				int columnInput;
   				do {
   					System.out.println("Please enter your column: ");			//	Take column
   					while (!column.hasNextInt()) {								//	Loop until a valid integer is entered
   						column.next();
   					}
   					columnInput = column.nextInt();
   				} while (columnInput > 10 || columnInput < 1);

   				Scanner row = new Scanner(System.in);
   				int rowInput;

   				do {
   					System.out.println("Please enter your row: ");				//	Take row
   					while (!row.hasNextInt()) {									//	Loop until a valid integer is entered
   						row.next();
   					}
   					rowInput = row.nextInt();
   				} while (rowInput > 10 || rowInput < 1);
   			
   				playerAttack(columnInput, rowInput, player, boardOne, boardTwo, shipsOne, shipsTwo);
   		}
   	}

   	public static void playerAttack(int column, int row, int player,//	Checks player input against ship placement
   								int[][] boardOne, int[][] boardTwo,
   									int[][] shipsOne, int[][] shipsTwo) {			//	Called to check attack
   		if(player % 2 == 0) {													//	If turn counter is divisible by 2, it's player 2's turn
   			if(shipsOne[row][column] == 1) {								//	If shot is a hit
   				boardOne[row][column] = 1;											//	Update display board with hit
   				System.out.println("It's a hit!" + "\n");
   				checkBoard(boardOne, player);													//	Check if game is over
   				showBoard(boardOne);
   				System.out.println("\n" + "This computers a beast!");	
   				playerTurn(player, boardOne, boardTwo, shipsOne, shipsTwo);		//	Repeat turn
   			}
   			else {																//	If shot is a miss
   				boardOne[row-1][column-1] = 0;										//	Update display board with miss
   				System.out.println("\n" + "Lel the comp sucks");
   				showBoard(boardOne);
   				player++;															//	Add one to player counter
   				playerTurn(player, boardOne, boardTwo, shipsOne, shipsTwo);		//	Next turn
   			}

   		}
   		else {																	//	If it's player 1's turn
   			if (shipsTwo[row-1][column-1] == 1) {									//	If shot is a hit
   				boardTwo[row-1][column-1] = 1;											//	Update display board with hit
   				System.out.println("It's a hit!" + "\n");
   				checkBoard(boardTwo, player);													//	Check if game is over
   				showBoard(boardTwo);
   				System.out.println("\n" + "You get to go again!");
   				playerTurn(player, boardOne, boardTwo, shipsOne, shipsTwo);		//	Repeat turn
   			}
   			else {															//	If shot is a miss
   				boardTwo[row-1][column-1] = 0;									//	Update display board with miss
   				System.out.println("Unlucky, you missed!");
   				showBoard(boardTwo);
   				player++;														//	Add one to player counter
   				playerTurn(player, boardOne, boardTwo, shipsOne, shipsTwo);	//	Next turn
}

}

}
   	
	public static void checkBoard(int[][] board, int player) {
					//	Checks each players board to check if there was a win
		int hitCounter = 0;
		for(int row=0; row < 10; row++){
			for(int column=0; column < 10; column++){
				if(board[row][column] == 1){
					hitCounter++;
				}
			}
		if(hitCounter == 17){														//	Maximum hits player can receive
			showBoard(board);														//	Print winning board
			gameEnd(player);
			}
		}
	}
	
	public static void gameEnd(int player) {									//	Ends the game		
		if(player % 2 == 0) {
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
		}
		else{
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
			System.out.println("\n" + "\n" + "Well Done!!");
		}
		System.exit(0);
	}
	
	public static void printCoolAsciiArtnShizzle(){
		  {
			    StringBuilder sb = new StringBuilder();
			    
			    sb.append("                                     |__\n");
			    sb.append("                                     |\\/\n");
			    sb.append("                                     ---\n");
			    sb.append("                                     / | [\n");
			    sb.append("                              !      | |||\n");
			    sb.append("                            _/|     _/|-++'\n");
			    sb.append("                        +  +--|    |--|--|_ |-\n");
			    sb.append("                     { /|__|  |/\\__|  |--- |||__/\\\n");
			    sb.append("                    +---------------___[}-_===_.'____                 /\\\n");
			    sb.append("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _\n");
			    sb.append(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7\n");
			    sb.append("|                   BattleShips v0.05 - By Stuart Russell                    /\n");
			    sb.append(" \\_________________________________________________________________________|\n\n");
			    
			    System.out.println(sb.toString());
			  }
	}

}