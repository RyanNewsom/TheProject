
/**
 * This is a generic Priority Queue implemented using a linked list. 
 * 
 * This priority queue will serve as the line to speak with the secretary. Each 
 * incoming customer is added to the end of the line and given a priority number,
 * I'm thinking 0 for phone customers, 1 for everyone else. At any rate, the phone 
 * customers will have some higher priority than everyone else. This will make it 
 * easy to give priorities to the walk-in customers if we decide to do the extra-
 * credit. My only concern is allowing the phone customers to interrupt the current
 * customer and get straight to the secretary. Dequeueing should not be as problem 
 * as the phone customer will have priority and be dequeued as expected (possibly 
 * unless it's a phone cust. interrupting another phone cust.?) I'm thinking of 
 * keeping a second list of served customers, to solve that issue.
 *
 * @author Aaron
 * @param <Element>
 */
public class PriorityQueue<Element> {
    
    private Node head, current, prev;
    private int size;
    
    /**
     *
     */
    public PriorityQueue() {
        head = null;
        current = null;
        prev = null;
        size = 0;
    }
    
    /**
     *
     * @return - whether or not the list is empty
     */
    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }
    
    /**
     *
     * @return - number of items in the list
     */
    public int size() {
        return size;
    }
    /**
     * setCurrent() sets the Node current to the Node at spot 'index' in the list
     * 
     */
    private void setCurrent(int index) {
        current = head;
        prev = null;
        for(int i=0; i<index; i++) {
            prev = current;
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }
    }
    
    /**
     * 
     * @return - the position of the node with the highest priority in the list
     */
    public int highestPriority () { 
        int pos = 0;
        int highest = 0;
        int counter = 0;
        
        Node tmp = head;
        
        while (counter < size) {
            if (tmp.getPriority() > highest) {
                highest = tmp.getPriority();
                pos = counter;
            }
            tmp = tmp.getNext();
            counter++;
        }
        return pos;
    }
    
    /**
     *  enqueue() adds an item to the end of the list
     * @param data - the actual object the node contains
     * @param priority - this objects priority
     */
    public void enqueue(Object data, int priority) {
        Node tmp = new Node(data, null, priority);
        
        if (size == 0) head = tmp;
        else {
            setCurrent(size);
            current.setNext(tmp);          
        }
        size++;                         
    }
    
    /**
     * dequeue() removes the item from the list with the highest priority
     * @return
     */
    public Object dequeue() {           
        int index = highestPriority();
        
        if (index == 0) {               //dequeueing from the front of the list
            if (size == 1) {            
                current = head;            
                head = null;            
            } else {                    
                current = head;            
                head = head.getNext();  
            }
        } else {
            if (index == size) {        //dequeueing from the end of the list
                setCurrent(index);      
                prev.setNext(null);     
            } else {                    //dequeueing from middle of list (won't happen in office sim.)
                setCurrent(index);      
                prev.setNext(current.getNext()); 
            }
        }
        size--;                         
        return current;                    
    }
}

