/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

/**
 *
 * @author hue
 */
public class State0 {
	public int state=0;
	/**
	 * @param c 
	 * character
	 * @param State
	 * current state of string 
	 * @return  integer 
	 */
	 /** to find next State will use for next character, also set state 
	 * set state for max value
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
