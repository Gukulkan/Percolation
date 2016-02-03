import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;

/**
 * Created by User on 2/3/2016.
 */
public class TestMain {
    public static void main(String args[]){
        //1-3 7-2 9-6 8-9 5-1 0-3
        QuickFindUF x = new QuickFindUF(10);
        x.union(1,3);
        x.union(7,2);
        x.union(9,6);
        x.union(8,9);
        x.union(5,1);
        x.union(0,3);
        for(int i = 0; i < 10; i++){
            System.out.print(x.find(i) + " ");
        }
        System.out.println();
        //1-2 7-6 3-4 2-8 7-3 0-9 0-2 7-9 2-5
        QuickUnionUF y = new QuickUnionUF(10);
        y.union(1,2);
        y.union(7,6);
        y.union(3,4);
        y.union(2,8);
        y.union(7,3);
        y.union(0,9);
        y.union(0,2);
        y.union(7,9);
        y.union(2,5);
        for(int i = 0; i < 10; i++){
            System.out.print(y.find(i) + " ");
        }
    }
}
