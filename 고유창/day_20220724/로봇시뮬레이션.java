package 고유창.day_20220724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
A: 열
B: 행
x: 열
y: 행
 */
public class 로봇시뮬레이션 {
    static int A, B, N, M;
    static int map[][];
    static Robot[] rb;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    static HashMap<String, Integer> hm;

    static class Robot {
        int r, c, dir;

        Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken()); //열
        B = Integer.parseInt(st.nextToken()); //행
        map = new int[B][A];
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 로봇개수
        M = Integer.parseInt(st.nextToken()); // 로봇 행동 개수
        rb = new Robot[N];

        //HashMap을 사용하여 초기 위치 설정
        hm = new HashMap();
        hm.put("N", 0);
        hm.put("E", 3);
        hm.put("S", 2);
        hm.put("W", 1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken()) - 1; // 로봇의 열 설정
            int r = B - Integer.parseInt(st.nextToken()); // 로봇의 행 설정(아래부터 0,1,...되므로 행의 최대크기인 B를 빼준다.)
            int dir = hm.get(st.nextToken());


            map[r][c] = (i+1);

            rb[i] = new Robot(r, c, dir);
        }



        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int rIdx = Integer.parseInt(st.nextToken())-1;
            String way = st.nextToken();
            int dist = Integer.parseInt(st.nextToken());
            moveRobot(rIdx, way, dist);
        }
        System.out.println("OK");
    }

    private static void moveRobot(int rIdx, String way, int dist) {
        Robot robot = rb[rIdx];

        if (way.equals("F")) {

            int r = robot.r;
            int c = robot.c;
            for (int i = 0; i < dist; i++) {
                int nr = r + dr[robot.dir];
                int nc = c + dc[robot.dir];

                if (nr >= 0 && nr < B && nc >= 0 && nc < A) {
                    if (map[nr][nc] > 0) {
                        System.out.println("Robot " + (rIdx+1) + " crashes into robot " + map[nr][nc]);
                        System.exit(0);
                    } else {
                        map[nr][nc] = map[r][c];
                        rb[rIdx] = new Robot(nr,nc,robot.dir);
                        map[r][c] = 0;
                        r = nr;
                        c = nc;
                    }
                } else {
                    System.out.println("Robot " + (rIdx+1) + " crashes into the wall");
                    System.exit(0);
                }
            }
        } else {

            int nDir = robot.dir;
            for (int i = 0; i < dist; i++) {
                nDir = way.equals("L") ? nDir + 1 : nDir - 1;
                if (nDir > 3)
                    nDir = 0;
                if (nDir < 0)
                    nDir = 3;

            }
            rb[rIdx] = new Robot(robot.r,robot.c, nDir);
        }

    }
}

