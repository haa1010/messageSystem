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
public class State1 extends State0 {
	
    public int getNext(char c,int State){
    	
        if(c=='r')
                { // change state
        		//move to state 2: wait d
        	if(State>0) this.state=State;
        	else this.state=0;
        	return 2;
                }
                else if(c<='z'&&c>='A') 
               // state to solve with character
                	return 8;
                else
                	// back to wait F
                return 0;
        }
    
    
}
