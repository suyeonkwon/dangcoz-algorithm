package day_20220627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 오목 {
    static int map[][];
    static int[] dr = {-1, 0, 1, 1};
    static int[] dc = {1, 1, 1, 0};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[19][19];
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        flag = false;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] != 0) {
                    omok(i, j, map[i][j]);
                }
            }
        }

        if (!flag)
            System.out.println(0);


    }

    private static void omok(int r, int c, int value) {
        for (int k = 0; k < 4; k++) {
            int cnt = 1;
            int nr = r + dr[k];
            int nc = c + dc[k];
            while (nr >= 0 && nr < 19 && nc >= 0 && nc < 19) {
                if (map[nr][nc] == value) {
                    cnt++;
                } else {
                    break;
                }
                nr += dr[k];
                nc += dc[k];
            }

            if (cnt == 5) {
                nr = r - dr[k];
                nc = c - dc[k];
                if (nr >= 0 && nr < 19 && nc >= 0 && nc < 19) {
                    if (map[nr][nc] == value) {
                        cnt++;
                    }
                }

                if (cnt == 6) {
                    continue;
                }

                flag = true;
                System.out.println(value);
                System.out.println(r + 1 + " " + (c + 1));
            }


        }
    }


}
