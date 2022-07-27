package 고유창.day_20220724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈{
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    static int N, M;
    static int map[][];
    static boolean v[][];
    static int totalCheeze;
    static boolean outside[][]; // 외부공기랑 닿는 부분 체크하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        totalCheeze = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    totalCheeze++;
            }
        }

        int cnt = 1;
        while (true) {
            v = new boolean[N][M];
            // 1. 외부공기랑 연결된 공간 체크
            chkOutside();
            v = new boolean[N][M];
            // 2. 치즈중 2면이상 외부랑 연결된공간 체크후 삭제
            setDelCheeze();
            // 3. 남아 있는 치즈 유무 확인
            if(totalCheeze==0)
                break;
            cnt++;
        }

        System.out.println(cnt);

    }

    private static void setDelCheeze() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    cheezeBFS(i, j);
                }
            }
        }
    }

    private static void cheezeBFS(int r, int c) {
        Queue<Point> que = new LinkedList<Point>();
        v[r][c] = true;
        que.add(new Point(r, c));
        while (!que.isEmpty()) {
            Point p = que.poll();
            int cnt = 0;
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (outside[nr][nc]) {
                        cnt++;
                    } else {
                        if (!v[nr][nc] && map[nr][nc] == 1) {
                            v[nr][nc] = true;
                            que.add(new Point(nr, nc));
                        }
                    }
                }
            }
            if (cnt > 1) {
                map[p.r][p.c] = 0;
                totalCheeze--;
            }
        }
    }

    private static void chkOutside() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !v[i][j]) {
                    outsideBFS(i, j);
                }
            }
        }
    }

    private static void outsideBFS(int r, int c) {
        Queue<Point> que = new LinkedList<Point>();
        que.add(new Point(r, c));
        v[r][c] = true;
        boolean flag = false;
        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (!v[nr][nc] && map[nr][nc] == 0) {
                        v[nr][nc] = true;
                        que.add(new Point(nr, nc));
                    }
                } else {
                    flag = true;
                }
            }
        }
        if (flag) {
            setOutSide();
        }
    }

    private static void setOutSide() {
        outside = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j]) {
                    outside[i][j] = true;// 외부공간 체크
                }
            }
        }
    }
}
