package  automata;
/**
 * wait for char is not a space
 * */
public class State9 extends State0{
		 public int getNext(char c,int State){
			 if(this.state>1) this.state=State;
		    	else
		    	this.state=0;
			 /**
			  * if char != ' ' return 9
			  * */
		        if(c != ' ')
		            return 9;
		        else /**else back to 0
		            */
		        	return 0;
		    }
	 

}
