package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16235_나무재테크 {
	static int N, M, K, size, nowR, nowC, nowAge;
	static int[][] field, addFood, deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};;
	static Queue<Tree> trees, deadTrees, addTree;
	
	static class Tree implements Comparable<Tree> {
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		field = new int[N][N]; // 땅
		addFood = new int[N][N]; // 매해 추가되는 양분
		for (int i=0;i<N;i++) {
			Arrays.fill(field[i], 5); // 초기엔 양
		}
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				addFood[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		trees = new LinkedList<>();
		deadTrees = new LinkedList<>();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			trees.offer(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		// 초기 설정 완료
		
		Collections.sort((List<Tree>)trees);
		
		for (int i=0;i<K;i++) {
			spring(); // 봄 : 나이 먹자
			summer(); // 여름 : 죽은 나무 양분으로 변함
			fall(); // 가을 : 나무 번식
			winter(); // 겨울 : 양분 추가
		}
		
		System.out.println(trees.size());
	}
	
	static void spring() {
		size = trees.size();
		for (int i=0;i<size;i++) {
			Tree now = trees.poll();
			if (field[now.r][now.c]>=now.age) {
				field[now.r][now.c] -= now.age;
				trees.offer(new Tree(now.r, now.c, now.age+1));
			} else deadTrees.offer(now);
		}
	}
	
	static void summer() {
		size = deadTrees.size();
		for (int i=0;i<size;i++) {
			Tree now = deadTrees.poll();
			field[now.r][now.c] += now.age/2;
		}
	}
	
	static void fall() {
		addTree = new LinkedList<>();
		size = trees.size();
		for (int i=0;i<size;i++) {
			Tree now = trees.poll();
			if (now.age%5==0) {
				for (int d=0;d<8;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc)) trees.offer(new Tree(nr, nc, 1));
				}
			}
			addTree.offer(now);
		}
		
		for (Tree tree : addTree) {
			trees.offer(tree);
		}
	}
	
	static void winter() {
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				field[i][j] += addFood[i][j];
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
