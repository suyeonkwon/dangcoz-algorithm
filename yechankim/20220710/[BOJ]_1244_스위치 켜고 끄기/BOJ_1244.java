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
            if (x == 1) { // ������ ��
                for (int j = 1; j < n+1; j++) {
                    if (j % number == 0) { // ���� ���� ����̸� ����ġ �ٲٱ�
                        arr[j] = arr[j] == 1 ? 0 : 1;
                    }
                }
            } else {
                int woman_n = Math.min(number, n-number+1);
                // ���� ���� �������� ��Ī���� ������ ���̹Ƿ� ���� �κ��� ã������
                // ����ġ�� 8����, ���� ���� 3�̶�� 1~2�� 4~5�� ���Ѵ�
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
