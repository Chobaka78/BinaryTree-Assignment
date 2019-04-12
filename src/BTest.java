//public class BTest{
//    public static void main (String [] args){
//        BTree cedar = new BTree();
//        cedar.add(75);
//        cedar.add(40);
//        cedar.add(100);
//        cedar.add(5);
//        cedar.add(32);
//        System.out.println(cedar);
//        System.out.println(cedar.depth(10));
//    }
//}

import java.util.*;
import java.lang.Math.*;
public class BTest {
    public static void main(String[] args) {
        BTree dolla=new BTree();
        dolla.add(12);
        dolla.add(50);
        dolla.add(45);
        dolla.add(5);
        dolla.add(100);
        dolla.add(1000);
        dolla.add(10);
        dolla.add(2);
        dolla.delete(45);

        BTree dollaa=new BTree();
        dollaa.add(20);
        dollaa.add(25);
        dollaa.add(10);
        dollaa.add(1);

        System.out.println(dolla);
        System.out.println(dolla.isAncestor(12,45));
        System.out.println(dollaa);
        //dolla.add(dollaa);
        System.out.println(dolla);
        System.out.println(dollaa);
        //System.out.println(dolla.display(2));
        dolla.display();
        System.out.println("\n");
        System.out.println(dolla.depth(45));
        System.out.println(dolla.countLeaves());
        System.out.println(dolla.height());
        System.out.println(dolla.isBalanced());
        dolla.sprout();
    }
}