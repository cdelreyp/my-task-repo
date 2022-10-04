import java.util.HashMap;
import java.util.Map;

public class Board {

	private Map<Integer, Integer> transitions;

	public Board() {
		this.transitions = new HashMap<>();
		this.transitions.put(2, 38);
		this.transitions.put(7, 14);
		this.transitions.put(8, 31);
		this.transitions.put(15, 26);
		this.transitions.put(16, 6);
		this.transitions.put(21, 42);
		this.transitions.put(28, 84);
		this.transitions.put(36, 44);
		this.transitions.put(46, 25);
		this.transitions.put(49, 11);
		this.transitions.put(51, 67);
		this.transitions.put(62, 19);
		this.transitions.put(64, 60);
		this.transitions.put(71, 91);
		this.transitions.put(74, 53);
		this.transitions.put(78, 98);
		this.transitions.put(87, 94);
		this.transitions.put(92, 88);
		this.transitions.put(95, 75);
		this.transitions.put(99, 80);
	}

	public int calculatePosition(int position) {
		if(position > 100) {
			System.out.println("Vuelta " + position);
			position = 100 - (position - 100);
		}
		Integer value = transitions.get(position);
		return value == null ? position : value;  
	}

}
