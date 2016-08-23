import java.util.Scanner;
import java.util.Random;

public class singlePlayer {

	public static void main(String[] args) {
		printCoolAsciiArtnShizzle();
		int[][] boardOne = new int [10][10];
		int[][] boardTwo = new int [10][10];
		int[][] shipsOne = new int [10][10];
		int[][] shipsTwo = new int [10][10];
		int turnCounter = 1;
		initBoard(boardOne);														//	board one and two are what the player sees, display boards
		initBoard(boardTwo);
		initBoard(shipsOne);
		System.out.println("Place your ships!");
		placeShips(shipsOne);														//	ships one and two stores where the ships are
		initShips(shipsTwo);
		System.out.println("\n" + "As the game currently stands" + "\n");
		System.out.println("THIS IS THE AI BOARD");
		showBoard(shipsTwo);
		System.out.println("THIS IS THE AI BOARD" + "\n");
		System.out.println("\n");
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
	
	public static void initShips(int[][] board) {
		int[] shipsComp = new int[5];
		int carrierComp = 5;
		int battleshipComp = 4;
		int submarineComp = 3;
		int cruiserComp = 3;
		int patrolComp = 2;
		shipsComp[0] = carrierComp;
		shipsComp[1] = battleshipComp;
		shipsComp[2] = submarineComp;
		shipsComp[3] = cruiserComp;
		shipsComp[4] = patrolComp;
		for(int c=0; c<5; c++) {
			int shipLength = shipsComp[c];
			Random randomGenerator = new Random();
			int columnComp = randomGenerator.nextInt(11);
			int rowComp = randomGenerator.nextInt(11);
			int orientationComp = randomGenerator.nextInt(2);
			addCompShipsToBoard(board, columnComp, rowComp, shipLength, orientationComp);
		}
	}

	public static void addCompShipsToBoard(int[][] shipsTwo, int column, int row, int shipLength, int orientation) {
		if(orientation == 0) {																					//	1 is horizontal
			for(int c=row; c<=((row+shipLength)-1); c++){
				if(shipsTwo[c-1][column-1] == 1) {
					initShips(shipsTwo);
				}
				else {
					shipsTwo[c][column] = 1;
				}
			}
				
		
		if(orientation == 1) {
			for(int c=column; c<=((column+shipLength)-1); c++) {
				if(shipsTwo[row-1][c-1] == 1) {
					initShips(shipsTwo);
				}
				else {
					shipsTwo[row][c] = 1;
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
			if((row + shipLength) <= 11) {											//	Check to see if the ship will fit
				addShipsToBoard(board, column, row, shipLength, orientation);			//	If it does add it to the board
			}
				else {
					System.out.println("Invalid entry! Try again.");					//	Also check for entry validity
					placeShips(board);
				}
		}
			else if(orientation.matches(horizontalLow) || orientation.matches(horizontalCap)) {
				if((column + shipLength) <= 11) {										//	Check to see if the ship will fit
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

   	public static void playerTurn(int player, int[][] boardOne,		// 	Player turn function
								int[][] boardTwo, int[][]shipsOne,
								int[][]shipsTwo) {					
		if(player % 2 == 0) {										//	If turn counter is divisible by 2, it's player 2's turn
			System.out.println("\n");
			System.out.println("Player 2's turn!");
			System.out.println("\n" + "Take your best shot player 2: " + "\n");
			showBoard(boardOne);
		}
			else {
				System.out.println("\n");
				System.out.println("Player 1's turn!");
				System.out.println("\n" + "Take your best shot!: " + "\n");
				showBoard(boardTwo);
			}
		
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
	
	public static void playerAttack(int column, int row, int player,//	Checks player input against ship placement
								int[][] boardOne, int[][] boardTwo,
								int[][] shipsOne, int[][] shipsTwo) {			//	Called to check attack
		if(player % 2 == 0) {													//	If turn counter is divisible by 2, it's player 2's turn
			if(shipsOne[row-1][column-1] == 1) {								//	If shot is a hit
				boardOne[row-1][column-1] = 1;											//	Update display board with hit
				System.out.println("It's a hit!" + "\n");
				checkBoard(boardOne, player);													//	Check if game is over
				showBoard(boardOne);
				System.out.println("\n" + "You get to go again!");	
				playerTurn(player, boardOne, boardTwo, shipsOne, shipsTwo);		//	Repeat turn
				}
				else {																//	If shot is a miss
					boardOne[row-1][column-1] = 0;										//	Update display board with miss
					System.out.println("Unlucky, you missed!");
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

	public static void checkBoard(int[][] board, int player) {					//	Checks each players board to check if there was a win
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
			System.out.println("\n" + "\n" + "Player 2 Wins!!");
		}
		else{
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
			System.out.println("GAME OVER!!");
			System.out.println("\n" + "\n" + "Player 1 Wins!!");
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