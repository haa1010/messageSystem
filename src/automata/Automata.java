/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author hue
 */
package automata;
public class Automata {
	public int cur = -1;// to find reached state
	public static State0[] listState = new State0[] { new State0(), new State1(), new State2(), new State3(),
			new State4(), new State5(), new State6(), new State7(), new State8(), new State9(), new State10(),
			new State11(), new State12(), new State13(), new State14(), new State15(), new State16(), new State17(),
			new State18(), new State19(), new State20(), new State21(), new State22(), new State23(), new State24(),
			new State25(), new State26() };
	/*
	 * getState()
	 * @param integer
	 * @return listState[integer] to find next State
	 * */
	public State0 getState(int c) {
		return listState[c];
	}
	public Automata() {}
	/**
	 * @param args the command line arguments
	 */
	/*
	 * @param string
	 * @return final state
	 * result() to find reach final state of a string
	 
	 * */
	public int result(String str) {
		/*
		 *state=0: wait for Frd
		 *state =1: wait for B/G/R/d/c
		 *state=2: ok receiver girl
		 *state=3: ok receive boy
		 *state=4: wait for del
		 *state =5: ok del
		 *state=6:
		 *state=7: wait for RJ
		 *state=8: wait for B/G
		 *state=9:ok rejoin girl
		 *state=10: ok rejoin boy
		 *state=11: wait for change
		 *state=12: ok change
		 * 
		 */
		int nextState = -7;
		State0 s = new State0();
		nextState = s.getNext(str.charAt(0), -1);
		Automata x = new Automata();
		//get state of first character
		s = x.getState(nextState);
		 if (s.state > x.cur)
				x.cur = s.state;
		 // get state of another character
		for (int i = 1; i < str.length(); i++) {
			nextState = s.getNext(str.charAt(i), x.cur);
			// x.cur to save the largest state it reach
			if (s.state > x.cur)
				x.cur = s.state;
			// nextState<0 means message in right form 
			if (nextState < 0)
				break;
		
			s = x.getState(nextState);

		}
		
		return x.cur;
	}
}