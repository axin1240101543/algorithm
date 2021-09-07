package com.darren.center.algorithm.code;

/**
 * <h3>algorithm</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2021年08月27日 19:56:03
 **/
public class Problem_048_RotateImage_Ii {

    public static void main(String[] args) {
        rotate(20);
    }

    public static void rotate(int n) {
        int a = 0;
        int b = n - 1;
        char[][] m = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = ' ';
            }
        }

        while (a <= b){
            set(m, a, b);
            a = a + 2;
            b = b - 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void set(char[][] m, int a, int b) {
        //0,a -> 0,b
        for (int i = a; i <= b; i++){
            m[a][i] = '*';
        }

        //a+1,b -> b,b
        for (int i = a + 1; i <=b ; i++) {
            m[i][b] = '*';
        }

        //b,b-1 -> b,a-1
        for (int i = b - 1; i > a; i--) {
            m[b][i] = '*';
        }

        //b-1,a+1 -> a+1,a+1
        for (int i = b - 1; i > a + 1; i--) {
            m[i][a+1] = '*';
        }
    }

}

