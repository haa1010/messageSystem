/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkmess.automata;
/*
 * already have 'Frd B' wait for space or endchar
 * if not return 7
 * */
public class State6 extends State0 {
	public int getNext(char c, int State) {

		if (c == ' ' || c == '\0') {
/*
 * change state=3,@return -2
 * */
			this.state = 3;
			return -2;
		} else 

		{
	
		/*
		 * change state=State;
		 * @return 7
		 * */

			this.state = State;
			return 7;
		}

	}

}
