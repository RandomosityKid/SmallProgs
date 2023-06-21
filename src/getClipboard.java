
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class getClipboard {
	public static void main(String arge[]) throws UnsupportedFlavorException, IOException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		System.out.println();
		System.out.println("data: " + clipboard.getData(DataFlavor.stringFlavor));
		System.out.println("contents: " + clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor));
	}
}
