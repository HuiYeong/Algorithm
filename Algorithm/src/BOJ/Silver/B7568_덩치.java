package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B7568_덩치 {
	public static class bulk {
		int i, weight, height, grade;

		public bulk(int i, int weight, int height, int grade) {
			super();
			this.i = i;
			this.weight = weight;
			this.height = height;
			this.grade = grade;
		}

		@Override
		public String toString() {
			return weight+" "+height+" "+grade;
		}	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		List<bulk> list = new ArrayList<>();
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new bulk(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
		}
		
		for (int i=0;i<N;i++) {
			int k = 0;
			for (int j=0;j<N;j++) {
				if (i!=j && list.get(i).weight < list.get(j).weight && list.get(i).height<list.get(j).height) k+=1;
			}
			list.get(i).grade = k+1;
		}
		
		for (int i=0;i<N;i++) {
			sb.append(list.get(i).grade+" ");
		}
		System.out.println(sb);
	}
}
