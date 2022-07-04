package baekjoon.bruteForce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2615_4 {
    //오른쪽 대각선 아래, 아래, 오른쪽 대각선 위, 오른쪽
    static int[] dx = {1,0,-1,1};
    static int[] dy = {1,1,1,0};
    static int[][] concave;
    static boolean[][] visit;
    static boolean win;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        concave = new int[19][19];
        visit = new boolean[19][19];

        //오목판 만들기
        for(int i=0; i<19; i++){
            String[] str = br.readLine().split(" ");
            for(int j=0; j<19; j++) {
                concave[i][j] = Integer.parseInt(str[j]);
            }
        }
        
        for(int i=0; i<19; i++){
            for(int j=0; j<19; j++){
                int prev = -1;
                if(concave[i][j] != 0){ //흑, 백인지 확인
                    prev = concave[i][j];
                    search(i,j,prev);
                }
            }
        }
        //승부 결정되지 않았으면
        if(!win){
            System.out.println(0);
        }
    }

    private static void search(int x, int y, int prev) {
        for(int i=0; i<4; i++){
            int cnt = 1;
            int cx = dx[i];
            int cy = dy[i];
            if(x+cx >= 19 || y+cy >= 19 || x+cx < 0 || y+cy < 0) continue;
            while(concave[x+cx][y+cy] == prev) {
                cnt++;
                cx += dx[i];
                cy += dy[i];
                if(cnt > 5) break;
                if(x+cx >= 19 || y+cy >= 19 || x+cx < 0 || y+cy < 0) break;
            }
            //다 하고 돌아왔는데 반대로 돌아왔으면 6오목
            if(x-dx[i] >= 0 && y-dy[i] >= 0 && x-dx[i] < 19 && y-dy[i] < 19){
                if(concave[x-dx[i]][y-dy[i]] == prev){
                    continue;
                }
            }
            if(cnt == 5){
                System.out.println(prev);
                System.out.println((x+1) + " " + (y+1));
                win = true;
                return;
            }
        }
    }
}
