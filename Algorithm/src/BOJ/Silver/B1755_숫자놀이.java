package BOJ.Silver;

import java.io.*;
import java.util.*;

public class B1755_숫자놀이 {
	static String[] trans = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}; // 각 수에 해당하는 영어로 변환
	static class Number implements Comparable<Number>{ // 숫자와 영어로 변환한 숫자를 관리하기 위한 class 생성
		String num, english;

		public Number(String num, String english) {
			super();
			this.num = num;
			this.english = english;
		}

		@Override
		public int compareTo(Number o) {
			return this.english.compareTo(o.english); // 영어 사전순으로 정렬하기 위함
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		StringBuilder sb = null;
		// 입력완료
		List<Number> list = new ArrayList<>(); // 변환한 값 담기 위한 리스트 선언
		for (int i=M;i<=N;i++) {
			String now = i+""; // 숫자 단위로 하나씩 읽기 위함
			sb = new StringBuilder();
			for (int j=0;j<now.length();j++) { // 입력된 숫자 길이만큼
				int temp = now.charAt(j)-'0'; // 하나씩 읽어서
				sb.append(trans[temp]); // 해당하는 숫자의 영어로 바꿔주기
			}
			list.add(new Number(now, sb.toString())); // 리스트에 저장
		}
		Collections.sort(list); // 사전순대로 정렬
		sb = new StringBuilder();
		for (int i=0;i<list.size();i++) {
			sb.append(list.get(i).num+" ");
			if ((i+1)%10==0) sb.append("\n"); // 10개 출력되면 줄 바꿈
		}
		System.out.println(sb);
	}
}
