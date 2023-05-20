package w2d2es2;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;


public class Main2 {
	
	public static File file= new File("C:\\Users\\mrtom\\Documents\\eclipse workspace\\w2dd3\\src\\w2d2es2\\file");
	
	
	public static void main(String[] args) throws IOException {
		
		scrivi("Giulio","20");
		scrivi("Giulia","34");
		scrivi("Jhonny","10");
		if(file.exists()) {
			System.out.println(file.getPath());
		}else {
			System.out.println("error");
		}
	}
	public static void scrivi(String name,String number){
		String roba=name+number;
		
		try (FileWriter writer = new FileWriter("C:\\Users\\mrtom\\Documents\\eclipse workspace\\w2dd3\\src\\w2d2es2\\file")) {writer.write(roba);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
}
