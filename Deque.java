/*
 * To loop through a collection, use the hasNext() and next() methods
 * Iterators are designed to easily change the collections that they loop through.
 * The remove() method can remove items from a collection while looping.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque <Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int counter;

    public Deque() {
        this.first = null;
        this.last = null;
        this.counter = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // returns the number of itens in the deque
    public int size() {
        return counter;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be a null element");

        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        if (isEmpty()) last = first;
        else oldFirst.previous = last;

        counter++;
    }

    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException("Item cannot be a null element");

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;

        if (isEmpty()) first = last;
        else oldLast.next = last;

        counter++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("You cannot remove elements from an empty deque");

        Node removedFirst = first;
        first = removedFirst.next;
        counter--;

        if (isEmpty()) last = null;
        else first.previous = null;

        return removedFirst.item;
    }

    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("You cannot remove elements from an empty deque");

        Node removedLast = last;
        last = removedLast.previous;
        counter--;

        if(isEmpty()) first = null;
        else last.next = null;

        return removedLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    public static void main(String[] args) {

    }

    private class Node {
        Item item;
        Node next;
        Node previous;

        Node(){}

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        Node(Item item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;
        }

        public boolean hasNext() { return current != null;}

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported by this class");
        }
    }
}
