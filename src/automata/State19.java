package  automata;
/**
 * wait for space or endchar
 * */
public class State19 extends State0 {
	 public int getNext(char c,int State){
	
		 
		 if (c == ' ' || c == '\0')
	    	{if(State>10) this.state=State;
	    	else this.state=10;
	    	return -5;}
	        else if(c<='z'&&c>='A')
	            {this.state=State;
	            return 20;}
	        else return 19;
	       
	    }
	   

}
