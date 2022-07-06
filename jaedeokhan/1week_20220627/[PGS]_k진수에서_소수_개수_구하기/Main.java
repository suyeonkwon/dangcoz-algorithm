// https://programmers.co.kr/learn/courses/30/lessons/92335

class PGS_K진수에서_소수_개수_구하기 {
        
    public int solution(int n, int k) {
        int answer = 0;
        
        // 양의 정수 n -> k진수로 변경     
        String kNotationNumber = getNumberOfKnotation(n, k);
        
        // 0P0, P0, 0P, P
        String[] primeArray = kNotationNumber.split("0+");
        
        // 소수 판별            
        for (String prime : primeArray){
            long primeNumber = Long.parseLong(prime);
            if (isPrime(primeNumber)){
                answer++;
            }
        }

        return answer;
    }
    
    private String getNumberOfKnotation(int number, int k){
        
        StringBuilder result = new StringBuilder();
        
        while (number > 0){
            result.append(number % k);
            number /= k;
        }
        
        return result.reverse().toString();
    }
    
    private boolean isPrime(long primeNumber){
        if(primeNumber <= 1) {
            return false;
        }
        // 소수의 제곱근으로 나눌 수 있는 최솟값을 구하기 
        int primeSqrt = (int) Math.sqrt(primeNumber);
        for (long i = 2; i <= primeSqrt; i++){
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }       
}
