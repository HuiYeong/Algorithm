package BOJ.Silver;

import java.io.*;
import java.util.*;

public class B2477_참외밭 {
	static int K, R, C, RR, CC, arr[] = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		K = Integer.parseInt(br.readLine());
	    StringTokenizer st = null;
	    for (int i = 0; i < 6; i++) {
	        st = new StringTokenizer(br.readLine());
	        int d = Integer.parseInt(st.nextToken());
	        arr[i] = Integer.parseInt(st.nextToken());
	    }
	 
	    for (int i = 0; i < 6; i++) {
	        if (i % 2 == 0 && C < arr[i]) C = arr[i];
	        else if (i % 2 == 1 && R < arr[i]) R = arr[i];
	    }
	 
	    for (int i = 0; i < 6; i++) {
	        if (i % 2 == 0 && R == arr[(i + 5) % 6] + arr[(i + 1) % 6])  CC = arr[i];
	        else if (i % 2 == 1 && C == arr[(i + 5) % 6] + arr[(i + 1) % 6]) RR = arr[i];
	    }
	    System.out.println((C * R - CC * RR) * K);
	}
}
