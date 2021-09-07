package com.darren.center.algorithm.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:41:01
 * 从左往右的尝试模型1
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如“111”就可以转化为：
 * “AAA”、“KA”、“AK”
 * 给定一个只有数字字符组成的字符串str，返回有多少转化结果？
 **/
public class Base006_ConvertToLetterString {

    public static void main(String[] args) {
       String s = "111";
        char[] str = s.toCharArray();
        int res = process(str, 0);
        System.out.println(res);
    }

    //str[0...index - 1]已经转化完了  固定了
    //index之前的位置 如何转化已经做过决定了 不用再关心
    //index ... 往后有多少中转化方法
    public static int process(char[] str, int index){
        //index到了终止位置  那么有一种转化方法
        if (index == str.length){
            return 1;
        }

        //index的字符是0 那么无法转化 1 -> A
        if (str[index] == '0'){
            return 0;
        }

        //index的字符是1 那么有两种可能性
        // 1、index自己去转化，然后index + 1你自己去玩吧
        // 2、index和index+1组团去转化，然后index+2你自己去玩吧
        if (str[index] == '1'){
            //可能性1
            int res = process(str, index + 1);
            if (index + 1 < str.length){
                //可能性2
                res = res + process(str, index + 2);
            }
            return res;
        }

        //index的字符是2 那么有两种可能性
        // 1、index自己去转化，然后index + 1你自己去玩吧
        // 2、（index+1的字符不能小于0 且 超过6，因为组团的最小值是20，最大值是26 -> Z）index和index+1组团去转化，然后index+2你自己去玩吧
        if (str[index] == '2'){
            int res = process(str, index + 1);
            if (index + 1 < str.length && (str[index] >= '0' && str[index] <= '6')){
                res = res + process(str, index + 2);
            }
            return res;
        }

        //index的字符是 3 ... 9 就只有一种可能性 因为index只能自己玩  组团index+1超过了26
        return process(str, index + 1);
    }



}

