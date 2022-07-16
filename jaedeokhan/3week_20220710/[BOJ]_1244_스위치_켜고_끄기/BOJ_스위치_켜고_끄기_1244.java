import java.io.*;
import java.util.*;

public class BOJ_스위치_켜고_끄기_1244 {

    static int[] map;

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 스위치 개수
        // 2. 각 스위치의 상태
        // 3. 학생 수
        // 4. 성별, 받은 수  - (남학생 1, 여학생 2), (받은 수)
        int studentNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < studentNum; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int maleOrFemale = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            // 남자
            if (1 == maleOrFemale){
                // 자신의 배수
                // 3이라면 2번 째 인덱스에서 부터 n번 까지
                for (int j = number - 1; j < n; j+=number){
                    changeSwitch(j);
                }
            }

            // 여자
            if (2 == maleOrFemale){
                // 좌우 대칭 범위를 가져온다.
                number = number - 1;
                changeSwitch(number);
                for (int j = 1; j < n / 2; j++){
                    int nextLeftIndex = number - j;
                    int nextRightIndex = number + j;

                    // 범위 밖인 경우
                    if (nextLeftIndex < 0 || nextRightIndex >= n){
                        break;
                    }

                    if (map[nextLeftIndex] == map[nextRightIndex]){
                        changeSwitch(nextLeftIndex);
                        changeSwitch(nextRightIndex);
                    } else {
                        // 대칭이 아닌경우
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++){
            bw.write(map[i-1] + " ");

            if (i % 20 == 0){
                bw.newLine();
            }

        }

        bw.flush();
        bw.close();
    }


    public static void changeSwitch(int index){
        if (map[index] == 0){
            map[index] = 1;
        }else if (map[index] == 1){
            map[index] = 0;
        }
    }
}