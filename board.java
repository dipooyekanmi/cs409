import java.io.IOException;
import java.util.ArrayList;


public class board {
	
	public board(String element, int y, int x, String dir, String[] playerName, int numPlayers, ArrayList<Object> list, char[] rack, char[][] board, int size, int total, int val, int[] score){
	}
	
	public void place(String element, int y, int x, String dir, String[] playerName, int numPlayers, ArrayList<Object> list, char[] rack, char[][] board, int size,int total, int val, int[] score) throws IOException{
		char a[] = element.toUpperCase().toCharArray();
		ArrayList<Object>temp = new ArrayList<Object>();
		dir = dir.toUpperCase();
		int height  = 15;
		int width = 15;
		int i = 0;
		boolean found = false;
		
		System.out.println(element.length());
		if (dir.equals("H")){
			for (int n = 0; n< a.length;n++){
				if( x==15){
					x=0;
				
				if(board [y][x] == '\0'|| board[y][x]== a[n]){
					board[y][x] = a[n];
					x++;
				}
				else
				if(board[y][x] != '\0'){
					System.out.println("There is already a word place at that position. Please enter a new word");
				}
			}
			}
		}
			
		if (dir.equals("V")){
			for (int n = 0; n< a.length;n++){
				if(board[y][x] =='\0'||board[y][x] == a[n]){
					board[y][x] = a[n];
					y++;
				}
				else
				if(board[y][x] != '\0'){
					System.out.println("There is already a word place at that position. Please enter a new word");
				}
			}
		}
		
		for(int v = 0; v < rack.length; v++){
  			for(int y1 = 0; y1 < a.length; y1++){
  			if(rack[v] == a[y1]){
  					rack[v] = '\0';
  					found = true;
  			if(found == true){
  			}
  			}
  			}
		}
		
		System.out.println("   1  2  3  4  5  6  7  8  9  10  11  12  13  14  15");
		while(i < height){
			if (i <= 8){
				System.out.print(i+1+ "  ");
			}
			if (i >= 9){
			System.out.print(i+1+ " ");
			}
			
			for (int j = 0; j < width; j++){
				if(j <= 8){
					char temp2 = board[i][j];
					System.out.print(temp2 + "  ");
				}
				if (j >= 9){
					System.out.print(board[i][j] + "   ");
				}
			}
			i++;
			System.out.println();
		}
		
	}
	
	public void remove(String element, int y, int x, String dir, String[] playerName, int numPlayers, ArrayList<Object> list, char[] rack, char[][] board, int size,int total, int val, int[] score){
		char a[] = element.toUpperCase().toCharArray();
		dir = dir.toUpperCase();
		int height  = 15;
		int width = 15;
		int i = 0;
		boolean found = false;
		if (dir.equals("H")){
			for (int n = 0; n< a.length;n++){
			if(board[y][x] == a[n]){
				list.add(a[n]);
				board[y][x] = '\0';
				x++;
			}
		}
		}
			
		if (dir.equals("V")){
			for (int n = 0; n< a.length;n++){
			if(board[y][x] == a[n]){
				list.add(a[n]);
				board[y][x] = '\0';
				y++;
			}	
		}
			}
		
		System.out.println("   1  2  3  4  5  6  7  8  9  10  11  12  13  14  15");
		while(i < height){
			if (i <= 8){
				System.out.print(i+1+ "  ");
			}
			
			if (i >= 9){
			System.out.print(i+1+ " ");
			}
			
			for (int j = 0; j < width; j++){
				if(j <= 8){
					
			System.out.print(board[i][j] + "  ");
				}
				
			if (j >= 9){
				System.out.print(board[i][j] + "   ");
			}
			}
			
			i++;
			System.out.println();
		}
		
			for(int v = 0; v < rack.length; v++){
	  			for(int y1 = 0; y1 < a.length; y1++){
	  			if(rack[v] == a[y1]){
	  					rack[v] = '\0';
	  					found = true;
	  			if(found == true){
	  			}
	  			}
	  			}
	}

	}
}


