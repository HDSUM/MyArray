package com.data.struct;

public class MyLinkedList<T> {
    private Node first;
    private Node end;
    private int size;

    public MyLinkedList() {
        first = null;
        end = null;
        size = 0;
    }

    public void add(int index, T t) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("越界");
        } else if (index > size >> 1) {
            Node prev = end;
            for (int i = 0; i < size - index; i++) {
                prev = prev.prev;
            }
            Node newNode = new Node(prev, t, prev.next);    // 如果prev为null
            prev.next = newNode;
            if (prev == end) {
                end = newNode;
            } else {
                newNode.next.prev = newNode;
            }
        } else if(size == 0){
            Node newNode = new Node(null , t, null);
            first = newNode;
            end = newNode;
        }else {
            Node prev = this.first;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node newNode = new Node(prev.prev, t, prev);    // 如果prev为null
            prev.prev = newNode;
            if (prev == first) {
                first = newNode;
            } else {
                newNode.prev.next = newNode;
            }
        }
        size++;
    }

    public void add(T t) throws Exception {
        add(size,t);
    }

    public T get(int index) throws Exception {
        if(index<0 || index>=size){
            throw new Exception("越界");
        }
        Node node = first;
        for(int i=0;i<index;i++){
            node  = node.next;
        }
        return (T) node.value;
    }

    public int size(){
        return size;
    }

    public void remove(T t){
        for(Node node = first;node!=null;node = node.next){
            if(node.value.equals(t)){
                if(node == first){
                    first = node.next;
                }else{
                    node.prev.next = node.next;
                }
                if(node == end){
                    end = node.prev;
                }else{
                    node.next.prev = node.prev;
                }
                size--;
                break;
            }
        }
    }

    public T remove(int index) throws Exception {
        if(index<0 || index>=size){
            throw new Exception("越界!");
        }
        Node node = first;
        for(int i=0;i<index;i++){
            node = node.next;
        }
        if(node == first){
            first = node.next;
        }else{
            node.prev.next = node.next;
        }
        if(node == end){
            end = node.prev;
        }else{
            node.next.prev = node.prev;
        }
        return null;
    }

    public void print(){
        Node node = first;
        System.out.printf("LinkedArray{");
        for(int i=0;i<size;i++){
            System.out.print(node.value+" ,");
            node = node.next;
        }
        System.out.println("\b}");
    }

    private static class Node<T> {
        Node prev;  // 指向上一个节点
        T value;    // 当前节点内容
        Node next;  // 指向下一个节点

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
