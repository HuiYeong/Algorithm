package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16935_배열돌리기3 {
	private static int[][] arr;
	private static int[][] arrR;
	private static int N, M;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<R;i++) {
			int command = Integer.parseInt(st.nextToken());
			
			if (command == 1 || command == 2)  arr = menu12(command);
			else if (command == 3 || command == 4) arr = menu34(command);
			else if (command == 5) {
				int[][] temp = {{0,0}, {0,arr[0].length/2}, {arr.length/2,arr[0].length/2}, {arr.length/2,0}};
				arr = menu56(temp, command);
			}
			else if (command == 6) {
				int[][] temp = {{0,0}, {arr.length/2,0}, {arr.length/2,arr[0].length/2}, {0,arr[0].length/2}};
				arr = menu56(temp, command);
			}
		}
		
		print();
		System.out.println(sb);
	}
	
	private static void print() {
		for (int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[i].length;j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
	}
	
	private static int[][] menu12(int command) {
		arrR = new int[arr.length][arr[0].length];
		for (int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[0].length;j++) {
				if (command == 1) arrR[i][j] = arr[arr.length-i-1][j];
				else if (command == 2) arrR[i][j] = arr[i][arr[0].length-j-1];
			}
		}
		return arrR;
	}
	
	private static int[][] menu34(int command) {
		arrR = new int[arr[0].length][arr.length];
		int r = 0;
		int c = command == 3?-1:arr[0].length;
		for (int i=0;i<arr[0].length;i++) {
			r = command == 3?arr.length-1:0;
			c = command == 3?c+1:c-1;
			for (int j=0;j<arr.length;j++) {
				arrR[i][j] = command == 3?arr[r--][c]:arr[r++][c];
			}
		}
		return arrR;
	}
	
	private static int[][] menu56(int[][] temp, int command) {
		arrR = new int[arr.length][arr[0].length];
		int arrI = command == 5?arr.length/2:0;
		int arrJ = command == 5?0:arr[0].length/2;
		for (int k=0;k<4;k++) {
			int startI = temp[k][0], startJ = temp[k][1];
			for (int i=startI;i<startI+arr.length/2;i++) {
				for (int j=startJ;j<startJ+arr[0].length/2;j++) {
					arrR[i][j] = arr[arrI][arrJ++];
				}
				arrI++; arrJ-=arr[0].length/2;
			}
			arrI = startI; arrJ = startJ;
		}
		
		return arrR;
	}
}
