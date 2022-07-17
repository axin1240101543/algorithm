package com.darren.center.algorithm2.class07;

import java.util.*;

/**
 * <h3>algorithm</h3>
 * <p></p>
 * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [ 3 , 3 , 1 , 2, 1, 2, 5…
 * op = [  T , T,  T,  T, F, T, F…
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品…﻿​
 *
 * 一对arr[i]和op[i]就代表一个事件：
 * 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 * op[i] == F就代表这个用户退货了一件商品
 *
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 * 都给购买次数最多的前K名用户颁奖。
 * 所以每个事件发生后，你都需要一个得奖名单（得奖区）。
 *
 * 得奖系统的规则：
 * 1，如果某个用户购买商品数为0，但是又发生了退货事件，
 *      则认为该事件无效，得奖名单和上一个事件发生后一致，例子中的5用户
 * 2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3，每次都是最多K个用户得奖，K也为传入的参数
 *       如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4，得奖系统分为得奖区和候选区，任何用户只要购买数>0，
 *       一定在这两个区域中的一个
 * 5，购买数最大的前K名用户进入得奖区，
 *       在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6，如果购买数不足以进入得奖区的用户，进入候选区
 * 7，如果候选区购买数最多的用户，已经足以进入得奖区，
 *      该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 *      如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 *      如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8，候选区和得奖区是两套时间，
 *      因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 *      从得奖区出来进入候选区的用户，得奖区时间删除，
 *      进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 *      从候选区出来进入得奖区的用户，候选区时间删除，
 *      进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，
 *      离开是指彻底离开，哪个区域也不会找到该用户
 *      如果下次该用户又发生购买行为，产生>0的购买数，
 *      会再次根据之前规则回到某个区域中，进入区域的时间重记
 * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 * public List<List<Integer>>  topK (int[] arr, boolean[] op, int k)
 *
 * @author : Darren
 * @date : 2022年07月16日 09:50:54
 **/
public class Code02_EveryStepShowBoss {

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK1(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            List<List<Integer>> ans3 = topK(arr, op, k);
            if (!sameAnswer(ans1, ans2) || !sameAnswer(ans2, ans3)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 优化版本
     * 时间复杂度：O（N * （logN + logK + K））
     * N：N个用户
     * logN：候选区复杂度
     * logK：得奖区复杂度
     * K：返回的的得奖区列表
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k){
        List<List<Integer>> ans = new ArrayList<>();
        EventHandler eventHandler = new EventHandler(k);
        for (int time = 0; time < arr.length; time++) {
            eventHandler.handleEvent(time, arr[time], op[time]);
            ans.add(eventHandler.getAnswers());
        }
        return ans;
    }

    private static class EventHandler{
        // 用户表
        Map<Integer, MyCustomer> userMap;
        // 得奖区
        HeapGreater<MyCustomer> winners;
        // 候选区
        HeapGreater<MyCustomer> candidate;
        // 得奖区最大值
        int k;

        public EventHandler(int k) {
            this.userMap = new HashMap<>();
            // 小根堆  数量少的放前面 如果数量一样 时间早的放前面
            this.winners = new HeapGreater<>(new MyDaddyComparator());
            // 大根堆  数量多的放前面 如果数量一样 时间早的放前面
            this.candidate = new HeapGreater<>(new MyCandidateComparator());
            this.k = k;
        }

        /**
         * 处理事件
         * @param time 事件事件
         * @param userId 用户Id
         * @param event 事件 true：买  false：卖
         */
        public void handleEvent(int time, int userId, boolean event){
            if (!event && !userMap.containsKey(userId)){
                return;
            }

            if (!userMap.containsKey(userId)){
                userMap.put(userId, new MyCustomer(userId, 0, 0));
            }

            // 修改当前用户的购买数
            MyCustomer myCustomer = userMap.get(userId);
            if (event){
                myCustomer.setAmount(myCustomer.getAmount() + 1);
            }else{
                myCustomer.setAmount(myCustomer.getAmount() - 1);
            }

            if (myCustomer.getAmount() == 0){
                userMap.remove(userId);
            }

            // 开始优化
            if (!winners.contains(myCustomer) && !candidate.contains(myCustomer)){
                // 如果两个区都不存在 那么是新用户
                myCustomer.setEventTime(time);
                if (winners.size() < k){
                    winners.push(myCustomer);
                }else{
                    candidate.push(myCustomer);
                }
            }else if (winners.contains(myCustomer)){
                // 在得奖区
                if (myCustomer.getAmount() == 0){
                    winners.remove(myCustomer);
                }else{
                    // 修改了购买数，调整堆
                    winners.resign(myCustomer);
                }
            }else{
                // 在候选区
                if (myCustomer.getAmount() == 0){
                    candidate.remove(myCustomer);
                }else{
                    // 修改了购买数，调整堆
                    candidate.resign(myCustomer);
                }
            }

            // 1 -> 0 或者 得奖区不满
            if (candidate.isEmpty()){
                return;
            }

            // 得奖区是满的，然后有个用户的购买数从1 -> 0，删除了，得奖区空了一个位置
            if (winners.size() < k){
                MyCustomer c = candidate.pop();
                c.setEventTime(time);
                winners.push(c);
            }else{
                // 如果候选区的第一名 能够干掉 得奖区最后一名 那么就交换（看排序规则）
                if (candidate.peek().getAmount() > winners.peek().getAmount()){
                    MyCustomer c = candidate.pop();
                    MyCustomer w = winners.pop();
                    c.setEventTime(time);
                    w.setEventTime(time);
                    candidate.push(w);
                    winners.push(c);
                }
            }
        }

        /**
         * 获得得奖名单
         * @return
         */
        public List<Integer> getAnswers(){
            List<Integer> ans = new ArrayList<>();
            List<MyCustomer> allElement = winners.getAllElement();
            for (MyCustomer myCustomer : allElement) {
                ans.add(myCustomer.getId());
            }
            return ans;
        }

    }

    /**
     * 不优化版本
     * @param arr 用户id
     * @param op 事件 T：购买 F：退货
     * @param k 得奖区的个数
     * @return 每个事件都会产生一个答案
     */
    public static List<List<Integer>> topK1(int[] arr, boolean[] op, int k){
        // 用户表
        Map<Integer, MyCustomer> userMap = new HashMap<>();
        // 得奖区
        List<MyCustomer> winners = new ArrayList<>();
        // 候选区
        List<MyCustomer> candidate = new ArrayList<>();
        // 答案（每个事件都产生一个答案）
        List<List<Integer>> ans = new ArrayList<>();

        // 遍历每个用户
        for (int i = 0; i < arr.length; i++) {
            int userId = arr[i];
            boolean event = op[i];

            // 规则1  无效事件：新用户且发生退货
            // 返回此时的得奖区
            if (!event && !userMap.containsKey(userId)){
                ans.add(doHandleAnswer(winners));
                continue;
            }

            // 新用户
            if (!userMap.containsKey(userId)){
                userMap.put(userId, new MyCustomer(userId, 0, 0));
            }

            // 规则2
            MyCustomer curUser = userMap.get(userId);
            if (event){
                curUser.setAmount(curUser.getAmount() + 1);
            }else{
                curUser.setAmount(curUser.getAmount() - 1);
            }

            // 新用户
            // 规则5
            if (!winners.contains(curUser) && !candidate.contains(curUser)){
                curUser.setEventTime(i);
                if (winners.size() < k){
                    winners.add(curUser);
                }else{
                    candidate.add(curUser);
                }
            }

            // 规则9
            if (curUser.getAmount() == 0){
                userMap.remove(userId);
            }
            // 清除购买数据为0的用户
            clearZeroUser(winners);
            clearZeroUser(candidate);

            // 规则7
            winners.sort((o1, o2) -> {
                // 得奖区 购买数最少的放前面 如一样 则时间早的放前面
               return o1.getAmount() != o2.getAmount() ? (o1.getAmount() - o2.getAmount()) : (o1.getEventTime() - o2.getEventTime());
            });
            candidate.sort((o1, o2) -> {
                // 候选区 购买数最多的放前面 如一样 则时间早的放前面
                return o1.getAmount() != o2.getAmount() ? (o2.getAmount() - o1.getAmount()) : (o1.getEventTime() - o2.getEventTime());
            });
//            winners.sort(new MyDaddyComparator());
//            candidate.sort(new MyCandidateComparator());

            // 规则8
            if (!candidate.isEmpty()){
                if (winners.size() < k){
                    // 得奖区是满的，然后有个用户的购买数从1 -> 0，删除了，得奖区空了一个位置
                    MyCustomer customer = candidate.get(0);
                    customer.setEventTime(i);
                    candidate.remove(0);
                    winners.add(customer);
                }else{
                    // 候选区的第0个和得奖区的第0个PK
                    if (candidate.get(0).getAmount() > winners.get(0).getAmount()){
                        MyCustomer c = candidate.get(0);
                        MyCustomer w = winners.get(0);
                        c.setEventTime(i);
                        w.setEventTime(i);
                        candidate.remove(0);
                        winners.remove(0);
                        candidate.add(w);
                        winners.add(c);
                    }
                }
            }
            ans.add(doHandleAnswer(winners));
        }
        return ans;
    }

    /**
     * 清除购买数据为0的用户
     * @param userData
     */
    private static void clearZeroUser(List<MyCustomer> userData) {
        Iterator<MyCustomer> iterator = userData.iterator();
        while (iterator.hasNext()){
            MyCustomer next = iterator.next();
            if (next.getAmount() == 0){
                iterator.remove();
            }
        }
    }

    /**
     * 处理答案
     * @param winners
     * @return
     */
    private static List<Integer> doHandleAnswer(List<MyCustomer> winners){
        List<Integer> ans = new ArrayList<>();
        winners.forEach(v -> ans.add(v.getId()));
        return ans;
    }

    public static class MyCustomer{
        /**
         * 用户id
         */
        private int id;
        /**
         * 购买数量
         */
        private int amount;
        /**
         * 事件时间
         */
        private int eventTime;

        public MyCustomer(int id, int amount, int eventTime) {
            this.id = id;
            this.amount = amount;
            this.eventTime = eventTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getEventTime() {
            return eventTime;
        }

        public void setEventTime(int eventTime) {
            this.eventTime = eventTime;
        }
    }

    public static class MyCandidateComparator implements Comparator<MyCustomer> {

        @Override
        public int compare(MyCustomer o1, MyCustomer o2) {
            return o1.getAmount() != o2.getAmount() ? (o2.getAmount() - o1.getAmount()) : (o1.getEventTime() - o2.getEventTime());
        }

    }

    public static class MyDaddyComparator implements Comparator<MyCustomer> {

        @Override
        public int compare(MyCustomer o1, MyCustomer o2) {
            return o1.getAmount() != o2.getAmount() ? (o1.getAmount() - o2.getAmount()) : (o1.getEventTime() - o2.getEventTime());
        }

    }

    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            // 规则1
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 规则2
            // 买、卖
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                map.remove(id);
            }

            // 规则5
            // c
            // 下面做 新用户
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            // 规则9
            // 移除所有的购买数为0的记录
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            // 规则7
            // 排序
            cands.sort(new CandidateComparator());
            daddy.sort(new DaddyComparator());
            // 规则8
            // 候选区的第0个和得奖区的第0个PK
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
        // 1 -> 0 或者 得奖区不满
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        // 得奖区是满的，然后有个用户的购买数从1 -> 0，删除了，得奖区空了一个位置
        if (daddy.size() < k) {
            Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        } else {
            // 规则7
            // 等奖区满了，候选区有东西
            if (cands.get(0).buy > daddy.get(0).buy) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int v, int b, int o) {
            id = v;
            buy = b;
            enterTime = 0;
        }
    }

    public static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }

    }

    public static class DaddyComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }

    }

    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}

