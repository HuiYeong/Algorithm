package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B1205_등수구하기 {
	static class rank {
		long grade;
		int ranking;

		public rank(long grade, int ranking) {
			super();
			this.grade = grade;
			this.ranking = ranking;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Integer.parseInt(st.nextToken());
		long New = Integer.parseInt(st.nextToken());
		long P = Integer.parseInt(st.nextToken());
		List<rank> list = new ArrayList<>();
		if (N==0) System.out.println(1);
		else if (N>0){
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				long g = Long.parseLong(st.nextToken());
				if (i!=0 && g==list.get(i-1).grade) list.add(new rank(g, list.get(i-1).ranking));
				else list.add(new rank(g, i+1));
			}
			if(list.get(list.size()-1).grade>=New && list.size()==P) System.out.println(-1); 
			else {
				for(int i=0;i<list.size();i++) {
					if (list.get(i).grade<=New) {
						System.out.println(list.get(i).ranking);
						return;
					}
					if (i==list.size()-1) System.out.println(list.size()+1);
				}
			}
		}
	}
}
