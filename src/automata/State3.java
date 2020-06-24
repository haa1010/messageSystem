/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

/**
 *
 * @author hue
 * already have 'Frd' wait for space
 */
public class State3 extends State0 {
    public int getNext(char c,int State){
    	if(c == ' ')
    	{ 
    	/**
    	 * set state=max(State,1)
    	 * @return 4;
    	 * 
    	 * */
    		
    		if(State>1) this.state=State;
    	else this.state=1;
    		return 4;
    		}
    	
    	else  if(c<='z'&&c>='A') { 
    		/**
    		 * @return 8:wait for letter
    		 * */
    		if(State>0) this.state=State;
        	else this.state=0;
    		return 8;
    		}
    	else {
    		/**
    		 * @return 9: wait for not a letter
    		 * 
    		 * */
    		if(State>0) this.state=State;
        	else this.state=0;
    		return 9;
    	}
    }
  
    
}
