import java.io.*;

public class BOJ_회문_17609 {
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 풀이 시작
        for (int i = 0; i < n; i++){
            String word = br.readLine();

            // 회문 : 0, 유사 회문 : 1,  일반 문자열 : 2
            int answer = getValidPalindrome(word);

            // 회문은 0이 넘어온다.
            // 유사회문은 초기에 answer = 1과 왼쪽, 오른쪽 둘 중에 하나만 다르기에 2가 넘어온다.
            // 일반 문자열은 초기에 answer = 1과 왼쪽, 오른쪽 둘 다 다르기에 3이 넘어온다.
            if (answer >= 2) {
                answer = answer - 1;
            }

            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    public static int getValidPalindrome(String word){

        int answer = 0;
        int wordLength = word.length();
        int leftIndex = 0, rightIndex = wordLength - 1;

        while (leftIndex <= rightIndex){
            char leftValue = word.charAt(leftIndex);
            char rightValue = word.charAt(rightIndex);

            if (leftValue == rightValue){
                leftIndex++;
                rightIndex--;
            }

            // 회문이 아닌경우
            // 유사회문인지, 일반 문자열인지 검사
            if (leftValue != rightValue){
                // 해당 부분을 하지 않으면 aabcbcaa 경우 유사회문이지만 0가 리턴
                // 최소 한 번 다른것을 체크해주고
                // 유사회문은 최소 한 번 + 왼쪽,오른쪽 중 하나만 다르므로 -> 2
                // 일반 문자열은 최소 한 번 + 왼쪽, 오른쪽 둘 다 다르므로 -> 3
                answer = 1;
                int lIndex = leftIndex, rIndex = rightIndex;
                // leftIndex를 밀어보면서 왼쪽부터 증감해서 확인
                lIndex++;

                while (lIndex <= rIndex){
                    char lValue = word.charAt(lIndex);
                    char rValue = word.charAt(rIndex);

                    if (lValue == rValue){
                        lIndex++;
                        rIndex--;
                    }

                    // 다르기에 answer 증감
                    if (lValue != rValue){
                        answer++;
                        break;
                    }
                }

                lIndex = leftIndex;
                rIndex = rightIndex;
                // rightIndex를 밀어보면서 오른쪽 가감해서 확인
                rIndex--;

                while (lIndex <= rIndex){
                    char lValue = word.charAt(lIndex);
                    char rValue = word.charAt(rIndex);

                    if (lValue == rValue){
                        lIndex++;
                        rIndex--;
                    }

                    // 오른쪽을 줄여봐도 다르다면 answer 증감
                    if (lValue != rValue){
                        answer++;
                        break;
                    }
                }

                // 유사회문(2), 일반 문자열(3) 리턴
                return answer;
            }
        }

        // 회문인경우(0) 리턴
        return answer;
    }
}