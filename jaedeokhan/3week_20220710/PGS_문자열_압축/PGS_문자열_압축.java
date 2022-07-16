package PGS_문자열_압축;

public class PGS_문자열_압축 {
    class Solution {
        public int solution(String s) {
            int sLength = s.length();
            int answer = Integer.MAX_VALUE;

            // 한 단어이면 압축 못하기에 1
            if (sLength == 1){
                return 1;
            }

            for (int startIndex = 1; startIndex < sLength / 2 + 1; startIndex++){

                String compareString = "";
                int equalCount = 1;
                String prevValue = s.substring(0, startIndex);

                for (int step = startIndex; step < sLength; step += startIndex){

                    int nextStep = step + startIndex;

                    if (nextStep >= sLength){
                        nextStep = sLength;
                    }

                    String currentValue = s.substring(step, nextStep);

                    if (prevValue.equals(currentValue)){
                        equalCount++;
                        continue;
                    }

                    // prevValue != currentValue 다른 경우
                    if (equalCount >= 2){
                        compareString += equalCount + prevValue;
                    } else {
                        compareString += prevValue;
                    }

                    prevValue = currentValue;
                    equalCount = 1;
                }

                // 남아있는 문자열 처리
                if (equalCount >= 2){
                    compareString += equalCount + prevValue;
                } else {
                    compareString += prevValue;
                }

                answer = Math.min(answer, compareString.length());
            }

            return answer;
        }
    }
}
