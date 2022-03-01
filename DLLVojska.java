package Test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-"+element.toString()+"->";
    }
}


class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }


    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

}

public class DLLVojska {


    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] ids = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(ids[i]));
        }

        s = stdin.readLine();
        String interval[] = s.split(" ");
        int a = Integer.parseInt(interval[0]);
        int b = Integer.parseInt(interval[1]);

        s = stdin.readLine();
        interval = s.split(" ");
        int c = Integer.parseInt(interval[0]);
        int d = Integer.parseInt(interval[1]);


        DLL<Integer> result = vojska(lista, a, b, c, d);


        DLLNode<Integer> node = result.getFirst();
        System.out.print(node.element);
        node = node.succ;
        while(node != null){
            System.out.print(" "+node.element);
            node = node.succ;
        }

    }

    private static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {
        DLLNode<Integer> temp = lista.getFirst();
        DLLNode<Integer> nodeA = lista.getFirst();
        DLLNode<Integer> nodeB = lista.getFirst();
        DLLNode<Integer> nodeC = lista.getFirst();
        DLLNode<Integer> nodeD = lista.getFirst();
        DLLNode<Integer> predA = lista.getFirst();
        DLLNode<Integer> posleB = lista.getFirst();
        DLLNode<Integer> posleD = lista.getFirst();

        while (temp != null){
            if(temp.element == a) nodeA = temp;
            if(temp.element == b) nodeB = temp;
            if(temp.element == c) nodeC = temp;
            if(temp.element == d) nodeD = temp;

            temp = temp.succ;
        }

        DLL<Integer> subList1 = new DLL<>();
        DLL<Integer> subList2 = new DLL<>();

        temp = nodeA;
        while (temp != nodeB.succ){
            subList1.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = nodeC;
        while (temp != nodeD.succ){
            subList2.insertLast(temp.element);
            temp = temp.succ;
        }

        DLL<Integer> sumList = new DLL<>();

        temp = lista.getFirst();
        while (temp != nodeA){
            sumList.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = subList2.getFirst();
        while (temp != null){
            sumList.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = nodeB.succ;
        while (temp != nodeC){
            sumList.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = subList1.getFirst();
        while (temp != null){
            sumList.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = nodeD.succ;
        while (temp != null){
            sumList.insertLast(temp.element);
            temp = temp.succ;
        }

        return sumList;
    }
}