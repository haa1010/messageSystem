package automata;
public class State18 extends State0 {
	/*
	 * already have 'Frd RJ G' and wait for end char or space
	 * if character= endchar or space @return -4, set state=9;
	 * if character=letter @return 20, state=8;
	 * else @return 18
	 * 
	 * */
	 public int getNext(char c,int State){
	    	if (c == ' ' || c == '\0')
	    	{
	    		this.state=9;
	    		return -4;
	    		}
	        else if(c<='z'&&c>='A')
	            {
	        	if(State>7) this.state=State;
	        	else this.state=8;
	        	return 20;
	        	}
	        else return 18;
	       
	    }
	    

}
