package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月19日 20:15:45
 **/
public class Problem_034_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        Solution solution = new Problem_034_SearchInRotatedSortedArray().new Solution();
        int[] nums = {3, 1};
        int target = 1;
        solution.search(nums, target);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            if(nums == null || nums.length == 0){
                return 0;
            }

            int l = 0;
            int r = nums.length - 1;
            int m = 0;
            while(l <= r){
                m = (l + r) >> 1;
                if(target == nums[m]){
                    return m;
                }


            }
            return -1;
        }

        public int search2(int[] nums, int target) {
            if (nums == null || nums.length == 0){
                return 0;
            }

            int l = 0;
            int r = nums.length - 1;
            int m = 0;
            while (l <= r){
                m = (l + r) / 2;
                //target == nums[m]
                if (target == nums[m]){
                    return m;
                }

                //nums[l] = nums[m] = nums[l]
                //2222222222222222 你并不知道target在这群2里面的那个位置
                if (nums[m] == nums[l] && nums[m] == nums[r]){
                    while (l != m && nums[l] == nums[m]){
                        l++;
                    }
                    //l来到重点位置 还是没有遇到target
                    if (l == m){
                        l = m + 1;
                        continue;
                    }
                }

                //l m r三个位置的值 肯定不相等  nums[m] != nums[l] || nums[m] != nums[r]
                if (nums[m] != nums[l]){
                    if (nums[m] > nums[l]){
                        //升序  nums的l...m范围
                        if (target >= nums[l] && target < nums[m]){
                            r = m - 1;
                        }else{
                            l = m + 1;
                        }
                    }else{
                        //nums[m] < nums[l] 发生了旋转 中点位置小于左边界位置
                        if (target > nums[m] && target <= nums[r]){
                            l = m + 1;
                        }else{
                            r = m - 1;
                        }
                    }
                }else{
                    //nums[m] == nums[l] nums[m] != nums[r]
                    if (nums[m] < nums[r]){
                        //升序  nums的m...r范围
                        if (target > nums[m] && target <= nums[r]){
                            l = m + 1;
                        }else{
                            r = m - 1;
                        }
                    }else{
                        //nums[m] > nums[r] 发生了旋转 中点位置大于右边界位置
                        if (target >= nums[l] && target < nums[m]){
                            r = m - 1;
                        }else{
                            l = m + 1;
                        }
                    }
                }
            }
            return -1;
        }
    }

}

