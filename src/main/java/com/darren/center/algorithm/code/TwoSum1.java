package com.darren.center.algorithm.code;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: 01403292
 * Date: 2021-08-11 14:20:43
 * Version: 1.0
 * Description:
 * //给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * //
 * // 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * //
 * // 你可以按任意顺序返回答案。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //输入：nums = [2,7,11,15], target = 9
 * //输出：[0,1]
 * //解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入：nums = [3,2,4], target = 6
 * //输出：[1,2]
 * //
 * //
 * // 示例 3：
 * //
 * //
 * //输入：nums = [3,3], target = 6
 * //输出：[0,1]
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 2 <= nums.length <= 104
 * // -109 <= nums[i] <= 109
 * // -109 <= target <= 109
 * // 只会存在一个有效答案
 * //
 * //
 * // 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * // Related Topics 数组 哈希表
 */
public class TwoSum1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 7, 15};
        int target = 14;
        int[] t = t(nums, target);
        Arrays.stream(t).forEach(v -> System.out.println(v));
    }

    private static int[] t(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }

        //如果值重复  那么只会保留最大的值的下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            //如果第二个值在集合中 且 下标不等于当前值的下标，那么就找到了第二个值
            if (map.containsKey(other) && i != map.get(other)) {
                return new int[]{i, map.get(other)};
            }
        }
        return new int[]{0, 0};
    }

}
