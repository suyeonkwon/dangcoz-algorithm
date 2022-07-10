import java.util.ArrayList;
import java.util.Collections;

public class PGS_3차_파일명_정렬_Level2_리펙토링 {
    class KakaoFile implements Comparable<KakaoFile> {

        String fileFullName;
        String head;
        int number;

        KakaoFile(String fileFullName, String head, int number) {
            this.fileFullName = fileFullName;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(KakaoFile o) {
            // HEAD 대소문자 이외 같으면 NUMBER 숫자 순 정렬
            if (this.head.compareTo(o.head) == 0) {
                return this.number - o.number;
            }

            // HEAD 사전 순 정렬
            return this.head.compareTo(o.head);
        }

        public String getFileFullName() {
            return this.fileFullName;
        }
    }

    class Solution {
        public String[] solution(String[] files) {
            String[] answer = new String[files.length];

            String numberRegExp = "\\D+"; // 숫자만 가져올 정규표현식
            ArrayList<KakaoFile> kakaoFiles = new ArrayList<>();

            for (String file : files) {
                // HEAD는 숫자가 아닌 문자
                String[] numbers = file.split(numberRegExp);
                String strNumber = numbers[1]; // 첫 번째는 공백, 두 번째는 NUMBER
                String head = file.substring(0, file.indexOf(strNumber)); // Ex) foo9.xtx -> foo
                int intNumber = Integer.parseInt(strNumber); // "0012" -> 12
                String lowerHead = head.toLowerCase();

                // HEAD, NUMBER만으로 정렬을 구분하기에
                // TAIL 관련된 코드는 제거
                kakaoFiles.add(new KakaoFile(file, lowerHead, intNumber));
            }

            // HAED 사전 순 오름차순 정렬이면서 일치하면 NUMBER 오름차순 정렬
            Collections.sort(kakaoFiles);

            for (int i = 0; i < kakaoFiles.size(); i++) {
                answer[i] = kakaoFiles.get(i).getFileFullName();
            }

            return answer;
        }
    }
}