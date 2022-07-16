import java.util.*;

public class PGS_3차_파일명_정렬_Level2 {
    class KakaoFile implements Comparable<KakaoFile>{

        String fileFullName;
        String head;
        int number;
        String tail;

        KakaoFile (String fileFullName, String head, int number, String tail){
            this.fileFullName = fileFullName;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        KakaoFile (String fileFullName, String head, int number){
            this.fileFullName = fileFullName;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(KakaoFile o){
            int res = this.head.compareTo(o.head);

            // HEAD 대소문자 이외 같으면 NUMBER 숫자 순 정렬
            if (res == 0){
                res = this.number - o.number;
            }

            // HEAD 사전 순 정렬
            return res;
        }

        @Override
        public String toString() {
            return "fullPath : " + fileFullName;
        }
    }

    class Solution {
        public String[] solution(String[] files) {
            String[] answer = new String[files.length];

            String numberRegExp = "\\D+"; // 숫자만 가져올 정규표현식
            ArrayList<KakaoFile> kakaoFiles = new ArrayList<>();

            for (String file : files){
                // HEAD는 숫자가 아닌 문자
                String[] numbers = file.split(numberRegExp);
                String number = numbers[1]; // 첫 번째는 공백, 두 번째는 NUMBER
                String head = file.substring(0,  file.indexOf(number));
                int dotIndex = file.indexOf(".");
                String tail = "";
                int orderNumber = Integer.parseInt(number);
                String lowerHead = head.toLowerCase();

                // TAIL이 존재하는지 체크
                if (dotIndex > -1){
                    tail = file.substring(dotIndex);
                    kakaoFiles.add(new KakaoFile(file, lowerHead, orderNumber, tail));
                } else {
                    kakaoFiles.add(new KakaoFile(file, lowerHead, orderNumber));
                }
            }

            // TAIL 사전순 오름차순이면서 동일하면 NUMBER 오름차순
            Collections.sort(kakaoFiles);

            for (int i = 0; i < kakaoFiles.size(); i++){
                answer[i] = kakaoFiles.get(i).fileFullName;
            }

            return answer;
        }
    }
}
