import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {
	public static void main(String args[]) throws IOException{
		String s = FileReader();
		System.out.println(s);
	}
	
	public static String FileReader() throws IOException{
		
		String file = "test.java"; 
		
		String result = null;
        DataInputStream in = null;

        try {
            File f = new File(file);
            byte[] buffer = new byte[(int) f.length()];
            in = new DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { /* ignore it */
            }
        }
        return result;
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
//	    try {
//	        long len = new File(filePath).length();
//	        if (len > Integer.MAX_VALUE) throw new IOException("File "+filePath+" too large, was "+len+" bytes.");
//	        byte[] bytes = new byte[(int) len];
//	        dis.readFully(bytes);
//	        return new String(bytes, "UTF-16");
//	    } finally {
//	        dis.close();
        
        //test
//	    }

		
//		FileInputStream fis = null;
//		//BufferedInputStream bis = null;
//		DataInputStream dis = null;
//		BufferedReader br = null;
//		StringBuffer sb = new StringBuffer();
//		String string;
//		
//		try{
//			fis = new FileInputStream("Test.odt");
//			//bis = new BufferedInputStream(fis);
//			dis = new DataInputStream(fis);
//			br = new BufferedReader(new InputStreamReader(dis));
//			
//			while ((string = dis.readLine()) != null){
//				sb.append(dis.readLine() + "\n");
//			}
//			fis.close();
//			//bis.close();
//			dis.close();
//		}
//		catch(FileNotFoundException e){
//			e.printStackTrace();
//			}catch(IOException e){
//				e.printStackTrace();
//		}
//		return sb.toString();
//	String pathname = "Test.odt";
//    File file = new File(pathname);
//    StringBuilder fileContents = new StringBuilder((int)file.length());
//    Scanner scanner = new Scanner(file);
//    String lineSeparator = System.getProperty("line.separator");
//
//    try {
//        while(scanner.hasNextLine()) {        
//            fileContents.append(scanner.nextLine() + lineSeparator);
//        }
//        return fileContents.toString();
//    } finally {
//        scanner.close();
//    }
	}


}
