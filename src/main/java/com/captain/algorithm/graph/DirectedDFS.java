package com.captain.algorithm.graph;

/**
 * 有向图的可达性. 深度优先算法.
 * @author Darcy
 * Created By Darcy on 2018/8/7 下午5:52
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

}
