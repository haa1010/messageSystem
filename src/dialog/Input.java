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

import graph.Database;
import graph.Graph;
import checkform.checkmess.message;

public class Input extends Composite {
	private Text sender;
	private Text message;
	private Text receiver;
	private static Shell shell;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public static void main(String[] args) {
		Display display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		Input dialog = new Input(shell, SWT.NONE);
		shell.setText("Message System");

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public Input(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(23, 45, 71, 20);
		lblNewLabel_1.setText("Sender");

		sender = new Text(this, SWT.BORDER);
		sender.setBackground(SWTResourceManager.getColor(245, 245, 220));
		sender.setBounds(107, 30, 376, 51);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(23, 101, 71, 20);
		lblNewLabel_2.setText("Message");

		message = new Text(this, SWT.BORDER);
		message.setBackground(SWTResourceManager.getColor(245, 245, 220));
		message.setBounds(107, 87, 376, 55);

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(23, 163, 70, 20);
		lblNewLabel_3.setText("Receiver");

		receiver = new Text(this, SWT.BORDER);
		receiver.setBackground(SWTResourceManager.getColor(245, 245, 220));
		receiver.setBounds(107, 147, 376, 57);

		Button clearBtn = new Button(this, SWT.NONE);
		clearBtn.setBounds(107, 208, 174, 42);
		clearBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		clearBtn.setText("CANCEL");
		clearBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					shell.close();
				}
			}
		});

		Button sendBtn = new Button(this, SWT.NONE);
		sendBtn.setBounds(293, 208, 190, 42);
		sendBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		sendBtn.setText("SEND");

		Button graphBtn = new Button(this, SWT.NONE);
		graphBtn.setForeground(SWTResourceManager.getColor(255, 20, 147));
		graphBtn.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					String[] arguments = new String[] { "123" };
					Graph.main(arguments);
				}
			}
		});
		graphBtn.setBounds(107, 264, 364, 51);
		graphBtn.setText("SHOW GRAPH");

		sendBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
//					checkform.checkmess.message.main("ba");
					Notification output;
					String mess = message.getText();
					String receive = receiver.getText();
					String send = sender.getText();
					if (send.equals("") || mess.equals("") || receive.equals("")) {
						output = new Notification("You have to fill out all fields!");
					} else {

						message test = new message(mess, Integer.parseInt(receive), send);
						System.out.println("mess : " + message.getText());
						System.out.println("receiver : " + Integer.parseInt(receiver.getText()));
						System.out.println("sender: " + sender.getText());
						test.exe();
						String res = test.getMessageReturn();
						System.out.println("Result = " + test.getResult() );
						if (test.getResult() == 0) {
							System.out.println("Wrong");
							Database.countWrong();
						}
						output = new Notification(res);
					}
					break;
				}
			}
		});
	}
}
