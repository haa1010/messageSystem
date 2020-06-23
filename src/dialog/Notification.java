package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * @author Tran Thi Hang 20176748
 */
public class Notification extends Shell {
	/**
	 * Launch the application. Create a shell with string s is content inside shell
	 * 
	 * @param s content of Notification
	 */
	public Notification(String s) {
		try {
			Display display = Display.getDefault();
			Notification shell = new Notification(display, s);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display default option
	 * @param s       content inside shell
	 */
	public Notification(Display display, String s) {
		super(display, SWT.SHELL_TRIM);
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(24, 44, 497, 151);
		lblNewLabel.setText(s);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Notification");
		setSize(531, 300);

	}
}
