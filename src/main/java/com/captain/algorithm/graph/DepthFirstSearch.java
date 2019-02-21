package com.captain.algorithm.graph;

/**
 * 深度优先算法 走迷宫
 *
 * @author Darcy
 * Created By Darcy on 2018/8/2 下午2:50
 */
public class DepthFirstSearch {
    /**
     * 顶点是否被标记
     */
    private boolean[] marked;

    /**
     * 走的次数
     */
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];

    }

    /**
     * 标记所有相邻点.
     *
     * @param G 无向图
     * @param v 顶点
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
