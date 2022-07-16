package baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class BJ_17609 {
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String str = br.readLine();

            arr = str.split("");

            int left = 0;
            int right = arr.length-1;

            if(palindrome(left, right)){
                System.out.println(0);
                continue;
            }

            if(pseudoPalindrome(left,right)){
                System.out.println(1);
                continue;
            }

            System.out.println(2);
        }
    }

    private static boolean pseudoPalindrome(int left, int right) {
        while(left<right){
            if(!arr[left].equals(arr[right])){
//                String tmp1 = str.substring(0,left) + str.substring(left+1);
//                String tmp2 = str.substring(0,right) + str.substring(right+1);

                //유사회문
                if(palindrome(left+1, right) || palindrome(left, right-1)){
                    return true;
                }else{
                    return false;
                }
            }

            left++;
            right--;
        }

        return false;
    }

    private static boolean palindrome(int left, int right) {
        while(left<right){
            if(!arr[left].equals(arr[right])){
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}
