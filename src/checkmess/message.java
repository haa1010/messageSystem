/*
 * @author: hue,hang
 * */
package checkmess;
import java.util.ArrayList;
import checkmess.automata.Automata;
import response.Response;
import graph.Database;
public class Message {
	private String message;//message sender send
	private int receiver;// receiver number
	private String messageReturn = null;// message send back to receiver
	private String phoneNumber;// phone number of sender
	private int result = 0;// result of checking message

	private boolean isTrueReceiver = false;
/*
 * constructor
 * @param message, receiver,phone
 * 
 * */
	public Message(String message, int receiver, String phone) {
		//check message is null or not
		try {
			message = message.trim();
		} catch (NullPointerException e) {
			System.out.print("Message is not null\n");

		} finally {
			//add space to end of message
			this.message = message != null ? message + " " : " ";
			this.receiver = receiver;
			this.phoneNumber = phone;
			
			// set isTrueReceiver
			if (this.receiver == 8011 || this.receiver == 8033 || this.receiver == 8088)
				this.isTrueReceiver = true;
		}
	}

	/*
	 * check message: check valid message corresponding to receiver and state using service, set messageReturn and result
	 * @param state of message, index: state of service, gender
	 * @return: result>0 is valid, result=0 means wrong message, set messageReturn
	 * 
	 * 
	 * */
	public void checkMessage(int state, int index, String gender) {
		/* result value:
		 * result =2: send girl's number
		 *  result =3: send boy's number
		 *  result =5: accept del 
		 *  result =9: rejoin girl 
		 *  result= 10 : rejoin boy
		 *   result=12: accept change
		 *   index value:
		 * index = -1: not use sevice yet
		 *  using service index = 1 
		 *   after delete service index = 0 
		 *  after rejoin index = 2
		* after change gender index = 3
		 */
		
		/*
		 * case index=-1 ,user can send:
		 *   Frd B/G to 8088  
		  */
		if (index == -1) {
			
			// wrong receiver
			if (this.receiver != 8088) {
				this.messageReturn = "You should send \"Frd B\" or \"Frd G\"to 8088 ";
				this.result = 0;
				return;
			} else  
				if (state == 0) {
				this.messageReturn = "Frd missing";
				this.result = 0;
				return;
			} else
				if (state != 2 && state != 3) {
				this.messageReturn = "B/G missing";
			} else 
				// state =2 : message is true form receive number of girl "Frd G"
				if (state == 2) {
				if (this.receiver == 8088) {
					{
						this.result = 2;
						return;
					}

				} else
					this.messageReturn = "you must send to 8088";
			} else
				// state =3 : message is true form receive number of boy "Frd B"
				
				if (state == 3) {
				if (this.receiver == 8088) {
					this.result = 3;
					return;
				} else {
					this.messageReturn = "you must send to 8088";
					this.result = 0;
					return;
				}
			}

		} else 
			/*
			 * index=0 means after delete sevice, user can send:
			 * Frd RJ B/G to 8011
			 * */
			if (index == 0) {
				
				// if wrong receiver
			if (this.receiver != 8011) {
				this.result = 0;
				this.messageReturn = "You must rejoin system. Please send \' Frd RJ B(or G)\' to 8011 ";
				return;
			}
			
			// if true receiver
			else
			if (state == 0) {
				this.messageReturn = "Frd missing";
					this.result = 0;
					return;
				
			
			} else 
				/*
				 * if  state !=10,9,8 means RJ missing
				 * */
				
				if (state != 10 && state != 9 && state != 8) {
				this.messageReturn = "RJ missing.You should send \"Frd RJ B\" or \"Frd RJ G\" to 8011";
				this.result = 0;
				return;
			} else //state==8  mean B/G missing
				if (state == 8) {
				this.messageReturn = "B/G missing";
				return;
			} else 
				// message is in right form rejoin as a boy : Frd RJ B
				if (state == 10) {
					this.result = 10;
					return;
			} else
				// message is in right form rejoin as a girl : Frd RJ G 
				if (state == 9) {
					this.result = 9;
					return;
			}
		} else 
			
			/*
			 * if index=1,2,3
			 * */
			if (index == 1 || index == 2 || index == 3) {
				/*
				 * if state=0
				 * */
			if (state == 0) {
				this.messageReturn = "Frd missing";
				this.result = 0;
				return;
			} else 
				// if state =5&& receiver !=8011 means wrong receiver for state 5
				if (state == 5 && this.receiver != 8011) {
				this.messageReturn = "You must send to 8011";
				this.result = 0;
				return;
			} else
				// if state =12&& receiver !=8033 means wrong receiver for state 12
				if (state == 12 && this.receiver != 8033) {
				this.messageReturn = "You must send to 8033";
				this.result = 0;
				return;
			} else 
				// if (state =2||state=3)&& receiver !=8088 means wrong receiver for state 2,3
				if ((state == 2 || state == 3) && this.receiver != 8088) {
				this.messageReturn = "You must send to 8088";
				this.result = 0;
				return;
			}
// case gender= boy
			else if (gender == "boy") {
				
				if (this.receiver == 8088) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "B/G missing";
						this.result = 0;
						return;
					} else 
						// state=2&& receiver=8088 means message in right form to receive phone number of girl
						if (state == 2) {
						this.result = 2;
						return;
					}
				} else 
					if (this.receiver == 8011) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "del missing";
						this.result = 0;
						return;
					} else
						// state=5& receiver=8011 : message in right form to delete service: Frd del
						if (state == 5) {

						this.result = 5;
						return;

					}

				} else if (this.receiver == 8033) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "chg missing. You must send 'Frd chg' to 8033";
						this.result = 0;
						return;
					}
					else // state=12& receiver =8033 : message in right form to change gender
						if (state == 12) {
						this.result = 12;
						return;
					}

				}

			} else if (gender == "girl") {
				if (this.receiver == 8088) {
					if (state != 5 && state != 12 && state != 3) {
						this.messageReturn = "B/G missing. You must send 'Frd B' to 8088";
						this.result = 0;
						return;
					} else
						// state=3&& receiver=8088 means message in right form to receive phone number of boy

						if (state == 3) {

						this.result = 3;
						return;

					}

				} else if (this.receiver == 8011) {
					if (state != 5 && state != 12 && state != 3) {
						this.messageReturn = "del missing. You must send 'Frd del' to 8011";
						this.result = 0;
						return;
					} else 
						//state =5 & receiver=8011 : message in right form of delete
						if (state == 5) {
						this.result = 5;
						return;

					}

				} else if (this.receiver == 8033) {
					if (state != 5 && state != 12 && state != 3) {
						this.messageReturn = "chg missing. You must send 'Frd chg' to 8033";
						this.result = 0;
						return;
					} else // state=12& receiver=8033 : message in right form to change gender 
						if (state == 12) {
						this.result = 12;
						return;
					}

				}
			}
		}
		// else message in wrong format
		this.result = 0;
		this.messageReturn = "wrong format";
		
	}
/* exe() to run checking valid message, update database, get messageReturn to client
 * 
 * */
	public void exe() {
		Message mes = this;
		Automata x = new Automata();
		CheckPhone phone = new CheckPhone(mes.phoneNumber);

		// customer's phone number is invalid
		if (!phone.isValidPhone()) {
			mes.setMessageReturn("Your phone number is wrong!");
			return;
		}

		// get information about customer from database
		Response user = new Response();
		ArrayList<String> s = user.checkUser(phone.getPhone());
		String gender = "";

		// customer who hasn't join system has state = -1
		if (Integer.parseInt(s.get(2)) != -1) {
			if (s.get(1).equals("G"))
				gender = "girl";
			else
				gender = "boy";
		}
// check message, receiver with state of phone number 
		mes.checkMessage(x.result(mes.message), Integer.parseInt(s.get(2)), gender);
		switch (mes.getResult()) {
		case 2:
		case 3:
			// send 3 number
			// result = 2 = send girl's number => gender = B
			if (mes.result == 2)
				Database.createNewUser(phone.getPhone(), "B");
			else
				Database.createNewUser(phone.getPhone(), "G");

			ArrayList<String> str = Database.addSentList(phone.getPhone());

			if (str.isEmpty())
				// customers had received all possible phone numbers for their gender
				mes.setMessageReturn("You have received all possible numbers.");
			else {
				String tmp = "Numbers for you:\n";
				for (String sub_str : str) {
					tmp += sub_str + "\n";
				}
				mes.setMessageReturn(tmp);
			}
			break;
		case 5:
			// del
			Database.changeState(phone.getPhone(), 0);
			mes.setMessageReturn("You have disconnected successfully to our system!");
			break;
		case 9:
			// rejoin girl
			Database.rejoin(phone.getPhone(), "G");
			mes.setMessageReturn("You have rejoined as a Girl successfully!");
			break;
		case 10:
			// rejoin boy
			Database.rejoin(phone.getPhone(), "B");
			mes.setMessageReturn("You have rejoined as a Boy successfully!");
			break;
		case 12:
			// change gender
			Database.changeGender(phone.getPhone());
			mes.setMessageReturn("You have changed your gender successfully!");
			break;
		default:
			break;
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageReturn() {
		return messageReturn;
	}

	public void setMessageReturn(String messageReturn) {
		this.messageReturn = messageReturn;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}