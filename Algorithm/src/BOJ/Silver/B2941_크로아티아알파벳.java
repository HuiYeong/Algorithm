package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2941_크로아티아알파벳 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		int cnt = 0;
		int size = word.length();
		for (int i=0;i<word.length();i++) {
			boolean success = false;
			char temp = word.charAt(i);
			char temp2 = 0;
			if (i+1<size) temp2 = word.charAt(i+1);
			if (temp=='c') {
				if (temp2=='=' || temp2=='-') success = true;
			}
			else if (temp=='d') {
				if (temp2=='z' || temp2=='-') {
					if (temp2=='z' && i+2<size && word.charAt(i+2)=='=') {
						cnt++;
						i+=2;
						continue;
					}
					else if (temp2=='-'){
						success = true;
					}
				}
			}
			else if ((temp=='l' || temp=='n') && temp2=='j') success = true;
			else if ((temp=='s' || temp=='z') && temp2=='=') success = true;
			if (success) i++;
			cnt++;
		}
		
		System.out.println(cnt);
	}
}