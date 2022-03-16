package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B15898_피아의아틀리에_신비한대회의연금술사 {
	static int N, MAX, arrEff[][], tempEff[][], orderSelect[] = new int[3], locSelect[] = new int[3], dirSelect[] = new int[3], loc[][] = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	static char arrEle[][], tempEle[][];
	static boolean orderVisit[];
	static List<int[][]> effList = new ArrayList<>();
	static List<char[][]> eleList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for (int i=0;i<N;i++) {
			tempEff = new int[4][4];
			tempEle = new char[4][4];
			for (int r=0;r<4;r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0;c<4;c++) {
					tempEff[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			for (int r=0;r<4;r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0;c<4;c++) {
					tempEle[r][c] = st.nextToken().charAt(0);
				}
			}
			effList.add(tempEff);
			eleList.add(tempEle);
		}
		// 입력 완료
		orderVisit = new boolean[N];
		permutationOrder(0);
		System.out.println(MAX);
	}
	
	static void permutationOrder(int cnt) { // 넣을 재료 뽑기
		if (cnt == 3) {
			combinationLoc(0, 0);
			return;
		}
		
		for (int i=0;i<N;i++) {
			if (orderVisit[i]) continue;
			orderSelect[cnt] = i;
			orderVisit[i] = true;
			permutationOrder(cnt+1);
			orderVisit[i] = false;
		}
	}
	
	static void combinationLoc(int cnt, int start) { // 위치 뽑기 ( (0,0) (0,1) (1,0) (1,1) 시작 위치)
		if (cnt == 3) {
			combinationDir(0, 0);
			return;
		}
		
		for (int i=start;i<4;i++) {
			locSelect[cnt] = i;
			combinationLoc(cnt+1, start);
		}
	}
	
	static void combinationDir(int cnt, int start) { // 방향 뽑기 (반시계 1번, 2번, 3번 회전)
		if (cnt == 3) {
			start();
			return;
		}
		
		for (int i=start;i<4;i++) {
			dirSelect[cnt] = i;
			combinationDir(cnt+1, start);
		}
	}
	
	static void start() {
		arrEff = new int[5][5];
		arrEle = new char[5][5];
		for (int i=0;i<5;i++) {
			Arrays.fill(arrEle[i], 'W'); // W로 채우기
		}
		
		for (int i=0;i<3;i++) { // 하나씩 재료 넣기
			add(effList.get(orderSelect[i]), eleList.get(orderSelect[i]), locSelect[i], dirSelect[i]);
		}

		int R = 0, B = 0, G = 0, Y = 0;
		for (int r=0;r<5;r++) {
			for (int c=0;c<5;c++) {
				if (arrEle[r][c] == 'R') R += arrEff[r][c];
				else if (arrEle[r][c] == 'B') B += arrEff[r][c];
				else if (arrEle[r][c] == 'G') G += arrEff[r][c];
				else if (arrEle[r][c] == 'Y') Y += arrEff[r][c];
			}
		}
		MAX = Math.max(MAX, (7*R)+(5*B)+(3*G)+(2*Y));
	}
	
	static void add(int[][] eff, char[][] ele, int location, int dir) {
		int addR = dir<2?0:3;
		int addC = dir==1||dir==2?3:0; 
		for (int r=loc[location][0];r<loc[location][0]+4;r++) {
			for (int c=loc[location][1];c<loc[location][1]+4;c++) {
				int value = arrEff[r][c] + eff[addR][addC];
				arrEff[r][c] = value<0?0:value>=9?9:value; // 0보다 작으면 0, 9보다 많으면 
				arrEle[r][c] = ele[addR][addC]=='W'?arrEle[r][c]:ele[addR][addC]; // W면 그대로, 아닐 경우 재료 원소
				if (dir == 0 && ++addC>=4) addC = 0;
				else if (dir == 1 && ++addR>=4) addR = 0;
				else if (dir == 2 && --addC<0) addC = 3;
				else if (dir == 3 && --addR<0) addR = 3;
			}
			if (dir == 0) addR++;
			else if (dir == 1) addC--;
			else if (dir == 2) addR--; 
			else addC++;
		}
	}
}
