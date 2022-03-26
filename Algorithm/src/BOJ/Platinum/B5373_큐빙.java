package BOJ.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * < 구현 문제 >
 * 
 * 하드코딩.. 주의할 점이 있다.
 * 1. 큐브 돌릴 때 주변 4개 말고도 돌리는 위치도 회전
 * 2. 윗면, 아랫면을 제외한 면을 돌릴 때는 지정한 순서가 반대로 될 수 있으니 유의
 * 
 * 모든 면에 대해 시계방향으로 이동하는 면들의 순서를 지정해준다. 
 */
public class B5373_큐빙 {
	static int TC, orderCnt, num, dir, d, order, dirArr[][] = {{0, 0}, {2, 0}, {0, 2}, {0, 0}};
	static String nowOrder, temp="", now="";
	static StringBuilder sb = new StringBuilder(), reverseSb = new StringBuilder();
	static char type, tempArr[][] = new char[3][3], color[][], colorArr[] = {'w', 'y', 'r', 'o', 'g', 'b'}, orderArr[] = {'U', 'D', 'F', 'B', 'L', 'R'};
	static List<char[][]> list = new ArrayList<>();
	static List<int[][]> orderList = new ArrayList<>();
	static Map<Character, Integer> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		init();
		
		TC = Integer.parseInt(br.readLine());
		while(TC-->0) {
			orderCnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<orderCnt;i++) {
				nowOrder = st.nextToken(); d = 0;
				order = map.get(nowOrder.charAt(0)); // 어떤 면을 돌릴까
				type = nowOrder.charAt(1); // 시계 방향? 반시계 방향?
				rotate(); // 돌리는 면 회전
				cubing(); // 주변 면 회전
			}
			up();
			refresh();
		}
		
		System.out.println(sb);
	}
	
	static void cubing() {
		int[][] nowArr = orderList.get(order);
		for (int i=0;i<5;i++) {
			num = nowArr[d][0]; dir = nowArr[d][1]; // now : 현재 회전해야 하는 면, dir : 이동하는 위치
			int r = dirArr[dir][0], c = dirArr[dir][1];
			for (int j=0;j<3;j++) {
				now += list.get(num)[r][c]; // 지금 내가 가진 값 (이동해야 하는 값)
				if (i!=0) list.get(num)[r][c] = temp.charAt(j); // 이동된 값 넣기
				if (dir%2==0) r++;
				else c++;
			}
			temp = now; now = "";
			
			// 이동하는 값이 반대로 될 경우
			if (order>1 && ((type=='+' && ( (order==4 && d>1) || (order==5 && d<2) || (order==2 && d%2==1) || (order==3 && d%2==0) )) || 
					(type=='-' && ((order==4 && (d==0 || d==3)) || (order==5 && (d==1 || d==2)) || (order==2 && d%2==0) || (order==3 && d%2==1) ) )) ) {
				reverseSb.append(temp);
				temp = reverseSb.reverse().toString();
				reverseSb.delete(0, reverseSb.length());
			}
			
			if (type=='+') d = d+1>3?0:d+1; // 시계 방향 이동
			else d = d-1<0?3:d-1; // 반시계 방향 이동
		}
	}
	
	static void rotate() {
		int nr = type=='+'?0:2, nc = type=='+'?2:0;
		for (int r=0;r<3;r++) {
			for (int c=0;c<3;c++) {
				tempArr[nr][nc] = list.get(order)[r][c]; // tempArr에 돌린 값 저장
				if (type=='+' && ++nr == 3) nr = 0;
				else if (type=='-' && --nr==-1) nr = 2;
			}
			if (type=='+') nc--;
			else if (type=='-') nc++;
		}
		
		for (int r=0;r<3;r++) {
			for (int c=0;c<3;c++) {
				list.get(order)[r][c] = tempArr[r][c]; // 다 돌리고 넣어주기
			}
		}
	}
	
	static void up() { // 윗면 출력
		for (int r=0;r<3;r++) {
			for (int c=0;c<3;c++) {
				sb.append(list.get(0)[r][c]);
			}
			sb.append("\n");
		}
	}
	
	static void refresh() { // 테스트 케이스 돌릴 때마다 초기 큐브로 만들어주기
		for (int i=0;i<6;i++) {
			for (int j=0;j<3;j++) {
				Arrays.fill(list.get(i)[j], colorArr[i]);
			}
		}
	}
	
	static void init() { // 초기화
		for (int i=0;i<6;i++) {
			color = new char[3][3];
			map.put(orderArr[i], i);
			for (int j=0;j<3;j++) {
				Arrays.fill(color[j], colorArr[i]);
			}
			list.add(color);
		}
		
		int[][] orderArr0 = {{2, 3}, {4, 3}, {3, 3}, {5, 3}};
		int[][] orderArr1 = {{2, 1}, {5, 1}, {3, 1}, {4, 1}};
		int[][] orderArr2 = {{0, 1}, {5, 0}, {1, 3}, {4, 2}};
		int[][] orderArr3 = {{0, 3}, {4, 0}, {1, 1}, {5, 2}};
		int[][] orderArr4 = {{0, 0}, {2, 0}, {1, 0}, {3, 2}};
		int[][] orderArr5 = {{0, 2}, {3, 0}, {1, 2}, {2, 2}};
		orderList.add(orderArr0);
		orderList.add(orderArr1);
		orderList.add(orderArr2);
		orderList.add(orderArr3);
		orderList.add(orderArr4);
		orderList.add(orderArr5);
	}
}
