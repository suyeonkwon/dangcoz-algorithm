import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1063 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 킹 위치 , 돌 위치, 움직이는 횟수
        char[] king = st.nextToken().toCharArray();
        char[] stone = st.nextToken().toCharArray();
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            // 움직이기
           String order = br.readLine();
           
            // 킹 좌표, 스톤 좌표
            char[] king_po = king.clone();
            char[] stone_po = stone.clone();
            
            // 킹 이동
            move(order, king_po);
            
            // 킹 범위 체크
            if (king_po[0]<'A' || king_po[0]>'H' || king_po[1]<'1' || king_po[1]>'8')
                continue;
            
            // 킹, 스톤 위치가 같으면
            if(Arrays.equals(king_po,stone_po)){
                
                // 스톤 이동
                move(order, stone_po);
                
                // 스톤 범위 체크
                if (stone_po[0]<'A' || stone_po[0]>'H' || stone_po[1]<'1' || stone_po[1]>'8')
                    continue;
                
            }
            
            king = king_po;
            stone = stone_po;
        }

        System.out.println(king);
        System.out.println(stone);
    }

    public static void move(String pos, char[] next){
        switch (pos){
            case "R": next[0]++; break;
            case "L": next[0]--; break;
            case "B": next[1]--; break;
            case "T": next[1]++; break;
            case "RT": next[0]++; next[1]++; break;
            case "LT": next[0]--; next[1]++; break;
            case "RB": next[0]++; next[1]--; break;
            case "LB": next[0]--; next[1]--; break;
        }
    }
}