
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
	static final Color cWhite = new Color(255, 255, 255);
	static final Color cCheck1 = new Color(228, 231, 234);
	static final Color cCheck2 = new Color(243, 243, 243);
	
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
				r.mouseMove(1625, 740);
				mouseClick();
				
				boolean doneLoading = false;
				while (true) {			
					if (colorCheck(cLoading)) {
						doneLoading = true;
					}
					if (doneLoading && (colorCheck(cWhite) ||
							colorCheck(cCheck1) ||
							colorCheck(cCheck2))) {
						break;
					}
				}
				
				moveClick(1860, 290);	//post
				moveClick(1300, 630);	//yes
				moveClick(1470, 640);	//ok
				
				break;
			}
		}
	}
	
	private static void moveClick(int xpos, int ypos) throws InterruptedException, AWTException {
		initRobot();
		Thread.sleep(300);
		r.mouseMove(xpos, ypos);
		mouseClick();
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
