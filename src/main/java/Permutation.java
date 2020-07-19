import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        String input = StdIn.readLine();
        for (String s : input.split(" ")){
            queue.enqueue(s);
        }
        while (k > 0){
            StdOut.println(queue.dequeue());
            k--;
        }


    }
}