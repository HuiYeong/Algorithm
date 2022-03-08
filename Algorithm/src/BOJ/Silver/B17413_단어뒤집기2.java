package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17413_단어뒤집기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		
		String word = br.readLine();
		char w2 = 0;
		int size = word.length();
		for (int i=0;i<size;i++) {
			char w = word.charAt(i);
			if (w=='<') { // 괄호 들어오면 괄호 닫을때까지 temp에 append
				int cnt = 0;
				while(true) {
					if (i+cnt<size) { // +cnt가 word 사이즈보다 작을 
						if (word.charAt(i+cnt)=='>') { // 다음 인덱스가 괄호 닫기
							temp.append(">"); // 괄호 닫기 넣기
							break; // 반복문 끝내기
						} 
						temp.append(word.charAt(i+cnt)); // 다음 인덱스 괄호 나올때까지 추가
						cnt++;
					}
				}
				i+=cnt; // 반복문 더 돌린만큼 i에 더하기
				sb.append(temp); // 그대로 삽입
				if (i+1<size && word.charAt(i+1)==' ') sb.append(" "); // 뒤에 띄어쓰기 있으면 뛰어쓰기도 삽입
				temp = new StringBuilder(); // temp 초기화
			}
			else if (w!=' ')temp.append(w); // 띄어쓰기가 아니라면 다 추가
			
			if (i+1<size) w2 = word.charAt(i+1); // 다음꺼 인덱스
			if (w2!=0 && (w2==' '||w2=='<')) { // 다음 인덱스가 띄어쓰기나 괄호가 아닐 때
				sb.append(temp.reverse()); // 뒤집어주기
				if (w2==' ') sb.append(" "); // 띄어쓰기 있으면 띄어쓰기 추가
				temp = new StringBuilder(); // temp 초기화
			}
		}
		if (temp.length()!=0) sb.append(temp.reverse()); // temp에 값 남아있으면 추가
		System.out.println(sb);
	}
}

