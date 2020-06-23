/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

/**
 *
 * @author hue
 * wait for not a space
 * if char is space return 0, else return 8
 */
public class State8 extends State0 {
    public int getNext(char c,int State){
    	if(State>0) this.state=State;
    	else
    	this.state=0;
    	 if(c == ' ')
             return 0;
         else {
        	 
        	 if(State>0) this.state=State;
     	else
        	this.state=0;
             return 8;
         }
    }
       
}
