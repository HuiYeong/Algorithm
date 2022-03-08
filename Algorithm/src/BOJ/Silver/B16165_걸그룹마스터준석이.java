package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B16165_걸그룹마스터준석이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, String[]> group = new HashMap<>();
		for (int i=0;i<N;i++) {
			String name = br.readLine(); // 그룹 팀 
			int count = Integer.parseInt(br.readLine()); // 그룹 인
			String[] member = new String[count]; // 멤버 이름 담을 배
			for (int j=0;j<count;j++) {
				member[j] = br.readLine(); 
			}
			Arrays.sort(member);
			group.put(name, member); // 미리 생성한 멤버 이름 배열과 팀 이름 넣어
		}
		
		for (int i=0;i<M;i++) {
			String question = br.readLine(); // 팀 이름 or 멤버 이름
			int command = Integer.parseInt(br.readLine()); // 1-> 해당 멤버가 속한 팀 이름, 0-> 팀에 속한 멤버 이
			if (command == 1) {
				sb.append(getKey(group, question)+"\n");
			}
			else {
				for (int j=0;j<group.get(question).length;j++) {
					sb.append(group.get(question)[j]+"\n");
				}
			}
		}
		System.out.println(sb);
	}

	public static <K, V> String getKey(HashMap<String, String[]> group, String question) {
		 
        for (String key : group.keySet()) { // hashmap에 있는 키들 가져오
        	for (int i=0;i<group.get(key).length;i++) {
        		if (question.equals(group.get(key)[i])) { // 찾는 값이 해당 키의 값에 존재하
                    return key; // 해당 키 리턴
                }
        	}
        }
        return null;
    }
}
