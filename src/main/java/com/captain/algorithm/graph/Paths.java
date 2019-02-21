package com.captain.algorithm.graph;

import com.captain.algorithm.StdOut;
import com.captain.algorithm.test1.Stack;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/2 下午5:38
 */
public class Paths {
    /**
     * 这个顶点上是否调用了dfs
     */
    private boolean[] marked;
    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 起点
     */
    private final int s;

    public Paths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 5);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(0, 2);

        BreadthFirstPaths paths = new BreadthFirstPaths(graph, 0);
        for (int v = 0; v < graph.V(); v++) {
            StdOut.print(0 + " to " + v + ": ");
            if (paths.hasPathTo(v))
                for (int x : paths.pathTo(v))
                    if (x == 0) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
