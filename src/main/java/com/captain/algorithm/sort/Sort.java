package com.captain.algorithm.sort;


/**
 * @author Darcy
 * Created By Darcy on 2018/6/26 上午10:48
 */
public class Sort {
    /**
     * 插入排序
     *
     * @param a 目标数组
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            /*带插入的元素*/
            AnyType tmp = a[p];
            /*提前结束循环的哨兵:tmp.compareTo(a[j - 1]) < 0*/
            /*当目标元素小于当前循环变量的前一个元素时*/
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                /*大的值前后移动一位*/
                a[j] = a[j - 1];
            }
            /*当循环结束时,就是找不到比目标元素tmp小的元素了,那么j的位置就是合适位置.*/
            a[j] = tmp;
        }
    }

    /**
     * 增量序列:h = N/2 的希尔排序.
     * 对h个独立的子数组执行一次插入排序.
     *
     * @param a 目标数组.需要排序的数组
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] a) {
        int j;
        /*设置增量序列gap= N/2*/
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= gap &&
                        tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }


    /**
     * 计算堆左儿子节点位置
     *
     * @param i 节点索引
     * @return 左儿子节点位置
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 堆内方法,恢复堆有序的方法
     *
     * @param a 素组
     * @index i 需要下滤的位置
     * @int n 堆的大小
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown(AnyType[] a, int i, int n) {
        /*孩子节点索引*/
        int child;
        /*需要下滤的值*/
        AnyType tmp;

        /*构建二叉堆,max堆*/
        for (tmp = a[i]; leftChild(i) < n; i = child) {
            /*找到当前i的左孩子节点*/
            child = leftChild(i);
            /*如果child不是末尾,并且左儿子小于右儿子 child自增1*/
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            /*目标数据比child小*/
            if (tmp.compareTo(a[child]) < 0) {
                /*大值上浮*/
                a[i] = a[child];
            } else {
                break;
            }
        }
        /*i是tmp在树有序的位置*/
        a[i] = tmp;
    }

    /**
     * 堆排序算法 最大堆,有序指的是 当一个颗二叉树的每个节点都大于等于它的两个子节点时,它被称为堆有序
     * 每次deleteMax后 堆都缩小1,将删除的最大元素放入.依次类推, 这要就完成排序了
     *
     * @param a 需要排序的数组
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void heapsort(AnyType[] a) {
        /* buildHeap */
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            percDown(a, i, a.length);
        }
        /* deleteMax */
        for (int i = a.length - 1; i > 0; i--) {
            /*根节点和最后一个元素交换位置 相当于deleteMax*/
            swapReferences(a, 0, i);
            /*将破坏堆有序的元素(即刚才交换到根节点的元素)下滤*/
            percDown(a, 0, i);
        }
    }


    /**
     * 归并排序算法
     *
     * @param a 需要排序的算法
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * 递归分而治之的方法
     *
     * @param a        目标数组
     * @param tmpArray 结果数组
     * @param left     左索引
     * @param right    右索引
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a, AnyType[] tmpArray,
                   int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * 合并子数组的两个半排序的内部方法
     *
     * @param a        可排序的数组
     * @param tmpArray 放置合并结果的数组
     * @param leftPos  子数组的最左索引
     * @param rightPos 下半数组的开始索引
     * @param rightEnd 子数组的最右索引
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        /*临时数组索引位置*/
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        /*将较小的元素放入tmpArray数组,同时tmpPos向前移动一步*/
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        /* 复制最左边的剩余部分*/
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }
        /*复制最右边的剩余部分*/
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        /*排序完成tmp复制回来*/
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    /**
     * Quicksort algorithm.
     *
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 3;

    /**
     * 交换数组a中位置1和位置2的元素
     *
     * @param a      目标数组
     * @param index1 位置1
     * @param index2 位置2
     */
    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     * Return median of left, center, and right.
     * Order these and hide the pivot.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a, int left, int right) {
        if (left + CUTOFF <= right) {

            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            quicksort(a, left, i - 1);    // Sort small elements
            quicksort(a, i + 1, right);   // Sort large elements
        } else  // Do an insertion sort on the subarray
        {
            insertionSort(a, left, right);
        }
    }

    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     *
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSelect(AnyType[] a, int k) {
        quickSelect(a, 0, a.length - 1, k);
    }

    /**
     * Internal selection method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k     the desired index (1 is minimum) in the entire array.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect(AnyType[] a, int left, int right, int k) {
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            if (k <= i)
                quickSelect(a, left, i - 1, k);
            else if (k > i + 1)
                quickSelect(a, i + 1, right, k);
        } else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }


    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;

    private static void checkSort(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != i) {
                System.out.println("Error at " + i);
            }
        }
        System.out.println("Finished checksort");
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        for (theSeed = 0; theSeed < 20; theSeed++) {
            Random.permute(a);
            Sort.insertionSort(a);
            checkSort(a);

            Random.permute(a);
            Sort.heapsort(a);
            checkSort(a);

            Random.permute(a);
            Sort.shellSort(a);
            checkSort(a);

            Random.permute(a);
            Sort.mergeSort(a);
            checkSort(a);

            Random.permute(a);
            Sort.quicksort(a);
            checkSort(a);

            Random.permute(a);
            Sort.quickSelect(a, NUM_ITEMS / 2);
            System.out.println(a[NUM_ITEMS / 2 - 1] + " " + NUM_ITEMS / 2);
        }


        Integer[] b = new Integer[10_000_000];
        for (int i = 0; i < b.length; i++) {
            b[i] = i;
        }

        Random.permute(b);
        long start = System.currentTimeMillis();
        Sort.quickSelect(b, b.length / 2);
        long end = System.currentTimeMillis();
        System.out.println("Timing for Section 1.1 example: ");
        System.out.println("Selection for N = " + b.length + " takes " +
                (end - start) + "ms.");
        System.out.println(b[b.length / 2 - 1] + " " + b.length / 2);

    }
}
