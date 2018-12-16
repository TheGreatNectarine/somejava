package practice;

import Biblioteka.ArrayQueue;
import Biblioteka.ArrayStack;
import Biblioteka.LinkedQueue;
import Biblioteka.LinkedStack;

import java.util.Stack;


public class Tester {

    public static void main(String[] args) {
        Deque<String> q = new Deque<>();
        q.addLast("1");
        q.addLast("2");
        q.addFirst("3");
        for (int i = 0; i < 3; i++) {
            System.out.println(q.removeLast());
        }

//        for (String s : q) {
//            System.out.println(s);
//        }
    }

}