/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;
/**
 *
 * @author hue
 * already have 'Frd ' wait for G/B/R/c/d
 */
public class State4 extends State0 {
    public int getNext(char c,int State){
    	/*
    	 * if c=' '@return 4;
    	 * if c='B' @ return 6
    	 * if c='G' @return 5
    	 * if c='F' @return 1
    	 * if c='d' @return 12
    	 * if c='c' @return 21
    	 * if c='R' @return 15
    	 * else if c is letter @return 8
    	 * else @return 9;
    	 * 
    	 * 
    	 * 
    	 * */
    	
    	if(State<1)
        	this.state=1;
        	else this.state=State;
    	if(c == ' ')
            return 4;
        else if (c == 'G')
            {
        	return 5;
        	}
        else if (c == 'B')        
        { 
        	return 6;
        }
        	else if (c == 'F')
        	{
        		if(this.state<1) this.state=0;
        		return 1;
        	}
        else if(c=='d') {
        	if(State>0) this.state=State;
        	else this.state=4;
        	
        	return 12;}
        else if(c=='c') {
        	if(State>0) this.state=State;
        	else this.state=11;
        	
        	return 21;}
        else if(c=='R') {
        	if(State>0) this.state=State;
        	else this.state=7;
        	
        	return 15;}
        else if(c<='z'&&c>='A')
        {
        	if(State>0) this.state=State;
        else this.state=1;
        
        	return 8;}
        else {
        	if(State>0) this.state=State;
            else this.state=1;
        	
        	return 9;
        }
        
    }
   
}
