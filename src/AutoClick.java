
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClick {
	public static void main(String arge[]) throws AWTException, InterruptedException {
		Robot r = new Robot();
		Thread t = null;
		
		while (true) {			
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			t.sleep(50);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			t.sleep(2000);
		}
	}
}
