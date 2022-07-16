package baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* R : 한 칸 오른쪽으로 1,0
L : 한 칸 왼쪽으로 -1,0
B : 한 칸 아래로 0,1
T : 한 칸 위로 0,-1
RT : 오른쪽 위 대각선으로 1,-1
LT : 왼쪽 위 대각선으로 -1,-1
RB : 오른쪽 아래 대각선으로 1,1
LB : 왼쪽 아래 대각선으로 -1,1
* */
public class BJ_1063 {
    static int dx[] = {1,-1,0,0,1,-1,1,-1};
    static int dy[] = {0,0,1,-1,-1,-1,1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        String[] king = stk.nextToken().split("");  //킹의 위치 A1 -> 1
        String[] stone = stk.nextToken().split(""); //돌의 위치 A2 -> 2
        int N = Integer.parseInt(stk.nextToken());

        int kingX = king[0].charAt(0) - 65;
        int kingY = 8-Integer.parseInt(king[1]);
        int stoneX = stone[0].charAt(0) - 65;
        int stoneY = 8-Integer.parseInt(stone[1]);

        int cx = 0;
        int cy = 0;
        for(int i=0; i<N; i++){
            String move = br.readLine();
            switch(move){
                case "R":
                    cx = dx[0]; cy = dy[0];
                    break;
                case "L":
                    cx = dx[1]; cy = dy[1];
                    break;
                case "B":
                    cx = dx[2]; cy = dy[2];
                    break;
                case "T":
                    cx = dx[3]; cy = dy[3];
                    break;
                case "RT":
                    cx = dx[4]; cy = dy[4];
                    break;
                case "LT":
                    cx = dx[5]; cy = dy[5];
                    break;
                case "RB":
                    cx = dx[6]; cy = dy[6];
                    break;
                case "LB":
                    cx = dx[7]; cy = dy[7];
                    break;
            }

            //체스판 안에 있을 경우 !
            if(checkArrange(kingX+cx, kingY+cy)){
                //킹이 움직인 위치가 돌이 있는 위치일 경우
                if(kingX+cx == stoneX && kingY+cy == stoneY){
                    //돌이 움직일 위치도 체스판 안에 있는 경우
                    if(checkArrange(stoneX+cx, stoneY+cy)){
                        //킹을 돌의 위치로 옮기고 돌은 킹이 움직인 만큼 하나 더 움직임
                        kingX = stoneX;
                        kingY = stoneY;
                        stoneX += cx;
                        stoneY += cy;
                    }
                }else{
                    //킹의 위치 움직임
                    kingX += cx;
                    kingY += cy;
                }
            }
        }

        //A1, A2 형태로 바꾸기
        System.out.println((char)(kingX+65) +""+ (8-kingY));
        System.out.println((char)(stoneX+65) +""+ (8-stoneY));
    }

    public static boolean checkArrange(int x, int y){
        if(x>-1 && y>-1 && x<8 && y<8){
            return true;
        }else{
            return false;
        }
    }
}
