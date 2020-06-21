/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkmess.automata;
public class Automata {
	public int finalState;
	
	public int cur = -1;// to find reached state
	public static State0[] listState = new State0[] { new State0(), new State1(), new State2(), new State3(),
			new State4(), new State5(), new State6(), new State7(), new State8(), new State9(), new State10(),
			new State11(), new State12(), new State13(), new State14(), new State15(), new State16(), new State17(),
			new State18(), new State19(), new State20(), new State21(), new State22(), new State23(), new State24(),
			new State25(), new State26() };
	

	public State0 getState(int c) {
		return listState[c];
	}
	
	public Automata() {}

	/**
	 * @param args the command line arguments
	 */
	public int result(String str) {
		int nextState = -7;

		State0 s = new State0();
		nextState = s.getNext(str.charAt(0), -1);
		Automata x = new Automata();
		s = x.getState(nextState);
		 if (s.State > x.cur)
				x.cur = s.State;
		for (int i = 1; i < str.length(); i++) {

			nextState = s.getNext(str.charAt(i), x.cur);
			if (s.State > x.cur)
				x.cur = s.State;
			if (nextState < 0)
				break;
		
			s = x.getState(nextState);

		}
		
		return x.cur;
	}
}