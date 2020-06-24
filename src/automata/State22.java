package  automata;
/**
 * already have 'Frd ch' wait for 'g'
 * */
public class State22 extends State0 {
	 public int getNext(char c,int State){
		 //state=max(State,11)
		 if(State>11) this.state=State;
         else this.state=11;
	if(c=='g') return 23;
	 else if(c<='z'&&c>='A') {
		
		 return 25;}
	else return 0;
   
}


}
