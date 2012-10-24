
import java.io.*;

public class FileReaderDiv {

	@SuppressWarnings("deprecation")
	public String getFileAsString(File file){ FileInputStream fis = null;
	BufferedInputStream bis = null;
	DataInputStream dis = null;
	StringBuffer sb = new StringBuffer();
	try {
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		dis = new DataInputStream(bis);

		while (dis.available() != 0) {
			sb.append( dis.readLine() +"\n");
		}
		fis.close();
		bis.close();
		dis.close();

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return sb.toString();
	}
	public static void main(String[] args) {

		File file = new File("LongParameter.java");
		FileReaderDiv fd = new FileReaderDiv();
		String s = fd.getFileAsString(file);
		LongParameter lp = new LongParameter(s);
	}
}