package com.huaiwei.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 3, 11, 15, 7};
        int target = 9;


        int[] ints = twoSum(nums, target);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    /**
     * 从数组中求取两数之和，并返回这两个数据的下标
     *
     * @param nums   数据
     * @param target 目标
     * @return int[]
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 从数组中求取两数之和，并返回这两个数据的下标
     *
     * @param nums   数据
     * @param target 目标
     * @return int[]
     */
    private static int[] twoSum(int target, int[] nums) {
        Map<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashTable.containsKey(target - nums[i])) {
                return new int[]{hashTable.get(target - nums[i]), i};
            }
            hashTable.put(nums[i], i);
        }
        return new int[]{};
    }
}
