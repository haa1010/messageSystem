package checkmess.automata;
/*
 * State17: alreary 'Frd RJ ' wait for B/G
 * this.state=8 means already reach 'Frd RJ ', wait for B/G
 * 
 * 
 * */
public class State17 extends State0 {

	public int getNext(char c,int State){
    	if(c == ' ')
            { // back to 17, set state=8
    		if(State>7) this.state=State;
    		else this.state=8;
    		return 17;
    		}
        else if (c == 'G')
        {// return 18, set state=8
        	if(State>7) this.state=State;
		else this.state=8;
            return 18;
        }
        else if (c == 'B')
        {// return 19
        	if(State>7) this.state=State;
    		else this.state=8;
        	return 19;}
        else if (c == 'F')
            { // return 1 
        	if(State >1) this.state=State;
            else this.state=0;
        	return 1;}
        else 
            {
        	if(State>7) this.state=State;
            else this.state=8;
        	return 24;
        	}
    	
        
    }
       

}
