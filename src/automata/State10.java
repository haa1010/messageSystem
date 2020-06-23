package automata;

public class State10 extends State0 {

	 public int getNext(char c,int State){
		 
	        if(c != ' ')
	            return 10;
	        else
	            return 0;
	    }

}
