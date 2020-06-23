package automata;
/*
 * after receive Frd RJ, wait for space 
 * if it is space return 17, set state=State>6?State:8
 * else if it is a letter return 26, set state=State>6?State:7
 * else return 11
 * this.state=7 means already reach 'Frd RJ'
 * this.state=8 means already reach 'Frd RJ '
 * 
 * */

public class State16 extends State0 {
	 public int getNext(char c,int State){
		 if(c == ' ')
	            {
			 if(State>=7) this.state=State;
			 else this.state=8;
			 return 17;
			 }
	    	
	    	else  if(c<='z'&&c>='A') {
	    		if(State>6) this.state=State;
	    		else this.state=7;
	    		return 26;}
	    	else return 11;
		 }

		
}
