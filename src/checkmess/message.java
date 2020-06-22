package checkmess;

import java.util.ArrayList;

import checkmess.automata.Automata;
import response.Response;
import graph.Database;

public class message {
	private String message;
	private int receiver;
	private String messageReturn = null;// message send back to receiver
	private String PhoneNumber;
	private int result = 0;

	private boolean isTrueReceiver = false;

	public message(String message, int receiver, String phone) {
		try {
			message = message.trim();
		} catch (NullPointerException e) {
			System.out.print("Message is not null\n");

		} finally {

			this.message = message != null ? message + ' ' : " ";
			this.receiver = receiver;
			this.PhoneNumber = phone;
			if (this.receiver == 8011 || this.receiver == 8033 || this.receiver == 8088)
				this.isTrueReceiver = true;
		}
	}

	public void checkReceiver(int state, int index, String gender) {
		/*
		 * result 2: send girl's number, result 3: send boy's number, result 5: accept
		 * del result 9: rejoin girl result, 10 : rejoin boy, result 12: accept change
		 * state = -1: not use sevice yet, init state = 1 del = 0 rejoin = 2 change = 3
		 */
		if (index == -1) {
			if (this.receiver != 8088) {
				this.messageReturn = "You should send \"Frd B\" or \"Frd G\"to 8088 ";
				this.result = 0;
				return;
			} else if (state == 0) {
				this.messageReturn = "Frd missing";
				this.result = 0;
				return;
			} else if (state != 2 && state != 3) {
				this.messageReturn = "B/G missing";
			} else if (state == 2) {
				if (this.receiver == 8088) {
					{
						this.result = 2;
						return;
					}

				} else
					this.messageReturn = "you must send to 8088";
			} else if (state == 3) {
				if (this.receiver == 8088) {
					this.result = 3;
					return;
				} else {
					this.messageReturn = "you must send to 8088";
					this.result = 0;
					return;
				}
			}

		} else if (index == 0) {
			if (this.receiver != 8011) {
				this.result = 0;
				this.messageReturn = "You must rejoin system. Please send \' Frd RJ B(or G)\' to 8011 ";
				return;
			}
			if (state == 0) {
				this.messageReturn = "Frd missing";
				if (this.receiver != 8011) {
					this.messageReturn += " You must send to 8011";
					this.result = 0;
					return;
				}
			} else if (state != 10 && state != 9 && state != 8) {
				this.messageReturn = "RJ missing.You should send \"Frd RJ B\" or \"Frd RJ G\" to 8011";
				this.result = 0;
				return;
			} else if (state == 8) {
				this.messageReturn = "B/G missing";
				if (this.receiver != 8011)
					this.messageReturn += " You must send to 8011";
				return;

			} else if (state == 10) {
				if (this.receiver == 8011) {
					this.result = 10;
					return;
				} else {
					this.messageReturn = "you must send to 8011";
					this.result = 0;
					return;
				}
			} else if (state == 9) {
				if (this.receiver == 8011) {
					this.result = 9;
					return;
				}
			}

		} else if (index == 1 || index == 2 || index == 3) {
			if (state == 0) {
				this.messageReturn = "Frd missing";
				this.result = 0;
				return;
			} else if (state == 5 && this.receiver != 8011) {
				this.messageReturn = "You must send to 8011";
				this.result = 0;
				return;
			} else if (state == 12 && this.receiver != 8033) {
				this.messageReturn = "You must send to 8033";
				this.result = 0;
				return;
			} else if ((state == 2 || state == 3) && this.receiver != 8088) {
				this.messageReturn = "You must send to 8088";
				this.result = 0;
				return;
			}

			else if (gender == "boy") {

				if (this.receiver == 8088) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "B/G missing";
						this.result = 0;
						return;
					} else if (state == 2) {

						this.result = 2;
						return;

					}

				} else if (this.receiver == 8011) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "del missing";
						this.result = 0;
						return;
					} else if (state == 5) {

						this.result = 5;
						return;

					}

				} else if (this.receiver == 8033) {
					if (state != 5 && state != 12 && state != 2) {
						this.messageReturn = "chg missing. You must send 'Frd chg' to 8033";
						this.result = 0;
						return;
					} else if (state == 12) {
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
					} else if (state == 3) {

						this.result = 3;
						return;

					}

				} else if (this.receiver == 8011) {
					if (state != 5 && state != 12 && state != 3) {
						this.messageReturn = "del missing. You must send 'Frd del' to 8011";
						this.result = 0;
						return;
					} else if (state == 5) {

						this.result = 5;
						return;

					}

				} else if (this.receiver == 8033) {
					if (state != 5 && state != 12 && state != 3) {
						this.messageReturn = "chg missing. You must send 'Frd chg' to 8033";
						this.result = 0;
						return;
					} else if (state == 12) {
						this.result = 12;
						return;
					}

				}
			}
		}
		this.result = 0;
		this.messageReturn = "wrong format";
		return;
	}

	public void exe() {
		/**
		 * This exe() method uses to handle message and give final result 
		 */
		
		message mes = this;
		Automata x = new Automata();
		CheckPhone phone = new CheckPhone(mes.PhoneNumber);
		/**
		 * Check if customer is valid or not 
		 */
		if (!phone.isValidE123()) {
			mes.setMessageReturn("Your phone number is wrong!");
			return;
		}

		/** 
		 * Get information about customer from database
		 * Customer who hasn't join system has state = -1
		 */
		
		Response user = new Response();
		ArrayList<String> s = user.checkUser(phone.getPhone());
		String gender = "";

		if (Integer.parseInt(s.get(2)) != -1) {
			if (s.get(1).equals("G"))
				gender = "girl";
			else
				gender = "boy";
		}

		mes.checkReceiver(x.result(mes.message), Integer.parseInt(s.get(2)), gender);
		switch (mes.getResult()) {
		/**
		 * Hanlde situations of result and update Database corresponding to request
		 * Case 2 : send 3 number of Girl
		 * Case 3 : send 3 number of Boy
		 * Case 5 : Customer deletes, disconnect to system, change state in Database
		 * Case 9 : Customer rejoin as a Girl
		 * Case 10 : Customer rejoin as a Boy
		 * Case 12 : Customer change his/her gender
		 */
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