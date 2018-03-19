package com.spider.song.spidercommon;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:Effective Java 第一版推荐写法
 * User: songzhengjie
 * Date: 2018-02-27
 * Time: 19:05
 * ========================================
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node mergeNode(Node head1, Node head2) {

        Node node1 = head1;
        Node node2 = head2;
        Node node3 = null;
        while (node1 != null && node2 != null) {

            node1 = node1.next;


            while (node2.next.data <= node1.data) {
                node3.next = node2.next;
                node3 = node3.next;
                node2 = node2.next;
            }
            node3.next = node1;
            node3 = node3.next;
        }

        return node3;
    }


    public static void main(String[] args) {

    }

}