import java.util.ArrayList;
import java.util.Random;
//import java.util.EmptyStackException;
import java.util.Scanner;
//import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringIndexOutOfBoundsException;


public class placeTile {
	public placeTile(String element, int y, int x, String dir, String[] playerName, int numPlayers, ArrayList<Object> list, char[] rack, char[][] board, int size, int total, int val, int[] score) throws IOException{
	turn(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
	}
	public void menu(){
		 System.out.println();
	        System.out.println("What would you like to do?");
	        System.out.println("1:   to place tile(s) on the board");
	        System.out.println("2:   to challenge a word placed by an oppenent");
	        System.out.println("3:   to swap some tiles from your rank");
	        System.out.println("4:   to end your turn");  
	        System.out.println("5:   to end the game");  
	        System.out.println("6:   to view to high scores");
	}
	public void turn(String element, int y, int x, String dir, String[] playerName, int numPlayers, ArrayList<Object> list, char[] rack, char[][] board, int size, int total, int val, int[] score) throws IOException{
	score s = new score(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
	board b = new board(element, x, y, dir,playerName, numPlayers, list, rack, board, size,total, val, score);
	int z = 0;
	score = new int[4];
	boolean found = false;
	int turn = 0;
	while(z < numPlayers){
	for(int l =0; l < numPlayers; l++){
		for(int a = 0; a <rack.length; a++){
         	if (rack[a] == '\0'){
         		Random ran  = new Random();
    			int r = ran.nextInt(list.size());
    			rack[a] = ((Character) list.remove(r)).charValue();
         	}
         	}
	//System.out.println(z);
	System.out.println("Player " + playerName[z] + "'s turn");
	Scanner scanner = new Scanner(System.in);
	char choice = 'x';
	System.out.println(rack[z]);
	while (choice != '6') {
		try {
	        menu();
	        System.out.println();
	        System.out.println(rack);
	        choice = scanner.nextLine().charAt(0);
	       // Character[] rack;
			switch (choice){
	        case '1': 
	        char a[] = element.toUpperCase().toCharArray();
               System.out.println("Please enter the word you wish to place on the board: ");
	             element = scanner.nextLine();
	             element.toUpperCase();
	             System.out.println("Do you wish this to be a verticle or horizontal word. Enter your choose as either a 'v' or a 'h': ");
	             dir = scanner.nextLine();
	             dir.toUpperCase();
	             System.out.println("Enter starting x coordinate of the word: ");
	             x = scanner.nextInt();
	             x = x-1;
	             System.out.println("Enter starting y coordinate of the word: ");
	             y = scanner.nextInt();
	             y = y-1;
	             for(int v = 0; v < rack.length; v++){
	      			for(int y1 = 0; y1 < a.length; y1++){
	      			if(rack[v] == a[y1]){
	      					rack[v] = '\0';
	      					found = true;
	      			if(found == true){
	      				turn(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
	      			}
	      			}
	      			}
	 		}
	             for(int v = 0; v < rack.length; v++){
	      			for(int y1 = 0; y1 < a.length; y1++){
	      			if(rack[v] == a[y1]){
	      					rack[y1] = '\0';
	      			}
	      			}
	      			}	
	             b.place(element, y, x, dir, playerName, numPlayers, list, rack, board, size, total, val, score);
	             s.playerScore(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
	             System.out.println(total);
	            
	             /*for(int b = 0; b <rack.length; b++){
			         	if (rack[b] == '\0'){
			         		Random ran  = new Random();
			    			int r = ran.nextInt(list.size());
			    			rack[b] = ((Character) list.remove(r)).charValue();
			         	}
	             }*/		        		
	            turn++;
	        	
	             break;
	        case '2' :
	        	try {
	        		FileReader fr = new FileReader("foldoc_terms.txt");
	        		BufferedReader reader = new BufferedReader(fr);
	        		String line = reader.readLine();
	        		Scanner scan = null;
	        		boolean found1 = false;
	        		Scanner keyboard = new Scanner(System.in);
	        		System.out.println("Please enter the word you would like to check against FOLDOC");
	        	    String search = keyboard.nextLine();
	        	    System.out.println("Do you wish this to be a verticle or horizontal word. Enter your choose as either a 'v' or a 'h': ");
		             dir = scanner.nextLine();
		             dir.toUpperCase();
		             System.out.println("Enter starting x coordinate of the word: ");
		             x = scanner.nextInt();
		             x = x-1;
		             System.out.println("Enter starting y coordinate of the word: ");
		             y = scanner.nextInt();
		             y = y-1;
	        		while (line != null && found1 == false) {
	        		//line = reader.readLine();
	        		   scan = new Scanner(line);
	        	    	if(line.equals(search)){
	        	    		found1 = true;
	        	    		System.out.println("word found");	        		
	        	    	}
	        	    	else {
	        	    	line = reader.readLine();
	        		}
	        	    	}
	        		reader.close();
	        		if (found1 == false){
	        			System.out.println("word not found");
	        			search = element;
	        			//b.remove(element, y, x, dir, playerName, numPlayers, list, rack, board, size, total, val, score);
	        			//s.minusScore(element, y, x, dir, playerName, numPlayers, list, rack, board, size, total, val, score);
	        		}
	        		}
	        		catch (FileNotFoundException e) {
	        		System.out.println("The foloc dictionary could not be found, please check you have the foldoc_terms.txt file");
	        		}
	        		catch (IOException e) {
	        		System.out.println("There has been a problem reading from file, please re-run");
	        		}
	        		
	        		break;
	        case '3' :
	           String c = "";
	           boolean discovered = false;
	         	System.out.println("Please enter the character that you wish to swap");
	         	c = scanner.nextLine();
	         	char[] ti = c.toUpperCase().toCharArray();
	         	for(int i = 0; i< rack.length; i++){
	         	for (int j = 0; j<ti.length; j++){
	         	if (rack[i] == ti[j]){
	         		list.add(rack[i]);
	         		System.out.println(rack[i]);
	         		rack[i] = '\0';
	         		discovered = true;
	         		if(discovered == true)
	         			turn(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
	         	}
	         	}
	         	}
	         	System.out.println(list);	         	
	     //    	System.out.println("Your new tiles are " + list);
	             break ;
	             
	        case '4' :
			    System.out.println("Turn ended");
			    z++;
			    if (z == numPlayers)
			    	z = 0;
				break;	
	        case '5' :
	        	String highscores = "";
	        	System.out.println("Game ending");
	        	
	        /*	InputStreamReader convert = new InputStreamReader(System.in);
	            BufferedReader stdin = new BufferedReader(convert);
	            String instr;
	            File outfile = new File("high_scores.txt");
	            boolean append = true;
	            try
	            {
	                if (outfile.exists())
	                {
	                    System.out.print("File already exists: do you want to overwrite ");
	    		System.out.println("or append (enter o or a)");
	                    instr = stdin.readLine();
	                    if (instr.equals("o"))
	                        append = false;
	                }
	                FileWriter fout = new FileWriter("high_scores.txt",append);
	                PrintWriter fileout = new PrintWriter(fout,true);
	            //    System.out.print("Enter a string: ");
	                
	                instr = (String)total;
	                fileout.println(instr);
	                fileout.flush();
	                fileout.close();
	            }	
	            */            

	        	try {
	        	    BufferedWriter out = new BufferedWriter(new FileWriter("high_scores.txt"));
	        		out.write(total);
	        	    out.close();
			}
		
	        	  
	        	 catch (IOException e) {
	        	}
	        	 System.out.println("High scores have been successfully saved");

	        	//s.winner(element, y, x, dir, playerName, numPlayers, list, rack, board, size, total, val, score);
	        	System.exit(1);{
	        	}
	        	break;
	            
			
	      
	            
			
		case '6' :
			
			FileReader fr = new FileReader("high_scores.txt");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			Scanner scan = null;
			String highscores1 = "";	
			while (line != null) {
			scan = new Scanner(line);
			highscores1 = scan.nextLine();
			System.out.println(line);
			line = reader.readLine();
			}
			reader.close();
			}		
		break;
			}
			
		
		    	
		catch (FileNotFoundException e) {
			System.out.println("No highscore file exists, please complete a game first ");
			}
			catch (IOException e) {
			System.out.println("There has been a problem reading from file");
			}
		
		
	
			
		// end of try
		catch (StringIndexOutOfBoundsException e)
		{
			System.out.println("Please enter a value corresponding to the task you wish to carry out");
			System.out.println();
			turn(element, x, y, dir,playerName, numPlayers, list, rack, board, size, total, val, score);
		
	      } 
		}
	}
	
}
	}
	
}


