package BOJ.Bronze;

import java.util.Scanner;

public class B8320_직사각형만드는방법 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int count = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=i; i*j<=n; j++) {
				count ++;
			}
		}
		
		System.out.println(count);
		scan.close();
	}

}
