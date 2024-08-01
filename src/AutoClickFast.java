
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
	static final Color cCheck3 = new Color(110, 110, 110); //scroll bar
	static final Color cCheck4 = new Color(176, 190, 217); //grey selected highlight
	
	public static void main(String arge[]) throws AWTException, InterruptedException {
		r = new Robot();
		
		r.mouseMove(1440, 780);
		int vagueGray = r.getPixelColor(mLocX(), mLocY()).getRed();
		while (true) {	
			if (colorCheck(cGray) ||
					(vagueGray > 210 && vagueGray < 225)) {
				break;
			}
			
			mouseClick();
			boolean midway = false;
			while (true) {			
				if (colorCheck(cLoading)) {
					midway = true;
				}
				if (midway && (colorCheck(cTeal) || colorCheck(cGray))) {
					break;
				}
			}
		}

		int mouseY = mLocY();
		r.mouseMove(1620, mouseY);
		mouseClick();

		boolean doneLoading = false;
		while (true) {			
			if (colorCheck(cLoading)) {
				doneLoading = true;
			}
			if (doneLoading && (colorCheck(cWhite) ||
					colorCheck(cCheck1) ||
					colorCheck(cCheck2) ||
					colorCheck(cCheck3) ||
					colorCheck(cCheck4))) {
				break;
			}
		}

		moveClick(1860, 260);	//post
		moveClick(1300, 610);	//yes
		moveClick(1470, 620);	//ok
		moveClick(1470, 620);	//ok catch for other position
	}
	
	private static void moveClick(int xpos, int ypos) throws InterruptedException, AWTException {
		initRobot();
		Thread.sleep(200);
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
