package 고유창.day_20220627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 회의실배정 {
    static int N;

    static class Time implements Comparable<Time> {
        int start, end, depth;

        Time(int start, int end, int depth) {
            this.start = start;
            this.end = end;
            this.depth = depth;
        }

        @Override
        public int compareTo(Time o) {
            if (o.end == this.end)
                return this.start - o.start;
            else
                return this.end - o.end;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "start=" + start +
                    ", end=" + end +
                    ", depth=" + depth +
                    '}';
        }
    }

    static PriorityQueue<Time> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Time(start, end, 0));
        }
        int endTime = 0;

        int cnt = 0;
        Time t = pq.poll();
        endTime = t.end;
        cnt++;


        while (!pq.isEmpty()) {
            Time time = pq.poll();
            if (time.start >= endTime) {
                endTime = time.end;
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
