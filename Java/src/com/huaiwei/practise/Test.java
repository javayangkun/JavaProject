package com.huaiwei.practise;


import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
       /* int a = 24352;
        char c = '张';
        System.out.println((char) a);
*/
        //System.out.println((int)c);
        //Java中的二维数组不是像C语言中的一样，缺少的部分不会补全零
        int[][] nums = {{122354,5224},{525452,12255,2525}};

        /*for (int[] num : nums) {
            for (int i : num) {
                System.out.println(i);
            }
        }*/
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
               // System.out.println(nums[i].length);
                System.out.println(nums[i][j]);
            }
        }
    }
}
