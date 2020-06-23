package automata;
/*
 * @author hue
 * already have '' wait for enchar or space
 * 
 * */
public class State23 extends State0 {
	 public int getNext(char c,int State){
    	 if(c == ' '||c=='\0')
             {/*set state=12,@return -6*/
    		 this.state=12;
    		 return -6;}
         else { /*else state=State, @return 0*/
        	 this.state=State;
            return 0;
         }
    }
   
	

}
