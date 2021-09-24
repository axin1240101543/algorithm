package com.darren.center.algorithm.ext;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月01日 20:06:35
 *
 * 100 = 3 + 69258 / 714
 * 100 = 82 + 3546 / 197
 *
 * 等号右边的部分，可以写成 p1 + p2 / p3的形式
 * 要求p1和p2和p3，所使用的数字，必须把1~9使用完全，并且不重复
 * 满足的话，我们就说，形如p1 + p2 / p3，一个有效的"带分数"形式
 * 要求，p2 / p3 必须整除
 *
 * 输入N，返回N有多少种带分数形式
 * 100，有11种带分数形式
 *
 * 输入的N，N  < 10的8次方
 * */
public class AddDevideNum {

    //傻缓存  第一次计算之后缓存下来
    public static Map<Integer, Integer> map = new HashMap<>();

    //10 ^ 8 = 10000 0000
    public static final int[] arr = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public static void main(String[] args) {
//        int swap = swap(457896321, 5, 3);
//        System.out.println(swap);
        int ways = ways(100);
        System.out.println(ways);

        Map<String, Integer> ans = new HashMap<>();
        ways1(77, ans);
        ans.forEach((k,v) ->{
            System.out.println(k + " : "  + v);
        });
    }


    /**
     * 打印一个数的阶乘
     * @param num
     * @param index
     */
    public static void factorial(int num, int index){
        if (index == -1){
            System.out.println(num);
        }else {
            for (int i = index; i >= 0; i--) {
                int lv = num / arr[index] % 10;
                int rv = num / arr[i] % 10;
                int next = num - (lv - rv) * arr[index] + (lv - rv) * arr[i];
                factorial(next, index - 1);
            }
        }
    }


    public static int count = 0;
    public static int sumCount = 0;
    public static void ways1(int n, Map<String, Integer> ans){
        process1(123456789, 8, n, ans);
    }
    public static void process1(int num, int index, int n, Map<String, Integer> ans){
        //已经加工好了一个数，只需要分配符号的位置
        if (index == -1){
//            System.out.println(num);
            count++;
            //加号的范围是[8 .. 2]
            //1[234567]89
            //8 765432 10
            for(int add = 8; add >= 2; add--){
                int p1 = num / arr[add];
                // p2 + p3 部分
                int remain = num % arr[add];
                //除号的活动范围
                //12345[678]9
                //87654 321 0
                for (int divide = add / 2; divide >= 1; divide--){
                    sumCount++;
                    int p2 = remain / arr[divide];
                    int p3 = remain % arr[divide];
                    if(p2 % p3 == 0){
                        int res = p1 + p2 / p3;
                        if (n == res){
                            ans.put(p1 + "_" + p2 + "_" + p3, res);
                        }
                    }
                }
            }
        }else{
            //9!
            //index = 8  index位置的数和num[8 ... 0]范围的数进行交换
            //index = 7  index位置的数和num[7 ... 0]范围的数进行交换
            //...
            //9!
            for (int i = index; i >= 0; i--) {
                int next = swap(num, index, i);
                process1(next, index - 1, n, ans);
            }
        }
    }

    public static int ways(int n){
        if (map.isEmpty()){
            process(123456789, 8);
        }
        return map.containsKey(n) ? map.get(n) : 0;
    }

    // process, 当的数形成样子，num 123456789
    // index 该哪个位置的数字去指定了
    // 1、枚举所有的把1~9使用完全，并且不重复的九位数 -> 9! -> 362880
    // 2、考虑放 + 和放 / 的位置
    // a b c d e f g h i 越界（num）
    // 8 7 6 5 4 3 2 1 0 -1 （index）
    // + 放8位置 / 只能放 4 3 2 1 位置 （p1:a p2 / p3 必须整除必须整除）
    // + 放7位置 / 只能放 3 2 1 位置   （p1:ab p2 / p3 必须整除必须整除）
    // + 放6位置 / 只能放 3 2 1 位置    （p1:abc p2 / p3 必须整除必须整除）
    // + 放5位置 / 只能放 2 1 位置    （p1:abcd p2 / p3 必须整除必须整除）
    // + 放4位置 / 只能放 2 1 位置    （p1:abcde p2 / p3 必须整除必须整除）
    // + 放3位置 / 只能放 1 位置    （p1:abcdef p2 / p3 必须整除必须整除）
    // + 放2位置 / 只能放 1 位置    （p1:abcdefg p2 / p3 必须整除必须整除）
    // 一共16次  362880*16 = 5806080 < 10^8(100000000)
    public static void process(int num, int index){
        //已经加工好了一个数，只需要分配符号的位置
        if (index == -1){
            //加号的范围是[8 .. 2]
            //1[234567]89
            //8 765432 10
            for(int add = 8; add >= 2; add--){
                int p1 = num / arr[add];
                // p2 + p3 部分
                int remain = num % arr[add];
                //除号的活动范围
                //12345[678]9
                //87654 321 0
                for (int divide = add >> 1; divide >= 1; divide--){
                    int p2 = remain / arr[divide];
                    int p3 = remain % arr[divide];
                    if(p2 % p3 == 0){
                        int res = p1 + p2 / p3;
                        if (!map.containsKey(res)){
                            map.put(res, 1);
                        }else {
                            map.put(res, map.get(res) + 1);
                        }
                    }
                }
            }
        }else{
            //从左往右指定数字，让后面的数交换到index位置
            //index = 8  index位置的数和num[8 ... 0]范围的数进行交换
            //index = 7  index位置的数和num[7 ... 0]范围的数进行交换
            //...
            //9!
            for (int i = index; i >= 0; i--) {
                int next = swap(num, index, i);
                process(next, index - 1);
            }
        }
    }

    /**
     * 将num中l位置的数字和r位置的数字交换
     * 1、提取num中l位置的数和l位置的数
     * int lNum = (num / arr[l]) % 10
     * int rNum = (num / arr[r]) % 10
     * 987654321
     * 876543210 (下标)
     * l = 5 lNum = 6
     * 987654321 / 100000 = 9876 % 10 = 6
     * r = 1 rNum = 2
     * 987654321 / 10 = 98765432 % 10 = 2
     *
     * 2、num - (lNum - rNum) * arr[l] + (lNum - rNum) * arr[r]
     * 987654321
     * 876543210 (下标)
     * 987654321 - (6 - 2) * 100000 = 987254321
     * 987254321 + (6 - 2) * 10 = 987254361
     *
     *
     * @param num
     * @param l
     * @param r
     */
    public static int swap(int num, int l , int r){
        //l和r 从右往左 从0开始
        //拿到l和r位置的数
        int lNum = (num / arr[l]) % 10;
        int rNum = (num / arr[r]) % 10;
        //System.out.println(lv);
        //System.out.println(rv);
        //差值 -+
        return num - (lNum - rNum) * arr[l] + (lNum - rNum) * arr[r];
    }

}

