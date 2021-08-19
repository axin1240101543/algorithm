package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月19日 09:44:23
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * dividend / divisor = quotient
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 *
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 **/
public class Problem_029_DivideTwoIntegers {

    public static void main(String[] args) {
        Solution solution = new Problem_029_DivideTwoIntegers().new Solution();
        int add = solution.add(-2147483648, 1);
        System.out.println(add);
        int minus = solution.minus(9, 10);
        System.out.println(minus);
        int multi = solution.multi(-9, -10);
        System.out.println(multi);
        System.out.println(solution.to(-1));
        System.out.println(solution.to(1));
        System.out.println(solution.printNumBinary(9));
        int divide = solution.divide(-2147483648, -3);
        System.out.println(divide);
    }
    class Solution {

        /**
         * @param dividend 被除数
         * @param divisor 除数
         * @return
         * 除法用abs来除
         * 问题：最小值比最大值多一个
         * 可能1：a是系统最小  b是系统最小 1
         * 可能2：a不是系统最小 b是系统最小 0
         * 可能3：a是系统最小 b不是系统最小（因为正数表示不了系统最小）
         * (a - 1) / b  = quotient
         * a - (b * quotient) = c（没有除的数）
         * result = quotient + (c / b)
         *
         * 如果除数是-1  那么系统最小 / -1  等于一个系统最大 + 1  result = 系统最大
         *
         * 可能4：a不是系统最小 b不是系统最小
         * 直接使用abs相除
         * 0110100
         * 0000010
         * 乘法：mult = 0110100*2^0 +  0110100*2^1
         *
         * a = b * 2^甲 + b * 2^乙  + b * 2^丙
         * b往左移，不能移动了（不能超过a），那么就知道甲的位置，这个位置设为1
         * 然后a - b
         * b往左移，不能移动了（不能超过a），那么就知道乙的位置，这个位置设为1
         * 然后a - b
         * b往左移，不能移动了（不能超过a），那么就知道丙的位置，这个位置设为1
         * 最后得到了结果
         */
        public int divide(int dividend, int divisor) {
            //除数是系统最小
            if (divisor == Integer.MIN_VALUE){
                return dividend == Integer.MIN_VALUE ? 1 : 0;
            }
            //被除数是系统最小 除数不是系统最小
            if (dividend == Integer.MIN_VALUE){
                //除数是-1  那么系统最小 / -1  等于一个系统最大 + 1  result = 系统最大
                if (divisor == to(1)){
                    return Integer.MAX_VALUE;
                }
                /*int baseQuotient = div(add(dividend, 1), divisor);
                return add(baseQuotient, div(minus(dividend, multi(baseQuotient, divisor)), divisor));*/
                int res = div(add(dividend, 1), divisor);
                return add(res, div(minus(dividend, multi(res, divisor)), divisor));
            }
            //被除数不是系统最小 除数不是系统最小
            return div(dividend, divisor);
        }

        /**
         * 被除数不是系统最小 除数不是系统最小 相除
         * @param a
         * @param b
         *
         * 可能4：a不是系统最小 b不是系统最小
         * 直接使用abs相除
         * 0110100
         * 0000010
         * 乘法：mult = 0110100*2^0 +  0110100*2^1
         *
         * a = b * 2^甲 + b * 2^乙  + b * 2^丙
         * b往左移，不能移动了（不能超过a），那么就知道甲的位置，这个位置设为1
         * 然后a - b
         * b往左移，不能移动了（不能超过a），那么就知道乙的位置，这个位置设为1
         * 然后a - b
         * b往左移，不能移动了（不能超过a），那么就知道丙的位置，这个位置设为1
         * 最后得到了结果
         */
        public int div(int a, int b){
            int x = isNegative(a) ? to(a) : a;
            int y = isNegative(b) ? to(b) : b;
            int res = 0;
            //为什么这么设置 因为从右往左移动不用担心符号位的问题
            for (int i = 31; i > to(1); i = minus(i,1)){
                //x往右移N位，发现能大于等于 y即可
                if ((x >> i) >= y){
                    //然后在i位置设置1  即1左移i位
                    res = res | (1 << i);
                    //最后x减去y左移i位的这一个1
                    x = minus(x, y << i);
                }
            }
            return isNegative(a) ^ isNegative(b) ? to(res) : res;
        }

        // 现场福利函数
        public String printNumBinary(int num) {
            StringBuilder builder = new StringBuilder();
            for (int i = 31; i >= 0; i--) {
                builder.append(((num >> i) & 1) == 0 ? '0' : '1');
            }
            return builder.toString();
        }

        /**
         * 判断是否是负数
         * @param n
         * @return
         */
        public boolean isNegative(int n){
            return n < 0;
        }

        /**
         * 011010 = 26
         * 001101 = 13
         * ------
         *    011010
         *   0
         *  01101000
         * 011010000
         * = 101010010 = 338
         *
         * 最开始是右移0位  左移0位  因为此时如果最右位是1  那么直接加上a
         * a右移1位 a后面加一个0  b左移一位 判断最右位是0还是1 1就加上此时移位过的a  否则不加
         *
         * @param a
         * @param b
         * @return
         */
        public int multi(int a, int b){
            int res = 0;
            while (b != 0){
                if ((b & 1) != 0){
                    res = add(res, a);
                }
                a = a << 1;
                b = b >>> 1;
            }
            return res;
        }

        /**
         * 0101 + 0110 = 1011
         *
         * 异或 -> 无进位相加
         * 0101 ^ 0110 = 0011
         * 进位  -> 两个数
         * 0101 & 0110 = 0100 << 1 = 1000
         *
         * 0011 ^ 1000 = 1011
         * 0011 & 1000 = 0000 << 1 = 0000
         *
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *
         * 01010  + 01111 = 11001
         *
         * 01010 ^ 01111 = 00101
         * 01010 & 01111 = 01010 << 1 = 10100
         *
         * 00101 ^ 10100 = 10001
         * 00101 & 10100 = 00100 << 1 = 01000
         *
         * 10001 ^ 01000 = 11001
         * 10001 & 01000 = 00000 << 1 = 0000
         *
         * @return
         */
        public int add(int a, int b){
            //2147483645
            int sum = a;
            while (b != 0){
                sum = a ^ b;
                b = (a & b) << 1;
                a = sum;
            }
            return sum;
        }

        /**
         * a - b = a + (-b)
         * @param a
         * @param b
         * @return
         */
        public int minus(int a, int b){
            return add(a, to(b));
        }

        /**
         * 负数变正数 -> 取反 + 1
         * 正数变负数 -> 取反 + 1
         * @param n
         * @return
         */
        public int to(int n){
            return add(~n,1);
        }
    }

}

