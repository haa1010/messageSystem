/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  automata;

/**
 *
 * @author hue
 * wait for space or endchar
 * 
 * 
 */
public class State7 extends State0 {
    public int getNext(char c,int State){
    	if(this.state>1) this.state=State;
    	else
    	this.state=1;
    	
    		if(c==' '||c=='\0') return 7;
    		/**if not @return 9*/
    	return 9;
    	
    }
     
}
