import java.io.*;
import java.util.*;

public class BOJ_킹_1063 {

    static final int ALPHABET_ASCII_START = 65;
    static final int MAP_SIZE = 8;

    static int[][] map = new int[MAP_SIZE][MAP_SIZE];
    static int[] dRow = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dCol = {1, -1, 0, 0, 1, -1, 1, -1};
    static String[] directions = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};

    public static void main(String[] args) throws IOException {

        // A ~ H는 열
        // 8 ~ 1은 행

        // 조건
        // - 돌과 같은 곳 이동 시에는 돌을 킹이 움직인 방향과 같은 방향으로 한 칸 이동
        // - 킹이나 돌이 체스판 밖으로 나갈 경우 그 이동으 건너 뛰고 다음 이동

        // 출력
        // - 1. 킹과 2. 돌의 마지막 위치 구하기

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String kingPosition = st.nextToken();
        String rockPosition = st.nextToken();
        int n = Integer.parseInt(st.nextToken());
        String[] inputDirections = new String[n];
        for (int i = 0; i < n; i++){
            inputDirections[i] = br.readLine();
        }

        // 풀이
        // 행은 1이면 8행이기에 8 - 1 => 7행으로 하기 위해 8을 빼준다.
        int kingCol = kingPosition.charAt(0) - ALPHABET_ASCII_START;
        int kingRow = MAP_SIZE - Integer.parseInt(kingPosition.substring(1, 2));
        int rockCol = rockPosition.charAt(0) - ALPHABET_ASCII_START;
        int rockRow = MAP_SIZE - Integer.parseInt(rockPosition.substring(1, 2));

        map[kingRow][kingCol] = 1; // 킹 위치 세팅
        map[rockRow][rockCol] = 2; // 돌 위치 세팅

        for (String inputDirection : inputDirections){
            int currentDirection = Arrays.asList(directions).indexOf(inputDirection);

            int nextKingRow = kingRow + dRow[currentDirection];
            int nextKingCol = kingCol + dCol[currentDirection];

            if (isOverFlow(nextKingRow, nextKingCol)){
                continue;
            }

            if (map[nextKingRow][nextKingCol] == 2){
                int nextRockRow = rockRow + dRow[currentDirection];
                int nextRockCol = rockCol + dCol[currentDirection];

                if (isOverFlow(nextRockRow, nextRockCol)){
                    continue;
                }

                // 돌 위치가 밖으로 넘치지 않았기에 업데이트
                map[rockRow][rockCol] = 0;
                map[nextRockRow][nextRockCol] = 2;
                rockRow = nextRockRow;
                rockCol = nextRockCol;
            }

            // 킹 위치도 밖으로 넘치지 않았기에 업데이트
            map[kingRow][kingCol] = 0;
            map[nextKingRow][nextKingCol] = 1;
            kingRow = nextKingRow;
            kingCol = nextKingCol;
        }

        // KingCol : A ~ Z, KingRow : 8 ~ 1
        // COL -> A : 0 + 48 => A
        // ROW -> 8 - 0 = 8
        //        8 - 1 = 7
        bw.write(String.valueOf((char) (kingCol + ALPHABET_ASCII_START) + "" + (MAP_SIZE - kingRow)));
        bw.newLine();
        bw.write(String.valueOf((char) (rockCol + ALPHABET_ASCII_START) + ""+ (MAP_SIZE - rockRow)));
        bw.flush();
        bw.close();
    }

    public static boolean isOverFlow(int rowIndex, int colIndex){
        if (rowIndex >= MAP_SIZE  || rowIndex < 0 || colIndex >= MAP_SIZE || colIndex < 0){
            return true;
        }
        return false;
    }
}