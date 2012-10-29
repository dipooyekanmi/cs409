import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


public class LongClassFinder {
		
	public String start(String f){
		int method = countMethods(f);
		int var = countVariables(f);
		return smellDetector(method, var);
	}
	
	public int countMethods(String file){
		int mCount = 0;		
		BufferedReader br = new BufferedReader(new StringReader(file));
		String line;
		
	try{
		 line = br.readLine();
		//Detect if this is the start of a method.
		if((line.contains("public") || line.contains("private") || line.contains("protected")) && line.contains("{")){
			mCount ++;	
			line = br.readLine();
		}
	
		//Detect if start of method but with opening bracket on next line.
		else if(line.contains("public") || line.contains("private") || line.contains("protected")){
			line = br.readLine();
		
				if(line.contains("{")){
					mCount ++;
					line = br.readLine();
				}
				//Nothing on this line, move on.
				else{
					line = br.readLine();
				}
		}
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("Failed to read file");
	}
	
		
		return mCount;
	}
	
	public int countVariables(String file){
		int vCount = 0;
		BufferedReader read = new BufferedReader(new StringReader(file));
		try {
			String line = read.readLine();
			
			//Check if line contains any variables
			if(line.contains("int") || line.contains("String") || line.contains("char") 
					|| line.contains("long") || line.contains("double") || line.contains("float")
					|| line.contains("Boolean") || line.contains("Integer") || line.contains("Character")
					|| line.contains("Array") || line.contains("ArrayList") || line.contains("HashMap") ){
				
				vCount++;
				line = read.readLine();
			}
			
			else{
				read.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read file");
		}
		return vCount;
	}
	
	public String smellDetector(int m, int v){
		int numMethods = m;
		int numVars = v;
		
		if(numMethods >= 15 && numVars >= 20){
			return "Potential Bad Smells: Lots of classes/variables. Are you sure you can't cut this down?";
		}
		
		else if(numMethods >= 15 && numVars < 20){
			return "Potential Bad Smells: You have a lot of methods, are you sure you can't cut them down?";
		}
		
		else if(numMethods < 15 && numVars >= 20){
			return "Potential Bad Smells: You have a large amount of variables: Could this be cut down?"; 
		}
		
		return "No Bad Smells detected.";
	}
}
