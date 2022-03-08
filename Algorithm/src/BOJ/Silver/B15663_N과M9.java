package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B15663_N과M9 {
	static int[] arr;
	   static int N;
	   static int M;
	   static int[] allSel;
	   static int max = 0;
	   static StringBuilder sb = new StringBuilder();
	   
	   public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      String[] NM = br.readLine().split(" ");
	      N = Integer.parseInt(NM[0]);
	      M = Integer.parseInt(NM[1]);
	      allSel = new int[10001];

	      arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	      Arrays.sort(arr);

	      for (int i : arr) {
	         allSel[i]++;
	         max = max < i ? i : max;
	      }
	      
	      permutation(0, "");
	      
	      System.out.println(sb.toString());
	   }

	   // 카운트 0부터
	   private static void permutation(int cnt, String answer) {
	      if (cnt == M) {
	         sb.append(answer+"\n");
	         return;
	      }
	      
	      for (int i = 1; i <= max; i++) {
	         if (allSel[i] == 0)
	            continue;
	         
	         allSel[i]--;
	         permutation(cnt + 1, answer + i + " ");
	         allSel[i]++;
	      }
	   }

}