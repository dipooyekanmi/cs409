import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class dataClass {
	
	public int calc(String file){
	String pattern = "get|set";
	Pattern p = Pattern.compile(pattern);
	Matcher m = p.matcher(file);
	int count = 0;
	for(int i = 0; i < file.length(); i++){
		if(m.find())
			count++;
	}
	return count;

}
}
