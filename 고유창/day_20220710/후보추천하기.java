package 고유창.day_20220710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 후보추천하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int total = Integer.parseInt(br.readLine());
        PriorityQueue<Person> pq = new PriorityQueue();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < total; i++) {
            int vote = Integer.parseInt(st.nextToken());

            if (pq.size() == N) {
                if (ifPersonExist(vote, pq)) {

                    getPerson(pq, vote, i);
                } else {

                    pq.poll();
                    addPerson(pq, vote, i);
                }
            } else {
                if (ifPersonExist(vote, pq)) {
                    getPerson(pq, vote, i);
                } else {
                    addPerson(pq, vote, i);
                }
            }
        }

        int[] ans = new int[pq.size()];
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            ans[i] = pq.poll().num;
        }

        Arrays.sort(ans);
        for (int i = 0; i < ans.length; i++)
            if (i != ans.length - 1)
                System.out.print(ans[i] + " ");
            else
                System.out.println(ans[i]);


    }

    private static boolean ifPersonExist(int vote, PriorityQueue<Person> pq) {
        int size = pq.size();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person p = pq.poll();
            if (p.num == vote) {
                pq.add(p);
                pq.addAll(list);
                return true;
            } else {
                list.add(p);
            }
        }
        pq.addAll(list);

        return false;
    }

    private static void addPerson(PriorityQueue<Person> pq, int vote, int i) {
        pq.add(new Person(vote, 0, i));
    }

    private static void getPerson(PriorityQueue<Person> pq, int vote, int date) {
        int size = pq.size();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person p = pq.poll();
            if (p.num == vote) {
                pq.add(new Person(p.num, p.nominated + 1, p.nominatedTime));
                pq.addAll(list);
                return;
            } else {
                list.add(p);
            }
        }
        pq.addAll(list);
    }

    static class Person implements Comparable<Person> {
        int num, nominated, nominatedTime;

        Person(int num, int nominated, int nominatedTime) {
            this.num = num;
            this.nominated = nominated;
            this.nominatedTime = nominatedTime;
        }

        @Override
        public int compareTo(Person person) {
            if (this.nominated == person.nominated) {
                return this.nominatedTime - person.nominatedTime;
            } else {
                return this.nominated - person.nominated;
            }
        }
    }
}
