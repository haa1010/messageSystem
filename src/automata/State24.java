package  automata;
/**
 * wait for space
 * */
public class State24 extends State0 {

	 public int getNext(char c,int State){
	        if(c != ' ')
	        {if(State>7) this.state=State;
	        else this.state=8;
	            return 24;}
	        else {
	        	if(State>0) this.state=State;
		        else this.state=0;
	        	return 0;
	        }
	    }


}
