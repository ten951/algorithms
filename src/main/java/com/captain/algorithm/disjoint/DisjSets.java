package com.captain.algorithm.disjoint;

/**
 * @author Darcy
 * Created By Darcy on 2018/6/27 下午5:46
 */
public class DisjSets {
    /**
     * Construct the disjoint sets object.
     *
     * @param numElements the initial number of disjoint sets.
     */
    public DisjSets(int numElements) {
        s = new int[numElements];
        for (int i = 0; i < s.length; i++)
            s[i] = -1;
    }

    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names.
     *
     * @param root1 the root of set 1.
     * @param root2 the root of set 2.
     */
    public void union(int root1, int root2) {
        if (s[root2] < s[root1])  // root2 is deeper
            s[root1] = root2;        // Make root2 new root
        else {
            if (s[root1] == s[root2])
                s[root1]--;          // Update height if same
            s[root2] = root1;        // Make root1 new root
        }
    }

    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     *
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int find(int x) {
        if (s[x] < 0)
            return x;
        else
            return s[x] = find(s[x]);
    }

    private int[] s;

    public static void reverseString(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        System.out.print(stringBuffer.reverse());
    }

    public static void main(String[] args) {
        String originalString = "good word!";
        reverseString(originalString);
    }
      /*  int NumElements = 128;
        int NumInSameSet = 16;

        DisjSets ds = new DisjSets(NumElements);
        int set1, set2;

        for (int k = 1; k < NumInSameSet; k *= 2) {
            for (int j = 0; j + k < NumElements; j += 2 * k) {
                set1 = ds.find(j);
                set2 = ds.find(j + k);
                ds.union(set1, set2);
            }
        }

        for (int i = 0; i < NumElements; i++) {
            System.out.print(ds.find(i) + "*");
            if (i % NumInSameSet == NumInSameSet - 1)
                System.out.println();
        }
        System.out.println();*/
        /*    int f = new DisjSets(5).f(5);
        System.out.println("f = " + f);*/

    public int f(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * f(n - 1);
        }
    }
}
