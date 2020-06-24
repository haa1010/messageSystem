package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import checkmess.Message;
import graph.*;

/**
 * 
 * @author Tran Thi Hang 20176748
 */

public class Input extends Composite {
	private Text sender;
	private Text message;
	private Text receiver;
	private static Shell shell;

	/**
	 * Create the composite shell for customer typing in and update data for graph
	 * at 4pm everyday
	 * 
	 * @param args default
	 * @see Update
	 */
	public static void main(String[] args) {
		Display display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		Input dialog = new Input(shell, SWT.NONE);
		shell.setText("Message System");

		shell.pack();
		shell.open();
		// run update method to update data for graph at 4pm everyday
		Update.main("abc");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This is the constructor which uses to construct an Input instance for
	 * handling. <br> There are 3 buttons in the dialog : <br> Cancel Button : close dialog. <br>
	 * Graph Button : display system's statistic graph. <br>Send Button : before send,
	 * we need to check that customer had filled out all the fields we need. <br> Then
	 * construct a message instance send to system and get a notification as a
	 * result. If result is that message is wrong, then increase number of wrong
	 * message in database
	 * 
	 * @param parent default
	 * @param style  default
	 * @see Graph
	 * @see Message
	 * @see Notification
	 * @see Database
	 */
	public Input(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(23, 45, 71, 20);
		lblNewLabel_1.setText("Sender");

		// get sender from label
		sender = new Text(this, SWT.BORDER);
		sender.setBackground(SWTResourceManager.getColor(245, 245, 220));
		sender.setBounds(107, 30, 376, 51);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(23, 101, 71, 20);
		lblNewLabel_2.setText("Message");

		// get message from label
		message = new Text(this, SWT.BORDER);
		message.setBackground(SWTResourceManager.getColor(245, 245, 220));
		message.setBounds(107, 87, 376, 55);

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(23, 163, 70, 20);
		lblNewLabel_3.setText("Receiver");

		// get receiver from label
		receiver = new Text(this, SWT.BORDER);
		receiver.setBackground(SWTResourceManager.getColor(245, 245, 220));
		receiver.setBounds(107, 147, 376, 57);

		// Click cancel button to close shell
		Button cancelBtn = new Button(this, SWT.NONE);
		cancelBtn.setBounds(107, 208, 174, 42);
		cancelBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		cancelBtn.setText("CANCEL");
		cancelBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					shell.close();
				}
			}
		});

		// display graph
		Button graphBtn = new Button(this, SWT.NONE);
		graphBtn.setForeground(SWTResourceManager.getColor(255, 20, 147));
		graphBtn.setBounds(107, 264, 364, 51);
		graphBtn.setText("SHOW GRAPH");
		graphBtn.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					Graph.main("run");
					break;
				}
			}
		});

		Button sendBtn = new Button(this, SWT.NONE);
		sendBtn.setBounds(293, 208, 190, 42);
		sendBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		sendBtn.setText("SEND");

		// send data to system for handling
		sendBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					/**
					 * Declare 3 variables mess, receive and send to convert customer's input into
					 * String and create a message instance to handles
					 */
					Notification output;
					String mess = message.getText();
					String receive = receiver.getText();
					String send = sender.getText();

					// check if customer leaves blank field
					if (send.equals("") || mess.equals("") || receive.equals("")) {
						output = new Notification("You have to fill out all fields!");
					} else {
						try {

							Message test = new Message(mess, Integer.parseInt(receive), send);
							test.exe();
							String res = test.getMessageReturn();

							// if message is wrong, increase number of wrong message in database
							if (test.getResult() == 0) {
								Database.countWrong();
							}
							output = new Notification(res);
						} catch (Exception exc) {
							output = new Notification("Wrong format. Try again!");
						}
					}
					break;
				}
			}
		});
	}
}
