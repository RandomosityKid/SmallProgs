
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClickFast {
	static Robot r;
	static final Color cGray = new Color(224, 224, 224);
	static final Color cLoading = new Color(0, 89, 201);
	static final Color cTeal = new Color(14, 160, 202);
	
	public static void main(String arge[]) throws AWTException, InterruptedException {
		Robot r = new Robot();
		Thread t = null;
		
		while (true) {		
			boolean midway = false;
			
			mouseClick();
			
			while (true) {			
				if (colorCheck(cLoading)) {
					midway = true;
				}
				if (midway && (colorCheck(cTeal) || colorCheck(cGray))) {
					break;
				}
			}
			
			if (colorCheck(cGray)) {
				r.mouseMove(1580, 750);
				mouseClick();
				break;
			}
		}
	}
	
	private static void mouseClick() throws AWTException, InterruptedException{
		initRobot();
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(50);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private static boolean colorCheck(Color rgb) throws AWTException{
		initRobot();
		Color color = getColor();
		Color targetColor = rgb;
		return color.equals(targetColor);
	}
	
	private static void initRobot() throws AWTException{
		if (r == null) {
			r = new Robot();
		}
	}
	
	private static Color getColor() throws AWTException{
		initRobot();
		return r.getPixelColor(mLocX(), mLocY());
	}
	
	public static int mLocX(){
		return MouseInfo.getPointerInfo().getLocation().x;
	}
	
	public static int mLocY(){
		return MouseInfo.getPointerInfo().getLocation().y;
	}
}
