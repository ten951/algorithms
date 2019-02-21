package com.captain.algorithm.hash;

import java.util.Random;

/**
 * @author Darcy
 * Created By Darcy on 2018/6/22 下午6:11
 */
public class CuckooHashTable<AnyType> {
    /**
     * 构建哈希表
     *
     * @param hf 散列函数
     */
    public CuckooHashTable(HashFamily<? super AnyType> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    /**
     * 构建哈希表.
     *
     * @param hf   散列函数
     * @param size 近似的如初化大小
     */
    public CuckooHashTable(HashFamily<? super AnyType> hf, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    private Random r = new Random();

    /**
     * 定义最大的装载因子
     */
    private static final double MAX_LOAD = 0.40;
    /**
     * 允许rehash的次数
     */
    private static final int ALLOWED_REHASHES = 1;

    /**
     * rehash次数
     */
    private int rehashes = 0;

    /**
     * 定义默认表的大小
     */
    private static final int DEFAULT_TABLE_SIZE = 101;

    /**
     * 散列函数
     */
    private final HashFamily<? super AnyType> hashFunctions;
    /**
     * 散列函数个数
     */
    private final int numHashFunctions;
    /**
     * 当前表
     */
    private AnyType[] array;
    /**
     * 当前表大小
     */
    private int currentSize;

    private boolean insertHelper1(AnyType x) {
        final int COUNT_LIMIT = 100;

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myhash(x, i);
                    if (array[pos] == null) {
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }
                int i = 0;
                do {
                    pos = myhash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                AnyType tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }
            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }

    private boolean insertHelper2(AnyType x) {
        final int COUNT_LIMIT = 100;
        while (true) {
            for (int count = 0; count < COUNT_LIMIT; count++) {
                int pos = myhash(x, count % numHashFunctions);

                AnyType tmp = array[pos];
                array[pos] = x;

                if (tmp == null) {
                    return true;
                } else {
                    x = tmp;
                }
            }
            if (++rehashes > ALLOWED_REHASHES) {
                expand();      // Make the table bigger
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }

    /**
     * Insert into the hash table. If the item is
     * already present, return false.
     *
     * @param x the item to insert.
     */
    public boolean insert(AnyType x) {
        if (contains(x)) {
            return false;
        }

        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }

        return insertHelper1(x);
    }

    private int myhash(AnyType x, int which) {
        int hashVal = hashFunctions.hash(x, which);
        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }

        return hashVal;
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        AnyType[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;
        for (AnyType str : oldArray) {
            if (str != null) {
                insert(str);
            }
        }
    }


    /**
     * Gets the size of the table.
     *
     * @return number of items in the hash table.
     */
    public int size() {
        return currentSize;
    }

    /**
     * Gets the length (potential capacity) of the table.
     *
     * @return length of the internal array in the hash table.
     */
    public int capacity() {
        return array.length;
    }

    /**
     * Method that searches all hash function places.
     *
     * @param x the item to search for.
     * @return the position where the search terminates, or -1 if not found.
     */
    private int findPos(AnyType x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myhash(x, i);
            if (array[pos] != null && array[pos].equals(x)) {
                return pos;
            }
        }

        return -1;
    }

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     * @return true if item was found and removed
     */
    public boolean remove(AnyType x) {
        int pos = findPos(x);
        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }
        return pos != -1;
    }

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains(AnyType x) {
        return findPos(x) != -1;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        doClear();
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }


    /**
     * Internal method to allocate array.
     *
     * @param arraySize the size of the array.
     */
    private void allocateArray(int arraySize) {
        array = (AnyType[]) new Object[arraySize];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    protected static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }

        for (; !isPrime(n); n += 2) {
        }

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
