import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            if (x == 1) { // 남자일 때
                for (int j = 1; j < n+1; j++) {
                    if (j % number == 0) { // 받은 수의 배수이면 스위치 바꾸기
                        arr[j] = arr[j] == 1 ? 0 : 1;
                    }
                }
            } else {
                int woman_n = Math.min(number, n-number+1);
                // 받은 수를 기준으로 대칭으로 비교해줄 것이므로 비교할 부분을 찾기위해
                // 스위치가 8개고, 받은 수가 3이라면 1~2와 4~5만 비교한다
                arr[number] = arr[number] == 1 ? 0 : 1;
                for (int j = 1; j < woman_n; j++) {
                    if (arr[number + j] == arr[number - j]) {
                        arr[number + j] = arr[number + j] == 1 ? 0 : 1;
                        arr[number - j] = arr[number - j] == 1 ? 0 : 1;
                    }
                    else break;
                }
            }
        }
        for (int i = 1; i < n+1; i++) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0) System.out.println();
        }
    }
}
