package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2581_소수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		boolean[] arr = new boolean[10001];
        arr[1] = true;
        for (int i=2;i*i<arr.length;i++) {
        	for (int j=i*i;j<arr.length;j+=i) {
        		arr[j] = true;
        	}
        }
        
        int sum = 0, MIN = 0;
        for (int i=M;i<=N;i++) {
        	if (!arr[i]) {
        		if (sum==0) MIN=i;
        		sum+=i;
        	}
        }
        
        if (sum==0) System.out.println(-1);
        else System.out.println(sum+"\n"+MIN);
	}
}
