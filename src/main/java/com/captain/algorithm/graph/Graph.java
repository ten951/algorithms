package com.captain.algorithm.graph;

import com.captain.algorithm.In;
import com.captain.algorithm.test1.Bag;

/**
 * 无向图(邻接表)
 *
 * @author Darcy
 * Created By Darcy on 2018/8/2 下午2:34
 */
public class Graph {
    /**
     * 定点数目
     */
    private final int V;
    /**
     * 边的数目
     */
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {

            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);

        }

    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 添加边
     *
     * @param v 顶点
     * @param w 顶点
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
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
}
