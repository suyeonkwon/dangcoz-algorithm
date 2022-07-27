package 고유창.day_20220718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long tree[] = new long[N];
        st = new StringTokenizer(br.readLine(), " ");
        long center = 0;
        for (int i = 0; i < N; i++) {
            tree[i] = Long.parseLong(st.nextToken());

        }

        Arrays.sort(tree);

        long start = 0;
        long end = tree[N - 1];
        long treeCut = 0;
        while (start < end) {
            center = (start + end) / 2;
            treeCut = 0;
            for (int i = 0; i < N; i++) {
                if (center - tree[i] < 0) {
                    treeCut += (tree[i] - center);

                }
            }

            if (treeCut > M) {
                start = center + 1;
            } else {
                end = center;
            }
        }
        System.out.println(start);
    }
}
