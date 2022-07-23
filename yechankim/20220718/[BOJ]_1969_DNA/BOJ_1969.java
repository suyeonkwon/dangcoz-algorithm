import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1969 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int i, j, n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int max;
        int hd = 0;
        String[] dna = new String[n];

        for (i = 0; i < n; i++) {
            dna[i] = br.readLine();
        }

        for (i = 0; i < m; i++) {
            int a = 0, t = 0, g = 0, c = 0;

            for (j = 0; j < n; j++) {
                switch (dna[j].charAt(i)) {
                    case 'A' -> a++;
                    case 'T' -> t++;
                    case 'G' -> g++;
                    case 'C' -> c++;
                }
            }
            max = Math.max(Math.max(a, c), Math.max(g, t));
            hd += (n - max);
            System.out.print(intToChar(a, t, g, c, max));
        }
        System.out.println("\n" + hd);
    }
    private static char intToChar(int a, int t, int g, int c, int max) {
        if (max == a) return 'A';
        else if (max == c) return 'C';
        else if (max == g) return 'G';
        else return 'T';
    }
}
