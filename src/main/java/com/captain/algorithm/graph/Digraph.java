package com.captain.algorithm.graph;

import com.captain.algorithm.test1.Bag;

/**
 * 有向图
 *
 * @author Darcy
 * Created By Darcy on 2018/8/7 下午4:43
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();

        }
    }

    public int V() {
        return V;
    }

    private int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }


    /**
     * v相邻的所有顶点
     *
     * @param v 定点
     * @return 迭代器
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 返回一个新的有向图,单方向是反的
     *
     * @return 有向图
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
