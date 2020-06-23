package automata;
/*
 * already have 'Frd d', wait for e
 * */
public class State12 extends State0 {

	  public int getNext(char c,int State){
		  if(State>4) this.state=State;
	        else this.state=4;
		  
		  if(c=='e')
	        {//set state=State>4?State:4
			  // move to state wait for 'l'
	        	if(State>4) this.state=State;
	        	else this.state=4;
	        	return 13;
	        }
		  else if(c<='z'&&c>='A')
			  // move to state wait for space
			  return 8;
	        else 
	        	// move to wait for F
	        	return 0;
	              
	        
	  
}

	    
	 
}
