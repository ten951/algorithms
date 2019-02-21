package com.captain.algorithm.tree;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树:对于树中的每个节点X,它的左子树中所有项的值小于X中的项,而它的右子树中的所有项的值都大于X中的值.
 *
 * @author Darcy
 * Created By Darcy on 2018/6/20 下午2:32
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        /**
         * 数据
         */
        AnyType element;
        /**
         * 左节点
         */
        BinaryNode<AnyType> left;
        /**
         * 右节点
         */
        BinaryNode<AnyType> right;

        BinaryNode(AnyType element) {
            this(element, null, null);
        }

        BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<AnyType> root;


    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {

        return root == null;
    }


    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * 查询t树是否存在x数据
     * (递归写法,根据二叉查找树的性质)
     *
     * @param x 数据
     * @param t 树
     * @return 是否存在
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    /**
     * 递归查找最小节点的写法
     * 从根节点开始并且只要有左儿子就向左进行,终止点就是最小的元素
     *
     * @param t 目标树
     * @return 最小的节点
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * 非递归的写法 查找t树种最大的节点
     * 从根节点开始并且只要有右儿子就向右进行,终止点就是最大的元素
     *
     * @param t 目标数
     * @return 最大节点
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 将x数据插入t树中,沿树查找,如果找到x,什么也不做.否则将x插入到遍历路径的最后一个点上.
     *
     * @param x 目标数据
     * @param t 目标树
     * @return root节点
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    /**
     * 打印树
     *
     * @param t 树
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * 删除树t中的x数据
     *
     * @param x 目标数据
     * @param t 目标树
     * @return 树
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return t;
        }
        /*判断x和t.element数据的大小.能确定x在t树种的位置*/
        int compareResult = x.compareTo(t.element);
        /*x小于t.element 说明x在t树的左子树*/
        if (compareResult < 0) {
            t.left = remove(x, t.left);
            /*x大于t.element 说明x在t树的右子树*/
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
            /*找到x的位置,判断这个节点的儿子情况, 发现x具有2个儿子节点, 那么就找到右子树中最小的节点,代替该节点的数据,并递归的删除那个节点*/
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
            /*找到x的位置 发现x是只有一个儿子节点, 让当前节点的父节点t,等当前节点的儿子节点就可以了*/
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }
}
