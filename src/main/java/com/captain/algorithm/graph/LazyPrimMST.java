package com.captain.algorithm.graph;

import com.captain.algorithm.test1.Queue;
import com.captain.algorithm.test3.MinPQ;

/**
 * 最小生成树的Prim算法的延时实现
 *
 * @author Darcy
 * Created By Darcy on 2018/8/22 下午4:17
 */
public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph G) {
        this.marked = new boolean[G.V()];
        this.mst = new Queue<>();
        this.pq = new MinPQ<>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
