package com.captain.algorithm.graph;

/**
 * 带权重的边的数据类型
 *
 * @author Darcy
 * Created By Darcy on 2018/8/21 下午2:42
 */
public class Edge implements Comparable<Edge> {
    /**
     * 顶点1
     */
    private final int v;
    /**
     * 顶点2
     */
    private final int w;
    /**
     * 权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 查看边的权重
     *
     * @return 边的权重
     */
    public double weight() {
        return weight;
    }

    /**
     * 边两端的顶点之一
     *
     * @return 边两端的顶点之一
     */
    public int either() {
        return v;
    }

    /**
     * 查看另一个顶点
     *
     * @param vertex 顶点
     * @return 另一个顶点
     */
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent Edge");
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
