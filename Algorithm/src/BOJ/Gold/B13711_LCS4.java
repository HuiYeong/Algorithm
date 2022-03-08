package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 수열
 */
public class B13711_LCS4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arrA[] = new int[N+1];
		int arrB[] = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			arrA[Integer.parseInt(st.nextToken())] = i;
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arrB[i] = arrA[Integer.parseInt(st.nextToken())];
		}
		
		int size = 0;
		arrA[size++] = arrB[0];
        for (int i = 1; i < N; i++) {
        	int idx = Arrays.binarySearch(arrA, 0, size, arrB[i]);
        	// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
            if (idx == -(size + 1)) 
            	arrA[size++] = arrB[i];
            else
            	arrA[-(idx + 1)] = arrB[i];
        }
        System.out.print(size);
	}
}
