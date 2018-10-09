package bin;

import javax.script.ScriptException;

public class LinkedListStack {
	private LinkedList ll = new LinkedList();

	public void push(Object obj) {
		ll.insertFirst(obj);
	}

	public Object pop() throws ScriptException {
		return ll.deleteFirst();
	}
	

	public Object top( ) throws ScriptException {		
		return ll.giveTop();
	}

	public void display() {
		ll.display();
	}
}
