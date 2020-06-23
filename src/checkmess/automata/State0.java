/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkmess.automata;

/**
 *
 * @author hue
 */
public class State0 {
	public int state=0;
	/*
	 * getNext()
	 * input: char c, int State
	 * output: an integer 
	 * to find next State will use for next character, also set state 
	 * this.state= State>0?State:0
	 * */
    public int getNext(char c, int State){
    	if(State>0) this.state=State;
    	else this.state=0;
        switch (c) {
            case 'F'://move to wait r
                	return 1;
            case ' ':// move to wait F
            	return 0;
            default:
                return 8;
        }
    }
    
}
