import java.io.*;
import java.util.*;

class Meeting implements Comparable<Meeting>{
    public int startTime;
    public int endTime;

    public Meeting(){
    }

    public Meeting(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting o){
	// 종료시간 오름차순
        if (this.endTime == o.endTime) {
            // 종료시간이 동일하면 종료시간 기준으로 시작시간 오름차순
            return this.startTime - o.startTime;
        } else {
            return this.endTime - o.endTime;
        }
    }
}

public class BOJ_1913_회의실_배정 {
    public static void main(String[] args) throws IOException {

        // 문제
        // - 각 회의가 겹치치 않으면서 회의실을 사용할 수 있는 최대 개수
        // 조건
        // - 회의는 시작하면 중간에 중단 X
        // - 회의가 끝나면 다음 회의 시작
        // - 시작시간과 종료 시간이 같을수도 O(시작하자마자 종료)
        // 입력
        // - 1 : 회의의 수(1 <= n <= 100,000)
        // - 2 ~ N : 각 회의 정보(시작 시간, 끝나는 시간)
        // 출력
        // - 최대 사용할 수 있는 회의의 최대 개수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int answer = 0;

        // 입력
        int n = Integer.parseInt(br.readLine());
        ArrayList<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            meetings.add(new Meeting(startTime, endTime));
        }

        // 시작
        Collections.sort(meetings);

        int currentEndTime = Integer.MIN_VALUE;

        for (Meeting meeting : meetings){
            int startTime = meeting.startTime;
            int endTime = meeting.endTime;

            if (startTime >= currentEndTime){
                currentEndTime = endTime;
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
