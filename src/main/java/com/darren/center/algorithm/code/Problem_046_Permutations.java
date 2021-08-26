package com.darren.center.algorithm.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Author: Darren
 * Date: 2021-08-26 15:19:01
 * Version: 1.0
 * Description:
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Problem_046_Permutations {
    public static void main(String[] args) {
        Solution solution = new Problem_046_Permutations().new Solution();
        int[] nums = {1,2,3};
        solution.permute(nums);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            process(nums, 0, ans);
            return ans;
        }

        /**
         * 深度优先遍历
         * @param nums
         * @param index
         * @param ans
         */
        public void process(int[] nums, int index, List<List<Integer>> ans){
            if (index == nums.length){
                List<Integer> cur = new ArrayList<>();
                for (int num : nums) {
                    cur.add(num);
                }
                ans.add(cur);
            }else{
                for (int i = index; i < nums.length; i++) {
                    swap(nums, index, i);
                    process(nums, index + 1, ans);
                    swap(nums, index, i);
                }
            }
        }

        public void swap(int[] arr, int i, int j){
            int tem = arr[i];
            arr[i] = arr[j];
            arr[j] = tem;
        }

        public List<List<Integer>> permute1(int[] nums) {
            HashSet<Integer> res = new HashSet<>();
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                res.add(num);
            }
            f(ans, res, path);
            return ans;
        }

        /**
         * 暴力方法 不需要恢复现场
         * @param ans 得到的结果
         * @param res 剩余的数
         * @param path 已经选择过的数
         */
        public void f(List<List<Integer>> ans, HashSet<Integer> res, List<Integer> path) {
            if (res.isEmpty()) {
                ans.add(path);
            } else {
                for (Integer re : res) {
                    List<Integer> curPath = new ArrayList<>(path);
                    curPath.add(re);
                    HashSet<Integer> remove = remove(res, re);
                    f(ans, remove, curPath);
                }
            }
        }

        public HashSet<Integer> remove(HashSet<Integer> res, Integer num){
            HashSet<Integer> remove = new HashSet<>(res);
            remove.remove(num);
            return remove;
        }
    }
}
