//Author: Harry Sivasubramaniam
//Email: hsivasub@uwaterloo.ca
//Instructions: this program will read a file called "grid.txt" stored in the directory indicated 
//in the main function. If "grid.txt" corresponds to a valid sudoku puzzle then this program will
//print out the solution else it will print out "Invalid Sudoku Puzzle"

package sudokusolver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class solver {
	public class Position{//used to return x,y positions of a cell on our grid
		int row;
		int col;
	}
	
	public static boolean solveSudoku(int [][] grid){ //returns true if solutions is found
		Position pos = new solver(). new Position();
		
		//findCell will take the grid and set pos to the position of the first empty cell
		if(findCell(grid, pos)==false){//findCell returns false if the grid is full(has been solved)
			return true; //yay! our puzzle has a solution
		}

		for(int i=1;i<=9;i++){
			grid[pos.row][pos.col]=i; //BRUTE FORCE!!!!
			if(isValid(grid, pos)){
				if(solveSudoku(grid)){
					return true; //yay our grid is solved
				}
			}
		}
		grid[pos.row][pos.col]=0; //resets the current position
		return false; //returns false if current state of grid is not solvable(requires backtrack)
	}
	
	public static boolean isValid(int [][] grid, Position pos){//checks if grid is valid when pos is inserted
		//grid is valid if numbers in rows,column and boxes are unique or 0
		for(int i=0;i<9;i++){
			//loops through columns of given row of the pos and checks if valid
			if(grid[pos.row][i]==grid[pos.row][pos.col] && i!=pos.col){
				return false;
			}
			//loops through rows of given column of pos in grid and checks if valid
			if(grid[i][pos.col]==grid[pos.row][pos.col] && i!=pos.row){
				return false;
			}
		}
		//checks if the 3x3 boxes are valid
		int r = (pos.row/3)*3;
		int c = (pos.col/3)*3;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if((i+r)==pos.row&&(j+c)==pos.col){
					continue;//we skip pos
				}
				if(grid[i+r][j+c]==grid[pos.row][pos.col]){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean findCell(int [][] grid, Position pos){ //returns 
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(grid[i][j]==0){//sets the pos of empty cell in our grid
					pos.row=i;
					pos.col=j;
					return true; //returns true to indicate empty cell exists
				}
			}
		}
		return false; //if we loop through grid and no empty cell is found then return false 
	}
	public static void printGrid(int [][]grid){
		 for(int i=0;i<9;i++){
		   		for(int j=0;j<9;j++){
		   			System.out.print(grid[i][j] + " "); //prints each cell of grid 
		   		}
		   		System.out.println(); //prints a new line
	    	}
	}
	
	public static void main(String args[]){
	    int [][]grid = new int[9][9];
	    try{
	    	//reads text file from directory (change this to where your text file is located)
	    	File file = new File("/Users/harry/Documents/workspace/sudokusolver/src/sudokusolver/grid.txt");
	    	FileReader fr = new FileReader(file); 
	    	Scanner in = new Scanner(fr); 
	    	int row=0;
	    	//populates our sudoku grid with the data from "grid.txt"
	    	while(in.hasNextLine() && row<9){
	    		String line=in.nextLine();
	    		Scanner lineScanner = new Scanner(line);
	    		for(int col=0; col<9; col++){
	    			grid[row][col] = lineScanner.nextInt();
	    		}
	    		row++; 
	    	}
	    }
	    catch(IOException e){
	    	System.out.println("File I/O Error");
	    }
	    //prints original puzzle
	    printGrid(grid);

	    if(solveSudoku(grid)){  //solveSudoku(grid) attempts to solve grid and returns true if solution is found
	    	System.out.println("Solution:");
	    	printGrid(grid);
	    }
	    else{ //if solveSudoku(grid) returns false then no solution exists for given puzzle
	    	System.out.println("Invalid Sudoku Puzzle");
	    	
	    }
	}
}














