package programmers;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PGS_17686 {
    static List<File> list =  new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        solution(files);
    }

    private static void solution(String[] files) {
        for(int i=0; i<files.length; i++){
            String file = files[i];

            boolean isNumber = false;
            String head = "";
            String number = "";

            for(int j=0; j<file.length(); j++){
                char c = file.charAt(j);
                if (c >= '0' && c <= '9') {
                    number += c;
                    isNumber = true;
                } else {
                    if (!isNumber) head += c;
                }
            }
            System.out.println(head);
            System.out.println(number);
            list.add(new File(head, number, file, i));
        }

        Collections.sort(list);

        for(File f : list){
            System.out.println(f.orgFileName);
        }
    }
    static class File implements Comparable<File> {
        String head;
        String number;
        String orgFileName;
        int index;

        public File(String head, String number, String orgFileName, int index) {
            this.head = head;
            this.number = number;
            this.orgFileName = orgFileName;
            this.index = index;
        }

        @Override
        public int compareTo(File f) {
            if (this.head.equalsIgnoreCase(f.head)) {
                if (Integer.parseInt(this.number) == Integer.parseInt(f.number)) {
                    return this.index - f.index;
                }
                return Integer.parseInt(this.number) - Integer.parseInt(f.number);
            }
            return this.head.toLowerCase().compareTo(f.head.toLowerCase());
        }
    }
}
