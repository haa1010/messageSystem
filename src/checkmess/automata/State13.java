package checkmess.automata;
/*
 * already have 'Frd de' wait for 'l'
 * set state=max(State,4)
 * */
public class State13 extends State0 {
	public int getNext(char c,int State){
    	if(State>4) this.state=State;
    	else this.state=4;
		if(c=='l') // move to state wait for end char or space
			return 14;
    	 else //back to state 8 
    		 return 8;
    	
        
    }
   
}
