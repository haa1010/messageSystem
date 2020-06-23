package checkmess.automata;
/*
 * wait for not a space
 * if not a space @return 25 else return 0
 * */
public class State25 extends State0 {
	 public int getNext(char c,int State){
		 if(State>1) this.state=State;
		 else this.state=11;
	        if(c != ' ')
	            return 25;
	        else
	            return 0;
	    }


}
