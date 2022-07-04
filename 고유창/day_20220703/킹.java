package 고유창.day_20220703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 킹 {
    static int dr[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int dc[] = {0, 0, -1, 1, -1, 1, -1, 1};
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new int[8][8];
        String king[] = st.nextToken().split("");
        String rock[] = st.nextToken().split("");
        int N = Integer.parseInt(st.nextToken());

        int kc = ((king[0].charAt(0)) - 65);
        int kr = 8 - Integer.parseInt(king[1]);

        int rc = ((rock[0].charAt(0)) - 65);
        int rr = 8 - Integer.parseInt(rock[1]);


        map[kr][kc] = 1;//king place
        map[rr][rc] = 2;//rock place


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String dir = st.nextToken();
            int way = selectWay(dir);
            int nr = kr + dr[way];
            int nc = kc + dc[way];
            if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8) {
                if (map[nr][nc] == 2) {//if king is moved to where rock is,
                    int nrr = rr + dr[way];
                    int nrc = rc + dc[way];

                    if (nrr >= 0 && nrr < 8 && nrc >= 0 && nrc < 8) {
                        map[rr][rc] = 0;
                        map[nrr][nrc] = 2;
                        rr = nrr;
                        rc = nrc;
                    } else
                        continue;
                }
                map[kr][kc] = 0;
                map[nr][nc] = 1;
                kr = nr;
                kc = nc;
            }


        }

        int ansKr = kr;
        int ansKc = kc;
        int ansRr = rr;
        int ansRc = rc;

        System.out.println((char) ('A' + ansKc) + "" + (8 - ansKr));
        System.out.println((char) ('A' + ansRc) + "" + (8 - ansRr));
    }


    private static int selectWay(String dir) {
        if (dir.equals("R")) {
            return 3;
        } else if (dir.equals("L")) {
            return 2;
        } else if (dir.equals("B")) {
            return 1;
        } else if (dir.equals("T")) {
            return 0;
        } else if (dir.equals("RT")) {
            return 5;
        } else if (dir.equals("LT")) {
            return 4;
        } else if (dir.equals("RB")) {
            return 7;
        } else {
            return 6;
        }
    }
}
