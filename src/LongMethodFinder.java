import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class LongMethodFinder{
	int numLines;
	int limit;
	
	public LongMethodFinder(){
		numLines = 0;
		limit = 50;
	}
	
	public String detectSmells(String f){
		String file = f;
		BufferedReader br = new BufferedReader(new StringReader(file));
		String line;
		ArrayList<Boolean> smellResults = new ArrayList<Boolean>();
		ArrayList<Integer> badMethods = new ArrayList<Integer>();
		boolean counting = false;
		int obCount = 0;
		int cbCount = 0;
		String tFail = "Failed to read file";
		try {
			line = br.readLine();
			
			while (line != null){
				
				
				//Are we counting a method or looking for one?
				if(counting == true){
					//Count the lines in the method.
					while(cbCount != obCount){
						//Is it a comment line? Don't count it.
						if (line.startsWith("//")){
							line = br.readLine();
						}
						//Does the line have any code in it?
						else if(line.length() != 0){
							if(line.contains("{")){
								obCount ++;
							}
							else if(line.contains("}")){
								cbCount ++;
							}
							numLines ++;
							line = br.readLine();
						}
						//Spacing line, don't count it.
						else if (line.length() == 0){
							line = br.readLine();
						}
					}
					
					//If the method is too long.
					if(numLines > limit){
						smellResults.add(false);
					}
					
					//Method is within limit.
					else{
						smellResults.add(true);
					}
					obCount = 0;
					cbCount = 0;
					numLines = 0;
					
				}
				
				//Searching for a method.
				else{
					//Detect if this is the start of a method.
					if((line.contains("public") || line.contains("private") || line.contains("protected")) && line.contains("{")){
							obCount ++;
							numLines++;
							line = br.readLine();
							counting = true;
					}
				
					//Detect if start of method but with opening bracket on next line.
					else if(line.contains("public") || line.contains("private") || line.contains("protected")){
						line = br.readLine();
					
							if(line.contains("{")){
								obCount ++;
								numLines++;
								line = br.readLine();
								counting = true;
							}
							//Nothing on this line, move on.
							else{
								line = br.readLine();
							}
					}
				}		
		}
	} 
		catch (IOException e) {
			e.printStackTrace();
			return tFail;
		}
		
		//Find out which, if any, methods are bad.
		for(int i = 0; i < smellResults.size(); i++){
			boolean temp = smellResults.get(i);
			if(temp == false){
				badMethods.add(i);
			}
		}
		
		if(badMethods.size() > 0){
			String failDetails = "Bad smells Detected. Check Methods: ";
			for(int i = 0; i < badMethods.size(); i++){
				if(i == 0){
					Integer ti = badMethods.get(i);
					String ts = ti.toString();
					failDetails.concat(ts);
				}
				
				else{
					failDetails.concat(", ");
					Integer ti = badMethods.get(i);
					String ts = ti.toString();
					failDetails.concat(ts);
				}
			}
			return failDetails;
		}
		else{
			return "No bad smells detected";
		}
	}
}