package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9342_염색체 {
	static boolean flag, good;
	static int index;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc=0;tc<TC;tc++) {
			flag = true;
			good = false;
			
			String now = br.readLine();
			index = 0;
			if(now.charAt(index) == 'A' || now.charAt(index) =='B' || now.charAt(index) =='C' || now.charAt(index) =='D' || now.charAt(index) =='E' || now.charAt(index) =='F') {
				good = true;
				index++;
			}
			
			if (flag && index < now.length()) check('A', now);
			if (flag && index < now.length()) check('F', now);
			if (flag && index < now.length()) check('C', now);
			
			good = false;
			if (flag && index<now.length()) {
				if(now.charAt(index) == 'A' || now.charAt(index) =='B' || now. charAt(index) =='C' || now.charAt(index) =='D' || now.charAt(index) =='E' || now.charAt(index) =='F') {
					good = true;
					index++;
				}
			}
			
			if (good && flag && index == now.length()-2) sb.append("Infected!\n");
			else if (flag && index == now.length()) sb.append("Infected!\n");
			else sb.append("Good\n");
		}
		System.out.println(sb);
	}
	
	static void check (char target, String now) {
		if (now.charAt(index) != target) {
			if (good) {
				index--;
				good = false;
			}
			else flag = false;
		} 
		if (now.charAt(index)==target){
			while(true) {
				if (index < now.length()) index++;
				if (index >= now.length() || now.charAt(index)!=target) break;
			}
		}
		
	}
}
