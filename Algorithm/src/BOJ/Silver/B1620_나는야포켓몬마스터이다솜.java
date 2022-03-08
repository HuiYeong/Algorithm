package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1620_나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> pokemonName = new HashMap<>();
		HashMap<Integer, String> pokemonNum = new HashMap<>();
		for (int i=1;i<=N;i++) {
			String name = br.readLine();
			pokemonName.put(name, i);
			pokemonNum.put(i, name);
		}
		
		for (int i=0;i<M;i++) {
			String question = br.readLine();
			if (48<=question.charAt(0) && question.charAt(0)<=57) {
				sb.append(pokemonNum.get(Integer.parseInt(question))+"\n");
			} else {
				sb.append(pokemonName.get(question)+"\n");
			}
		}
		System.out.println(sb);
	}
}

