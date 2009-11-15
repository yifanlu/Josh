import com.yifanlu.*;

// This examples demostrates output & buffers

public class ScreenBuffers {
    public static void main(String[] args) {
 		ConsoleBuffer newBuff = new ConsoleBuffer();
 		Josh.out.setOutputColor(ConsoleColor.BLUE(), ConsoleColor.GREEN());
 		for(int x = 0; x < 26; x++)
 			Josh.out.println ("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
 			
 		ConsoleColor[] temp = Josh.outBuffer.readConsoleOutputAttribute(new ConsoleCoord(1,1),9);
 		newBuff.outBuffer.writeConsoleOutputAttribute(temp,new ConsoleCoord(1,1),9);
 		
 		newBuff.out.setOutputColor(ConsoleColor.GREEN(), ConsoleColor.BLUE());
 		for(int x = 0; x < 26; x++)
 			newBuff.out.println ("ZYXWVUTSRQPONMLKJIHGFEDCBA");
 		Josh.in.readLine();
 		Josh.setConsoleActiveScreenBuffer(newBuff);
    }
}
