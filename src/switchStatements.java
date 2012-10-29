import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.objectweb.asm.*;

public class switchStatements {
	
	//ClassWriter cw = new ClassWriter(0);
	
	
	public int calc(String file){
		String pattern = "switch";
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
