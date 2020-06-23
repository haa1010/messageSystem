/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

/**
 *
 * @author hue
 * already have F, wait for d
 * if char =d return 3
 * else char=letter return 9;
 * else return 8
 */
public class State2 extends State0 {
	
    public int getNext(char c,int State){
    	//set state to max(State,0)
    	//
    	if(State>0) this.state=State;
    	else this.state=0;
    	if(c=='d') return 3;
    	else if(c>='A'&&c<='z') return 9;
    	else return 8;
        
    }
   
}
