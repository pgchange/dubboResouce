package com.study.dubbo.test;

/**
 * <p>
 * Created by zhangchangji on 2020/4/13.
 */
public class LinkList {

    private Node head;

    private Node last;

    private int size;

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void add(int i) {
        Node node = new Node(i);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
        }
    }


    public void addLast(int i) {
        Node newNode = new Node(i);
        Node l = getLast();
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
    }

    private class Node {

        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
