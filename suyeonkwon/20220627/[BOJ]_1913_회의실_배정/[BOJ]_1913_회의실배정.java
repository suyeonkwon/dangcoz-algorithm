package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BJ_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for(int i=0; i<N; i++){
            String[] str = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);
        }

        //회의 종료시간, 회의 시작시간으로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }else{
                    return o1[1] - o2[1];
                }

            }
        });

        int end = 0;
        int result = 0;
        for(int i=0; i<N; i++){
            //끝나는 시간보다 회의 시작시간이 빠르면 continue
            if(arr[i][0] < end) continue;

            //마지막 배열이면 result+1하고 멈춤
            if(i == N-1) {
                result ++;
                break;
            }

            end = arr[i][1];

            result++;
        }

        System.out.println(result);
    }
}
