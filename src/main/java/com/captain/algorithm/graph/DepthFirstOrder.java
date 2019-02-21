package com.captain.algorithm.graph;

import com.captain.algorithm.test1.Queue;
import com.captain.algorithm.test1.Stack;

/**
 * @author Darcy
 * Created By Darcy on 2018/8/17 下午3:09
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;


    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        this.pre = new Queue<>();
        this.post = new Queue<>();
        this.reversePost = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
