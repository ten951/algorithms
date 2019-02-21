package com.captain.algorithm.hash;

/**
 * 允许任意数量的散列函数
 *
 * @author eudora
 */
public interface HashFamily<AnyType> {
    /**
     * 根据which来选择散列函数
     *
     * @param x     值
     * @param which 散列类型
     * @return 散列值
     */
    int hash(AnyType x, int which);

    /**
     * 返回集合中散列函数的个数
     *
     * @return 数量
     */
    int getNumberOfFunctions();

    /**
     * 获取新的散列函数
     */
    void generateNewFunctions();
}
