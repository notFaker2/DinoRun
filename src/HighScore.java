import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
	 int hs=0;
	 public int getvalue() throws IOException {
			
			try {
		        FileReader reader = new FileReader("data.txt");
		        BufferedReader bufferedReader = new BufferedReader(reader);
		        String line = bufferedReader.readLine();
		        if (line != null) {
		            hs = Integer.parseInt(line);
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
				return hs;
		}
		public void upvalue(int a) throws IOException {
			if(a>hs) {
				hs=a;
			}
			FileWriter writer = new FileWriter("data.txt");
			writer.write(Integer.toString(hs));
			writer.close();
		}
	
}
