package org.yangxin.test.sort;

import java.util.Arrays;

/**
 * 插入排序（用整数）
 *
 * @author yangxin
 */
public class Sort {

    public static void main(String[] args) {
        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] array = {-2, 8, 0, 3, 4, 1};

        // 快速排序
        quickSort(array);

        // 归并排序
//        mergeSort(array);

        // 插入排序
//        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] array, int left, int right) {
        // 如果是这种情况，我们认为已经有序，则不处理直接返回
        if (left >= right) {
            return;
        }

        // 取得枢纽元素的下标
        int keyIndex = partition(array, left, right);

        // 对左区间进行快速排序
        quickSort(array, left, keyIndex - 1);
        // 对右区间进行快速排序
        quickSort(array, keyIndex + 1, right);
    }

    /**
     * 使枢纽元素（枢纽元素初始默认为array[0]）处于待排序元素中的合适位置，并返回枢纽元素的下标
     */
    private static int partition(int[] array, int left, int right) {
        // 暂存枢纽元素（也相当于在下标为0处挖了一个坑）
        int key = array[left];

        int i = left, j = right;
        while (i < j) {
            // 从右边开始找，找到第一个比枢纽元素小的元素的位置
            while (i < j && array[j] >= key) {
                j--;
            }

            // 此时j指向从右边开始第一个比枢纽元素小的元素，则进行交换（相当于拆东墙补西墙）
            array[i] = array[j];

            // 从左边开始找，找到第一个比枢纽元素大的元素的位置
            while (i < j && array[i] <= key) {
                i++;
            }

            // 此时i指向从左边开始第一个比枢纽元素大的元素，则进行交换（相当于拆东墙补西墙）
            array[j] = array[i];
        }

        // 此时i和j相等
        array[i] = key;
        return i;
    }

    /**
     * 归并排序
     */
    private static void mergeSort(int[] array) {
        int[] aux = new int[array.length];
        // 注意，这里传入的入参right的值为array.length-1
        mergeSort(array, 0, array.length - 1, aux);
    }

    /**
     * 将[left, right]区间内的元素排序，以使其有序
     */
    private static void mergeSort(int[] array, int left, int right, int[] aux) {
        if (left < right) {
            // 取中位元素的下标，用于“分”
            int mid = left + (right - left) / 2;

            // 将左区间的元素排序
            mergeSort(array, left, mid, aux);
            // 将右区间的元素排序
            mergeSort(array, mid + 1, right, aux);

            // 归并，也就是所谓的”治“
            merge(array, left, mid, right, aux);
        }
    }

    /**
     * 合并
     */
    private static void merge(int[] array, int left, int mid, int right, int[] aux) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                aux[k++] = array[i++];
            } else {
                aux[k++] = array[j++];
            }
        }

//        while (i <= mid) {
//            aux[k++] = array[i++];
//        }
        if (i <= mid) {
            System.arraycopy(array, i, aux, k, mid - i + 1);
        }
//        while (j <= right) {
//            aux[k++] = array[j++];
//        }
        if (j <= right) {
            System.arraycopy(array, j, aux, k, right - j + 1);
        }

//        k = 0;
//        while (left <= right) {
//            array[left++] = aux[k++];
//        }
        System.arraycopy(aux, 0, array, left, right - left + 1);
    }

    /**
     * 插入排序（边比较，边排序）
     */
    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                int tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
            }
        }
    }
}
