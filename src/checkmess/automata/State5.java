/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkmess.automata;
/**
 *
 * @author hue
 * already have 'Frd G' wait for space or endchar
 */
public class State5 extends State0 {
    public int getNext(char c,int State){
    	
    	
    	if (c == ' ' || c == '\0')
    	{ 
    		/*set state=2,@return -1*/
    		this.state=2;
    		return -1;
    	}
        else 
            { /*state=max(State,1) @return 7*/
        	
        	 this.state=State;
        
        	
        	
        	return 7;
        	}
    }}
    
   

