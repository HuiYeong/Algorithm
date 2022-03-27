package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * < 구현 문제 >
 * 
 * 5357 큐빙과 비슷한 문제로 모든 칸을 시계방향, 반시계방향으로 2번씩 전부 돌려보면 끝
 * 
 * 1. 큐브 돌릴 때 주변 4개 말고도 돌리는 위치도 회전
 * 2. 위, 아래를 제외한 칸을 돌릴 때는 지정한 순서가 반대로 될 수 있으니 유의
 * 이를 주의하며 코드를 짜줬다.
 *
 * 모든 칸에 대해 시계방향으로 이동하는 칸들의 순서를 지정해준 뒤 지정된 순서로 이동
 * 
 */
public class B16939_2x2x2큐브 {
	static int tempArr[][], num, dir, d, order, dirArr[][] = {{0, 0}, {1, 0}, {0, 1}, {0, 0}};
	static boolean clockwise;
	static String temp="", now="";
	static StringBuilder reverseSb = new StringBuilder();
	static List<int[][]> list = new ArrayList<>(), initList = new ArrayList<>();
	static List<int[][]> orderList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<6;i++) {
			tempArr = new int[2][2];
			for (int r=0;r<2;r++) {
				for (int c=0;c<2;c++) {
					tempArr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			initList.add(tempArr);
		}
		
		init();
		// 초기화 끝
		
		for (int i=0;i<6;i++) {
			order = i;
			clockwise = true; // 시계방향
			for (int j=0;j<2;j++) {
				rotate(); // 돌리는 칸 회전
				cubing(); // 주변 칸 회전
				if (check()) { // 각 칸의 색깔이 동일한지 체크
					System.out.println(1); // 동일하면 1 출력
					System.exit(0);
				}
				clockwise = false; // 반시계 방향
				refresh(); // 한 번 돌렸으니 초기 배열로 되돌리기
			}
		}
		
		System.out.println(0); // 모든 방향을 돌렸을 때 동일하지 않다면 0 출력
	}
	
	static void cubing() {
		int[][] nowArr = orderList.get(order);
		for (int i=0;i<5;i++) {
			num = nowArr[d][0]; dir = nowArr[d][1]; // now : 현재 회전해야 하는 칸, dir : 이동하는 위치
			int r = dirArr[dir][0], c = dirArr[dir][1];
			for (int j=0;j<2;j++) {
				now += list.get(num)[r][c]; // 지금 내가 가진 값 (이동해야 하는 값)
				if (i!=0) list.get(num)[r][c] = Character.digit(temp.charAt(j), 10); // 이동된 값 넣기
				if (dir%2==0) r++;
				else c++;
			}
			temp = now; now = "";
			
			// 이동하는 값이 반대로 될 경우
			if (order>1 && ((clockwise && ( (order==4 && d>1) || (order==5 && d<2) || (order==2 && d%2==1) || (order==3 && d%2==0) )) || 
					(!clockwise && ((order==4 && (d==0 || d==3)) || (order==5 && (d==1 || d==2)) || (order==2 && d%2==0) || (order==3 && d%2==1) ) )) ) {
				reverseSb.append(temp);
				temp = reverseSb.reverse().toString();
				reverseSb.delete(0, reverseSb.length());
			}
			
			if (clockwise) d = d+1>3?0:d+1; // 시계 방향 이동
			else d = d-1<0?3:d-1; // 반시계 방향 이동
		}
	}
	
	static void rotate() {
		tempArr = new int[2][2];
		int nr = clockwise?1:0, nc = clockwise?0:1;
		for (int r=0;r<2;r++) {
			for (int c=0;c<2;c++) {
				tempArr[nr][nc] = list.get(order)[r][c]; // tempArr에 돌린 값 저장
				if (!clockwise && ++nr == 2) nr = 0;
				else if (clockwise && --nr==-1) nr = 1;
			}
			if (!clockwise) nc--;
			else nc++;
		}
		
		for (int r=0;r<2;r++) {
			for (int c=0;c<2;c++) {
				list.get(order)[r][c] = tempArr[r][c]; // 다 돌리고 넣어주기
			}
		}
	}
	
	static boolean check() { // 각 칸의 색깔이 동일한지 체크
		for (int i=0;i<6;i++) {
			int[][] now = list.get(i);
			int value = now[0][0];
			for (int r=0;r<2;r++) {
				for (int c=0;c<2;c++) {
					if (now[r][c] != value) return false;
				}
			}
		}
		return true;
	}
	
	static void refresh() { // 초기 배열로 refresh
		for (int i=0;i<6;i++) {
			for (int r=0;r<2;r++) {
				for (int c=0;c<2;c++) {
					list.get(i)[r][c] = initList.get(i)[r][c];
				}
			}
		}
	}
	
	static void init() { // 초기화
		for (int i=0;i<6;i++) {
			tempArr = new int[2][2];
			for (int r=0;r<2;r++) {
				for (int c=0;c<2;c++) {
					tempArr[r][c] = initList.get(i)[r][c];
				}
			}
			list.add(tempArr);
		}
		
		int[][] orderArr0 = {{5, 3}, {3, 3}, {1, 3}, {4, 3}}; //B
		int[][] orderArr1 = {{2, 3}, {4, 0}, {1, 1}, {3, 2}}; //D
		int[][] orderArr2 = {{5, 1}, {4, 1}, {1, 1}, {3, 1}}; //F
		int[][] orderArr3 = {{5, 2}, {2, 0}, {1, 0}, {0, 0}}; //L
		int[][] orderArr4 = {{5, 0}, {0, 2}, {1, 2}, {2, 2}}; //R
		int[][] orderArr5 = {{2, 1}, {3, 0}, {0, 3}, {4, 2}}; //U
		orderList.add(orderArr0);
		orderList.add(orderArr1);
		orderList.add(orderArr2);
		orderList.add(orderArr3);
		orderList.add(orderArr4);
		orderList.add(orderArr5);
	}
}
