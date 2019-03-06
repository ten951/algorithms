package com.captain.algorithm.test2;

/**
 * 运行时间和输入无关(即便给一个有序数组运行时间和无序数组一样)
 * 数据移动是最少的(交换次数和数组大小成线性关系)
 *
 * @author Darcy
 * Created By Darcy on 2018/6/6 下午5:27
 */
public class Selection<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] a) {
        final int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            /*内层循环从i+1开始, 查找比min小的j, 如果找不到i就是最小的*/
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            /*交换当前i和min的位置*/
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Selection<String> stringSelection = new Selection<String>();
        String[] strings = {"A", "D", "B"};
        stringSelection.sort(strings);
        stringSelection.show(strings);

    }
}
