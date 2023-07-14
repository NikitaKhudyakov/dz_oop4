// На основе интерфейса GBList создать свою реализацию LinkedList с методами вставки в начало, конец списка, 
// получения размера списка и получения элемента по индексу
// ссылка на ролик по LinkedList
// https://www.youtube.com/watch?v=BH6RJf2fVCQ + любая информация из гугла РАЗРЕШЕНА.

import java.util.Iterator;

public class Linked<E> implements LinkedInterface<E>, Iterable<E>, IngIterator<E> {

    private Node<E> prevElement;
    private E currentElement;
    private Node<E> nextElement;

    public static void main(String[] args) {
        Linked<String> stringLinked = new Linked<>();
        stringLinked.addLast("QWERT");
        stringLinked.addLast("ASDF");
        stringLinked.addLast("ZXCV");
        stringLinked.addLast("AQWE");

        Iterator iterator = stringLinked.desIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println(stringLinked.size());
        System.out.println(stringLinked.getElemIndex(0));
    }

    private Node<E> fstNode;
    private Node<E> lstNode;
    private int size = 0;

    @Override
    public void addFirst(E e) {
        Node<E> next = fstNode;
        next.setCurrentElem(e);
        fstNode = new Node<>(null, null, next);
        next.setPrevElem(fstNode);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElemIndex(int counter) {
        Node<E> target = fstNode.getNextElem();
        for (int i = 0; i < counter; i++) {
            target = getNextElem(target);
        }
        return target.getCurrentElem();
    }

    private Node<E> getNextElem(Node<E> current) {
        return current.getNextElem();
    }

    public Linked() {
        lstNode = new Node<E>(null, fstNode, null);
        fstNode = new Node<E>(null, null, lstNode);
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = lstNode;
        prev.setCurrentElem(e);
        lstNode = new Node<E>(null, prev, null);
        prev.setNextElem(lstNode);
        size++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElemIndex(counter++);
            }
        };
    }

    @Override
    public Iterator<E> desIterator() {
        return new Iterator<E>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElemIndex(counter--);
            }
        };
    }

    private class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        private Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public void setNextElem(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getNextElem() {
            return nextElement;
        }

        public void setCurrentElem(E currentElement) {
            this.currentElement = currentElement;
        }

        public E getCurrentElem() {
            return currentElement;
        }

        public void setPrevElem(Node<E> fstNode) {
            this.prevElement = prevElement;
        }
    }
}