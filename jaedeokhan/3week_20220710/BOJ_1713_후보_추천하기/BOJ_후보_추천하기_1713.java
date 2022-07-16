package BOJ_1713_후보_추천하기;

import java.io.*;
import java.util.*;

public class BOJ_후보_추천하기_1713 {

    static int[] studentLike = new int[101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 사진틀의 개수
        int totalLikeCount = Integer.parseInt(br.readLine()); // 총 추천 횟수
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 추천받은 학생들 배열
        ArrayList<Integer> pictureFrame = new ArrayList<>();

        for (int i = 0; i < totalLikeCount; i++){
            int studentNum = Integer.parseInt(st.nextToken());

            // 학생이 존재하면 추천수만 증가하고 continue
            if (pictureFrame.indexOf(studentNum) > -1){
                studentLike[studentNum] += 1;
                continue;
            }

            // 사진틀에 학생 사진을 넣을 수 있으면
            if (pictureFrame.size() <  n){
                pictureFrame.add(studentNum);
                studentLike[studentNum] += 1;
            } else { // 비어있는 사진틀이 없는 경우
                // 현재까지 가장 추천 받은 횟수가 가장 적은 학생 두 명 이상인 경우 - 가장 오래된 사진 삭제
                int minLike = Integer.MAX_VALUE;
                // 가장 작은 추천 수 가져온다.
                for (int j = 0; j < pictureFrame.size(); j++){
                    int currentStudentLikeCount = studentLike[pictureFrame.get(j)];
                    if (minLike > currentStudentLikeCount){
                       minLike =  currentStudentLikeCount;
                    }
                }

                // 가장 게시된 지 오래된 사진 삭제
                for (int j = 0; j< pictureFrame.size(); j++){
                    // 리스트에 앞에 있는 것이 오래된 사진이기에 최솟값이 가장 앞에 있는 것 제거
                    int oldest = pictureFrame.get(j);
                    if (minLike == studentLike[oldest]){
                        pictureFrame.remove(j);
                        studentLike[oldest] = 0;
                        break;
                    }
                }

                pictureFrame.add(studentNum);
                studentLike[studentNum] += 1;
            }
        }

        Collections.sort(pictureFrame);

        for (int i = 0; i < pictureFrame.size(); i++){
            bw.write(pictureFrame.get(i) + " ");
        }

        bw.flush();
        bw.close();

    }
}
