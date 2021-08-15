package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月14日 19:22:00
 **/
public class Problem_008_StringToIntegerAtoi {

    public static void main(String[] args) {
        Solution solution = new Problem_008_StringToIntegerAtoi().new Solution();
        solution.myAtoi("words and 987");
    }
    class Solution {
        public int myAtoi(String s) {
            if (s == null || s.equals("")){
                return 0;
            }
            s = s.trim();
            boolean r = s.startsWith("+") || s.startsWith("-");
            //start来到第一个不是0的位置
            int start = r ? 1 : 0;
            for(; start < s.length(); start++){
                if (s.charAt(start) != '0'){
                    break;
                }
            }
            //end来到最左的不是字符的位置
            int end = - 1;
            for(int i = s.length() - 1; i >= start; i--){
                if (s.charAt(i) < '0' || s.charAt(i) > '9'){
                    end = i;
                }
            }
            s = (r ? String.valueOf(s.charAt(0)) : "") + (s.substring(start, (end == -1) ? s.length() : end));
            if (s.equals("")){
                return 0;
            }
            char firstChar = s.charAt(0);
            if (firstChar != '+' && firstChar != '-' && (firstChar < '0' || firstChar > '9')){
                return 0;
            }
            if ((firstChar == '+' || firstChar == '-') && s.length() == 1){
                return 0;
            }
            for (int i = 1; i < s.length(); i++){
                if (s.charAt(i) < '0' || s.charAt(i) > '9'){
                    return 0;
                }
            }

            boolean negative = s.startsWith("-");
            s = r ? s.substring(1, s.length()) : s;
            int m = Integer.MIN_VALUE / 10;
            int o = Integer.MIN_VALUE % 10;
            int result = 0;
            int cur = 0;
            for (int i = 0; i < s.length(); i++) {
                cur = '0' - s.charAt(i);
                if(result < m || (result == m && cur < o)){
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + cur;
            }
            if (!negative && result == Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }
            return negative ? result : Math.abs(result);
        }
    }
}

