package automata;
/*
 * after receive Frd R, wait for J. 
 * if char='j' move to state 16
 * if char !=j&& be a letter move to state 11
 * else back to 0
 * 
 * */
public class State15 extends State0 {
	
	 public int getNext(char c,int State){
	
		 if(c=='J')
         {
		if(State>0) this.state=State;
		else this.state=7;
		return 16;
		}
         else if(c<='z'&&c>='A') return 11;
         else  return 0;
 }


	
}
