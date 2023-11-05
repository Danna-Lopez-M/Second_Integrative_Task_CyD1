package ui;

import dataStructures.MyQueue;

import java.util.Scanner;

public class Main {
    Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main m = new Main();
/*         // probando mi stack:
        Stack<String> myStack = new Stack<>();

        myStack.push("hello world");
        myStack.push("msg2");
        myStack.push("msg3");
        myStack.push("msg4");
        myStack.push("msg5");
        myStack.push("msg6");

        System.out.println("probando mi stack:");

        System.out.println(myStack.size());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.size());

        // probando mi queue:
        Queue<String> myQueue = new Queue<>();

        myQueue.enqueue("hello world");
        myQueue.enqueue("msg2");
        myQueue.enqueue("msg3");
        myQueue.enqueue("msg4");
        myQueue.enqueue("msg5");
        myQueue.enqueue("msg6");

        System.out.println("probando mi queue: ");
        System.out.println(myQueue.size());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.size());

        Stack<Integer> myStack = new Stack<>();
        for (int i = 0; i <= 7; i++) {
            myStack.push(i);
        }
        System.out.println(myStack.peek());
*/
            MyQueue<Integer> myQueue = new MyQueue<>();
            int n = m.sc.nextInt();
            if(n > 0) {
                for (int i = 1; i <= n; i++) {
                    // System.out.println(i);
                    myQueue.enqueue(i);
                }
                // System.out.println(myQueue.peek());
                // debe ser mientras que el tamaño sea mayor a uno. Porque cuando el tamaño sea igual a uno,
                // esa será la carta que queda del proceso.
                String discardedCards = "";
                while (myQueue.size() > 1) {
                    discardedCards += myQueue.dequeue() + ",";
                    int nextItem = myQueue.dequeue();
                    myQueue.enqueue(nextItem);
                }
                System.out.println("Discarded cards: " + discardedCards);
                System.out.println("Remaining card: " + myQueue.dequeue());
            }
        }


}

/*
* seguimiento acerca del Set:
* */

/*
package ui;
import model.*;
import java.util.Scanner;

public class Main {
    private Scanner sc;
    private GenericSet<String> set;
    public Main(){
        sc = new Scanner(System.in);
        set = new GenericSet<>();
    }
    public void answerOption(int userOption){
        switch (userOption){
            case 0:
                System.out.println("closing app...");
                break;
            case 1:
                printSet();
                break;
            case 2:
                add();
                break;
            case 3:
                delete();
                break;
            case 4:
                isEmpty();
                break;
            case 5:
                size();
                break;
            case 6:
                contains();
                break;
            case 7:
                union();
                break;
            case 8:
                intersection();
                break;
            case 9:
                difference();
                break;
        }
    }
    public void printSet(){
        System.out.println(set.print());
    }
    public void add(){
        System.out.println("please specify the item you want to add: ");
        String res = sc.nextLine();
        set.add(res);
    }
    public void delete(){
        System.out.println("please specify the item you want to delete: ");
        String res = sc.nextLine();
        set.delete(res);
    }
    public void isEmpty(){
        System.out.println(set.isEmpty());
    }
    public void size(){
        System.out.println(set.size());
    }
    public void contains(){
        System.out.println("please specify the item you want to search/verify it is contained by the set:");
        String res = sc.nextLine();
        System.out.println(set.contains(res));
    }
    public void union(){
        GenericSet<String> newSet = new GenericSet<>();
        newSet.add("hello");
        newSet.add("world");
        System.out.println(set.union(newSet));
    }
    public void intersection(){
        GenericSet<String> newSet = new GenericSet<>();
        newSet.add("hello");
        newSet.add("world");
        System.out.println(set.intersection(newSet));
    }
    public void difference(){
        GenericSet<String> newSet = new GenericSet<>();
        newSet.add("hello");
        newSet.add("world");
        System.out.println(set.difference(newSet));
    }
    public int showMenuAndGetOption(){
        int input;
        System.out.println("\n app menu"+
                        "\n (1) print set."+
                        "\n (2) add element."+
                        "\n (3) delete element."+
                        "\n (4) verify if set is empty."+
                        "\n (5) get size of set."+
                        "\n (6) verify if element is contained by set."+
                        "\n (7) union."+
                        "\n (8) intersection."+
                        "\n (9) difference."+
                        "\n (0) exit."
                );
        input = sc.nextInt();
        sc.nextLine();
        return input;
    }
    public static void main(String[] args){
        Main2 m = new Main2();
        int option = 0;
        do {
            option = m.showMenuAndGetOption();
            m.answerOption(option);
        }while (option != 0);
    }
}

 */
