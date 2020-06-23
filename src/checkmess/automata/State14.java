package checkmess.automata;
/*
 * already have 'Frd del' wait for space or endchar
 * 
 * */
public class State14 extends State0 {
	  public int getNext(char c,int State){
	    	 if(c == ' '||c=='\0')
	             {//message in form Frd del, @return -3, change state=5
	    		  this.state=5;
	    		 return -3;
	    		 }
	         else 
	            {// back to wait F
	        	 if(State>0) this.state=4;
	        	 return 0;
	            }
	        
	    }
	   
}
