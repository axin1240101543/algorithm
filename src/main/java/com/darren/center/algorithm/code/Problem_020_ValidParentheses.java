package com.darren.center.algorithm.code;

import java.util.Stack;

/**
 * Author: Darren
 * Date: 2021-08-18 11:17:31
 * Version: 1.0
 * Description:
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 *  有效字符串需满足：
 *  左括号必须用相同类型的右括号闭合。
 *  左括号必须以正确的顺序闭合。
 *
 *  示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 *  示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 *  示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 *  示例 4：
 * 输入：s = "([)]"
 * 输出：false
 *
 *
 *  示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 *  提示：
 *  1 <= s.length <= 104
 *  s 仅由括号 '()[]{}' 组成
 */
public class Problem_020_ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new Problem_020_ValidParentheses().new Solution();
        solution.isValid("((");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if (s == null || s.length() < 2) {
                return false;
            }
            char[] chars = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if ('(' == chars[i] || '[' == chars[i] || '{' == chars[i]) {
                    stack.push(chars[i]);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    Character pop = stack.pop();
                    if (('(' == pop && chars[i] != ')')
                            || ('[' == pop && chars[i] != ']')
                            || ('{' == pop && chars[i] != '}')) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }

}
