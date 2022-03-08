package BOJ.Gold;

import java.io.*;
import java.util.*;
 
public class B14890_경사로 {
    static int N, X, cnt;
    static int[][] map;
    static boolean[] check;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력완료
        boolean flag = true;
        for (int r=0;r<N;r++) {  // 가로 먼저 검사
            flag = true;
            check = new boolean[N];
            stop:for (int c=0;c<N-1;c++) {
                if (Math.abs(map[r][c]-map[r][c+1])>1) {
                    flag = false;
                    break stop;
                }
                 
                else if (map[r][c]-map[r][c+1]==1) {
                    for (int i=c+1;i<c+1+X;i++) {
                        if (!isIn(r, i) || map[r][c+1]!=map[r][i] || check[i]) {
                            flag = false;
                            break stop; 
                        }
                        check[i] = true;    
                    }
                    c+=X-1;
                }
                 
                else if (map[r][c]-map[r][c+1]==-1) {
                    for (int i=c;i>c-X;i--) {
                        if (!isIn(r, i) || map[r][c]!=map[r][i] || check[i]) {
                            flag = false;
                            break stop; 
                        }
                        check[i] = true;    
                    }
                }
            }
            if (flag) cnt++;
        }
         
        for (int c=0;c<N;c++) {  // 가로 먼저 검사
            flag = true;
            check = new boolean[N];
            stop:for (int r=0;r<N-1;r++) {
                if (Math.abs(map[r][c]-map[r+1][c])>1) {
                    flag = false;
                    break stop;
                }
                 
                else if (map[r][c]-map[r+1][c]==1) {
                    for (int i=r+1;i<r+1+X;i++) {
                        if (!isIn(i, c) || map[r+1][c]!=map[i][c] || check[i]) {
                            flag = false;
                            break stop; 
                        }
                        check[i] = true;    
                    }
                    r+=X-1;
                }
                 
                else if (map[r][c]-map[r+1][c]==-1) {
                    for (int i=r;i>r-X;i--) {
                        if (!isIn(i, c) || map[r][c]!=map[i][c] || check[i]) {
                            flag = false;
                            break stop; 
                        }
                        check[i] = true;    
                    }
                }
            }
            if (flag) cnt++;
        }
        System.out.println(cnt);
    }
     
    static boolean isIn (int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }
}