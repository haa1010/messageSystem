package checkmess.automata;
//wait for not a space
public class State26 extends State0 {
	public int getNext(char c,int State){
		if(State>1) this.state=State;
		else this.state=7;
		if(c == ' ')
            return 0;
        else
            return 26;
       
   }
       
}
