import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 사진틀 개수
        int picNum = Integer.parseInt(br.readLine());
        int[] stu = new int[picNum]; // 학생
        int[] rec = new int[picNum]; // 추천
        int[] time = new int[picNum]; // 시간

        // 2. 전체 학생 총 추천 횟수
        int total = Integer.parseInt(br.readLine());

        // 3. 추천받은 학생 번호
        StringTokenizer st = new StringTokenizer(br.readLine());

        int now = 0;
        int idx = 0;

        for (int i = 0; i < total; i++) {
            now = Integer.parseInt(st.nextToken());
            idx = 0;

            for (int j = 0; j < picNum; j++) {
                // 사진틀이 비어 있거나 이미 사진이 게시된 경우
                if (stu[j] == 0 || stu[j] == now) {
                    idx = j;
                    break;
                }
                // 추천 횟수가 적거나 추천 횟수는 같은데 게시된 시간이 더 빠른 경우
                if (rec[idx] > rec[j] || (rec[idx] == rec[j] && time[idx] > time[j])) {
                    idx = j;
                }
            }
            
            // 사진 갈아끼우기
            if (stu[idx] != now) {
                stu[idx] = now;
                rec[idx] = 0;
                time[idx] = i;
            }
            rec[idx]++;
        }

        Arrays.sort(stu);

        for (int x : stu) {
            if (x != 0) bw.write(String.valueOf(x) + " ");
        }
        bw.flush();
    }
}
