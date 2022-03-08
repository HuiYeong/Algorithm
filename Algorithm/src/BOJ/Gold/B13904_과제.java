package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class B13904_과제 {
	static List<Homework> list;
	static class Homework implements Comparable<Homework>{
		int deadline, grade;

		public Homework(int deadline, int grade) {
			super();
			this.deadline = deadline;
			this.grade = grade;
		}

		@Override
		public String toString() {
			return deadline+" "+grade;
		}

		@Override
		public int compareTo(Homework o) {
			return Integer.compare(o.grade, this.grade);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new LinkedList<>();
		StringTokenizer st = null;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int[] ans = new int[1001];
		for (int i=0;i<N;i++) {
			for (int j=list.get(i).deadline;j>0;j--) {
				if (ans[j]==0) {
					ans[j] = list.get(i).grade;
					break;
				}
			}
		}
		int MAX = 0;
		for (int i=0;i<ans.length;i++) {
			MAX += ans[i];
		}
		System.out.println(MAX);
	}
}
