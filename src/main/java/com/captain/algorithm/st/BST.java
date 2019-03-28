package com.captain.algorithm.st;

import com.captain.algorithm.StdOut;
import com.captain.algorithm.test1.Queue;

/**
 * @author Darcy
 * Created By Darcy on 2019-03-18 16:42
 */
public class BST<Key extends Comparable<Key>, Value> {
    /**
     * 二叉查找树的根节点
     */
    private Node root;

    /**
     * 内嵌的节点类
     */
    private class Node {

        /**
         * 键
         */
        private Key key;

        /**
         * 值
         */
        private Value value;

        /**
         * left 左
         * right 右
         */
        private Node left, right;

        /**
         * 以该节点为根的子树中的节点总数
         */
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }

    }

    public int size() {
        return size(root);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key min() {
        return min(root).key;
    }


    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return min(x.right);
    }

    public Key max() {
        return max(root).key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }

    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;

    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }

    }

    public int rank(Key key) {
        return rank(key, root);
    }


    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }


    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        /*根据二叉树性质查找key*/
        if (cmp > 0) {
            return delete(x.right, key);
        } else if (cmp < 0) {
            return delete(x.left, key);
        } else {
            /*找到要删除的节点 x*/
            /*左子树为null, 返回右子树, x就为null. 会自己被回收*/
            if (x.left == null) {
                return x.right;
            }
            /**/
            if (x.right == null) {
                return x.left;
            }
            /*要删除的x左右子树都不为null 的情况, 将x保存为t*/
            Node t = x;
            /*找到t右子树最小的node. 赋值给x*/
            x = min(t.right);

            x.right = deleteMin(t.right);
            x.left = t.left;

        }
        /*重新计算x的节点数量*/
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    private void print(Node x) {
        if (x==null) {
            return;
        }
        print(x.right);
        StdOut.println(x.key);
        print(x.right);
    }
}
