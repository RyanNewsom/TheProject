/**
 *
 * @author Aaron
 */
public class Node {
    
    private Object data;
    private Node next;
    private int priority;
    
    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.priority = 5; // this will be the lowest priority, I arbitrarily chose 5
    }
    
    public Node (Object data, Node next) {
        this.data = data;
        this.next = next;
        this.priority = 5; // this will be the lowest priority, I arbitrarily chose 5
    }

    public Node (Object data, Node next, int priority) {
        this.data = data;
        this.next = next;
        this.priority = priority; 
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public void setData(Object data) {
	this.data = data;
    }
		
    public void setNext(Node next) {
	this.next = next;
    }
    		
    public Node getNext() {
	return next;
    }
    
    public Object getData() {
	return data;
    }
}
