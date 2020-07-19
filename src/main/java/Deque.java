import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int lenght;


    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;
    }


    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return lenght;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItemNull(item);
        if (first == null) {
            first = new Node<>();
            first.item = item;
            last = first;
            lenght++;
            return;
        }
        Node oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        oldFirst.previous = first;
        lenght++;
    }




    // add the item to the back
    public void addLast(Item item) {
        checkItemNull(item);
        if (first == null) {
            first = new Node<>();
            first.item = item;
            last = first;
            lenght++;
            return;
        }

        Node oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.previous = oldlast;
        oldlast.next = last;
        lenght++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkFirstNull();
        if (lenght == 1){
            Item res = first.item;
            last = first = null;
            lenght--;
            return res;
        }
        Item res = first.item;
        first = first.next;
        first.previous = null;
        lenght--;
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkFirstNull();
        if (lenght == 1){
            Item res = first.item;
            last = first = null;
            lenght--;
            return res;
        }

        Item res = last.item;
        last = last.previous;
        last.next = null;
        lenght--;
        return res;
    }


    private void checkFirstNull(){
        if (first == null){
            throw new NoSuchElementException();
        }
    }

    private void checkItemNull(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
    }



    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new IteratorList();
    }


    private class IteratorList implements Iterator<Item>{

        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw  new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addLast("Last");
        deque.addFirst("First");
        deque.removeFirst();
        deque.removeLast();
        deque.size();
        System.out.println(deque.isEmpty());

        for (String s : deque){
            StdOut.println(s);
        }

    }


}
