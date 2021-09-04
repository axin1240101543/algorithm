package com.darren.center.algorithm.base;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:41:01
 * 范围上尝试的模型
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 **/
public class Base008_CardsInLine {

    public static void main(String[] args) {
        int[] arr = {4,8,2};
        int res = Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
        System.out.println(res);
    }

    //先手函数
    public static int f(int[] arr, int l, int r){
        //只有一张牌了 先手就直接拿走
        if (l == r){
            return arr[l];
        }
        //选择左边的牌 + 后手的最好情况
        int p1 = arr[l] + s(arr, l + 1, r);
        //选择右边的牌 + 后手的最好情况
        int p2 = arr[r] + s(arr, l, r - 1);
        //选择最好的
        return Math.max(p1, p2);
    }

    //后手函数
    public static int s(int[] arr, int l, int r){
        //只有一张牌了 后手没的选
        if (l == r){
            return 0;
        }
        int p1 = f(arr, l + 1, r);
        int p2 = f(arr, l, r - 1);
        //选择最差的
        return Math.min(p1, p2);
    }
}

