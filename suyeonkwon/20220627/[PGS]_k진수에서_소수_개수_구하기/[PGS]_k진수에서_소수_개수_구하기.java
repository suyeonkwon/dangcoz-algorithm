package programmers;
/*
* k진수에서 소수 개수 구하기
* */
public class PGS_92335 {
    class Solution {
        public int solution(int n, int k) {
            int answer = 0;
            String num = kNum(n,k);
            //k진수로 바꾼 문자를 0을 기준으로 자름
            String[] nums = num.split("0");

            for(int i=0; i<nums.length; i++){
                if(!nums[i].equals("")){
                    if(isPrime(nums[i].trim())){    //앞뒤 공백 제거
                        answer++;
                    }
                }
            }
            return answer;
        }

        public boolean isPrime(String num){
            //int를 사용할 경우 int overflow가 발생하는것에 주의!
            Long n = Long.parseLong(num);

            if(n <= 1) return false;
            else if(n == 2) return true;
            for(int i = 2; i <= Math.sqrt(n); i++)
                if(n % i == 0)
                    return false;
            return true;
        }

        public String kNum(int n, int k) {
            String res = "";
            while(n > 0) {
                res = n % k + res;
                n /= k;
            }
            return res;
        }
    }
}
