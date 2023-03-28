
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CopyPasteTest {
	public static void main(String arge[]) throws AWTException {
		Robot r =  new Robot();
		Thread t;
		
		String toCopy = "Copy Me!";
		
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection sS = new StringSelection(toCopy);
		cb.setContents(sS, sS);
		
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
	}
}
