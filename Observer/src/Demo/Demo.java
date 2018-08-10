package Demo;
import editor.Editor;
import listeners.EmailNotificationListener;
import listeners.LogOpenListener;

public class Demo {

	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.events.subscribe("open", new LogOpenListener("file.txt"));
		editor.events.subscribe("save", new EmailNotificationListener("test@goo.com"));

		try {
			editor.openFile("test.txt");
			editor.saveFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
