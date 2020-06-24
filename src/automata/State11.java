package automata;
/**wait for space
 * */
public class State11 extends State0 {
	 public int getNext(char c,int State){
		 if(State>0) this.state=State;
			else this.state=7; 
		 if(c != ' ')
	            return 11;
	        else
	            return 0;
	    }

}
