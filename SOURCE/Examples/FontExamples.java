/**
 * Font changing
 */
 
import com.yifanlu.*;

public class FontExamples {
    public static void main(String[] args) {
    	for(int x = 0; x<10; x++)
    	{
    		Josh.outBuffer.setCurrentConsoleFont(x);
    		Josh.out.println("This is font #" + x);
    		Josh.in.readLine();
    	}
    }
}
