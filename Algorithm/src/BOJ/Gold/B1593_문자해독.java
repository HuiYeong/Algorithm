package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class B1593_문자해독 {
	static int g, s, start, end, cnt, arr[], search[];
	static boolean flag;
	static Map<Integer, Integer> map;
	static String G, S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		G = br.readLine();
		S = br.readLine();
		arr = new int[80];
		search = new int[80];
		map = new HashMap<>();
		for (int i=0;i<g;i++) {
			int now = G.charAt(i)-'0';
			arr[now]++;
			if (map.containsKey(now)) map.put(now, map.get(now)+1);
			else map.put(now, 1);
		}
		end = 0; 
		while(true) {
			if (end-start >= g || end >= s) {
				flag = true;
				Iterator<Integer> keys = map.keySet().iterator();
				while( keys.hasNext()) { 
					int key = keys.next();
					if (arr[key] != search[key]) {
						flag = false;
						break;
					}
				}
				if (flag) cnt++;
				if (end >= s) break;
				
				search[S.charAt(start)-'0']--;
				start++;
			}
			
			search[S.charAt(end)-'0']++;
			end++;
		}
		System.out.println(cnt);
	}
}
