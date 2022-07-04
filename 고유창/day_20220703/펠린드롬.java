package 고유창.day_20220703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 펠린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String word = br.readLine();
            //다르면 오른쪽부터 인덱스 이동
            int st = 0;
            int ed = word.length() - 1;
            int diff = 0;
            while (st < ed) {
                if (word.charAt(st) != word.charAt(ed)) {
                    ed--;
                    diff++;
                } else {
                    st++;
                    ed--;
                }
            }
            if (diff > 1) {
                //다르면 왼쪽부터 인덱스 이동
                st = 0;
                ed = word.length() - 1;
                diff = 0;
                while (st < ed) {
                    if (word.charAt(st) != word.charAt(ed)) {
                        st++;
                        diff++;
                    } else {
                        st++;
                        ed--;
                    }
                }
                if (diff > 1) {
                    System.out.println(2);
                } else {
                    System.out.println(1);
                }
            } else if (diff == 1) {
                System.out.println(1);
            } else
                System.out.println(0);

        }
    }
}
