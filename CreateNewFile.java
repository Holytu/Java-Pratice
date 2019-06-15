import java.util.*;
import java.io.File;
import java.io.IOException;

public class Hello {
	
	public static void main(String[] args) {
		
		try {
			File file1 = new File("C:/Users/user/Desktop/demo1.txt");
			
			if(file1.createNewFile() )
			{
				System.out.print("Success Create a file\n");
			}
			else 
			{
				System.out.print("Fail to create a file\n");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
