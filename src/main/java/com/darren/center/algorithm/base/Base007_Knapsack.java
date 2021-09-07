package com.darren.center.algorithm.base;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年09月02日 21:41:01
 * 从左往右的尝试模型2（背包问题）
 * 给定两个长度都为N的数组weights和values
 * weights[i]和values[i]分别代表i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少？
 **/
public class Base007_Knapsack {

    public static void main(String[] args) {
        int[] w = {1,5,6,4,8};
        int[] v = {10,30,80,50,30};
        int res = process(w, v, 0, 0, 10);
        System.out.println(res);

        int res1 = process2(w, v, 0, 10);
        System.out.println(res1);
    }

    //不变参数 重量列表 w  价值列表 v  背包空间 pag
    //index ... 最大价值
    //0 ... index - 1 位置已经货物做了选择 使得你的重量到达了alreadyW
    //如果返回-1  说明方案无效
    //如果不返回-1 说明返回的值是有效的
    public static int process(int[] w, int[] v, int index, int alreadyW, int pag){

        //超了 -1表示无效
        if (alreadyW > pag){
            return -1;
        }

        //没超
        if (index == w.length){
            return 0;
        }

        //不要index位置的货物
        int p1 = process(w, v, index + 1, alreadyW, pag);

        //要index位置的货物
        int nextP2 = process(w, v, index + 1, alreadyW + w[index], pag);
        int p2 = -1;
        if (nextP2 != -1){
            //如果要了index位置的货物，index + 1 的后续是有效的，那么index位置的价值加上 index + 1的后续价值
            p2 = v[index] + nextP2;
        }

        return Math.max(p1, p2);
    }

    //不变参数 重量列表 w  价值列表 v
    //index ... 最大价值
    //0 ... index - 1 位置已经货物做了选择 使得你的剩余空间是res
    //如果返回-1  说明方案无效
    //如果不返回-1 说明返回的值是有效的
    public static int process2(int[] w, int[] v, int index, int res){
        //如果你的剩余空间小于0 那么方案无效
        if (res < 0){
            return -1;
        }

        //res >= 0
        //如果一直选到最后 没有越界 那么返回0的价值
        if (index == w.length){
            return 0;
        }

        //选择不要index位置的货物
        int p1 = process2(w, v, index + 1, res);

        //选择要index位置的货物
        int nextP2 = process2(w, v, index + 1, res - w[index]);
        int p2 = -1;
        if (nextP2 != -1){
            //如果选择index位置的货物有效 那么加上index的价值加上index+1的后续价值
            p2 = v[index] + nextP2;
        }

        //不要和要 返回最大价值
        return Math.max(p1, p2);
    }

}

