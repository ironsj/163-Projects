package ModifyPack;

import java.io.Serializable;

/*****************************************************************
 Class that handles the linked list of campsites

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public class MySingleWithOutTailLinkedList implements Serializable {
    /** the first node in the linked list*/
    private Node top;

    /** the size of the linked list*/
    public int size;

    /*****************************************************************
     Default constructor, sets top to null and size to 0
     *****************************************************************/
    public MySingleWithOutTailLinkedList() {
        top = null;
        size = 0;
    }

    /*****************************************************************
     Returns the size of the linked list
     @return the size of the linked list
     *****************************************************************/
    public int size() {
        return size;
    }

    /*****************************************************************
     Clears the linked list; sets top to null, size to 0
     *****************************************************************/
    public void clear() {
        top = null;
        size = 0;
    }

    /*****************************************************************
     Inserts new node into the correct place everytime a node is added
     *****************************************************************/
    public void sort(){
        Node curr = top;
        Node index = null;
        Node temp = null;
        if(top.getNext() != null){
            while(curr != null){
                index = curr.getNext();
                while(index != null){
                    if(curr.getData().getClass().getName().compareTo(index.getData().getClass().getName()) < 0){
                        temp = new Node(curr.getData(), curr.getNext());
                        curr.setData(index.getData());
                        index.setData(temp.getData());
                    }
                    else if(curr.getData().getClass().getName().compareTo(index.getData().getClass().getName()) == 0){
                        if(curr.getData().estimatedCheckOut.compareTo(index.getData().estimatedCheckOut) > 0){
                            temp = new Node(curr.getData(), curr.getNext());
                            curr.setData(index.getData());
                            index.setData(temp.getData());
                        }
                        else if(curr.getData().estimatedCheckOut.compareTo(index.getData().estimatedCheckOut) == 0){
                            if(curr.getData().guestName.compareToIgnoreCase(index.getData().guestName) > 0){
                                temp = new Node(curr.getData(), curr.getNext());
                                curr.setData(index.getData());
                                index.setData(temp.getData());
                            }
                        }
                    }
                    index = index.getNext();
                }
                curr = curr.getNext();
            }
        }
    }

    /*****************************************************************
     Adds a campsite to the linked list
     @param s the campsite being added to the list
     *****************************************************************/
    public void add(CampSite s) {
        size++;
        if(size == 1){
            Node add = new Node(s, null);
            top = add;
        }
        else{
            Node add = new Node(s, top);
            top = add;
        }
        sort();
    }

    /*****************************************************************
     Removes a node from the linked list
     @param index the place in the linked list of the node being removed
     @return the campsite being removed
     *****************************************************************/
    public CampSite remove(int index) {
        int i = 0;
        Node cur = top;
        Node prev = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for list of size " + size);
        }
        else {
            while(i < index){
                prev = cur;
                cur = cur.getNext();
                ++i;
            }
        }
        if(i == 0){
            top = top.getNext();
            size--;
        }
        else if(index == size - 1){
            prev.setNext(null);
            size--;
        }
        else {
            prev.setNext(cur.getNext());
            size--;
        }
        return cur.getData();
    }

    /*****************************************************************
     Returns a specific campsite in the linked list
     @param index the place in the linked list of the node being removed
     @return the campsite at the specfic index
     *****************************************************************/
    public CampSite get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for list of size " + size);
        }
        else {
            int i = 0;
            Node cur = top;
            while (i < index) {
                cur = cur.getNext();
                i++;
            }
            return cur.getData();
        }
    }

    /*****************************************************************
     Displays the information of each campsite in the linked list
     *****************************************************************/
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    /*****************************************************************
     Creates string with the 'top' of the list and the size
     @return String with top and size
     *****************************************************************/
    @Override
    public String toString() {
        return "MySingleWithOutTailLinkedList{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}
