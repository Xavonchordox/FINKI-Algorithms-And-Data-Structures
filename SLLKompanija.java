package Test10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id,int plata, SLLNode succ) {
        this.id = id;
        this.plata=plata;
        this.succ = succ;
    }


}

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }


    public void insertFirst(int id, int plata) {
        SLLNode ins = new SLLNode(id,plata, first);
        first = ins;
    }

    public void insertLast(int id,int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, plata, null);
            tmp.succ = ins;
        } else {
            insertFirst(id,plata);
        }
    }

    public SLLNode getFirst() {
        return first;
    }
    
    public void pecati (SLL lista)
    {
        SLLNode p=lista.first;
        while(p!=null)
        {
            System.out.println(p.id+" "+p.plata);
            p=p.succ;
        }
    }

    public SLL brisi_pomali_od(int iznos) {
        // Vasiot kod tuka

        SLLNode temp = first;
        SLL pogolemi = new SLL();

        while (temp != null){
            if(temp.plata >= iznos){
                pogolemi.insertLast(temp.id, temp.plata);
            }

            temp = temp.succ;
        }

        return pogolemi;
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka

        SLLNode nodeA;
        SLLNode nodeB;
        SLL sorted = new SLL();

        if(this.first == null){
            System.out.println("nema");
        }

        for(nodeA = first; nodeA != null; nodeA = nodeA.succ){
            for(nodeB = nodeA.succ; nodeB != null; nodeB = nodeB.succ){
                if(nodeA.id < nodeB.id) {
                    int tempID = nodeA.id;
                    int tempSAL = nodeA.plata;

                    nodeA.id = nodeB.id;
                    nodeA.plata = nodeB.plata;
                    nodeB.id = tempID;
                    nodeB.plata = tempSAL;
                }
            }

            sorted.insertLast(nodeA.id, nodeA.plata);
        }

        return sorted;
    }

}
public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s=stdin.readLine();
            String s1=stdin.readLine();
            lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1=lista1.brisi_pomali_od(Integer.parseInt(s));
        if(lista1!=null)
        {
            lista1=lista1.sortiraj_opagacki();
            lista1.pecati(lista1);
        }

    }
}
