package 고유창.day_20220710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기 {
    static int swich[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        swich = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            swich[i] = Integer.parseInt(st.nextToken());
        }

        int stuNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < stuNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int gender = Integer.parseInt(st.nextToken());
            int switchNum = Integer.parseInt(st.nextToken());

            if (gender == 2) {// 여자
                swich[switchNum - 1] = swich[switchNum - 1] > 0 ? 0 : 1;
                int left = switchNum - 2;
                int right = switchNum;
                while (left >= 0 && right < N) {
                    if (swich[left] == swich[right]) {
                        swich[left] = swich[left] > 0 ? 0 : 1;
                        swich[right] = swich[right] > 0 ? 0 : 1;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }

            } else {//남자
                for (int j = switchNum; j <= N; j += switchNum) {
                    swich[j - 1] = swich[j - 1] > 0 ? 0 : 1;
                }
            }

        }
        int cnt = 1;
        for (int i = 0; i < swich.length; i++) {
            if (i % 20 == 0 && i != 0) {
                System.out.println();
                System.out.print(swich[i] + " ");
            } else {
                System.out.print(swich[i] + " ");
            }
            cnt++;
        }

    }
}

