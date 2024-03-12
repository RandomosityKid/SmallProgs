
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MarchReports {
	static Robot r;
	static Thread t;
	
	public static void main(String arge[]) throws AWTException, InterruptedException {
		r = new Robot();
		r.mouseMove(1800, 170);
		mClick();
		for (int i = 0; i < 5; i++) {
			closeWindow();
			mClick();
			enter();
			if (i == 4) {
				wait(100);
				break;
			}
			wait(200);
		}
		wait(350);
		closeWindow();
		wait(200);
		closeWindow();
		wait(200);
		closeWindow();
	}
	
	private static void initRobot() throws AWTException{
		if(r == null){
			r = new Robot();
		}
	}
	
	private static void wait(int speed) throws InterruptedException{
		t.sleep(speed);
	}
	
	private static void mClick() throws InterruptedException, AWTException {
		initRobot();
		wait(300);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		wait(100);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private static void closeWindow() throws AWTException, InterruptedException{
		initRobot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_W);
		r.keyRelease(KeyEvent.VK_W);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	private static void enter() throws AWTException, InterruptedException{
		initRobot();
		wait(800);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
}

