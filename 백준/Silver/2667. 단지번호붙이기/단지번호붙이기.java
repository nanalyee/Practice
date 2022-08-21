import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

class Main {
    // 선언
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0,0,-1,1 }; // 위, 아래, 좌, 우
    static int[] dy = { -1,1,0,0 };
    static int n;
    static int count = 0;
    static int num = 0;

	static public void main(String []args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // 입력 및 초기화
        n = sc.nextInt(); // map 한변
        map = new int[n][n];
        visited = new boolean[n][n];
        for ( int i=0 ; i<n ; i++ ) {
            String str = sc.next();
            for ( int j=0 ; j<n ; j++ ) {
                char ch = str.charAt(j);
                map[i][j] = (int)ch-'0';
            }
        }

        // 간선
        ArrayList<Integer> list = new ArrayList<>();
        for ( int i=0 ; i<n ; i++ ) {
            for ( int j=0 ; j<n ; j++ ) {
                if ( map[i][j] == 1 && !visited[i][j] ) {
                    count++;
                    dfs(i,j);
                    list.add(num);
                    num = 0;
                }
            }
        }

        System.out.println(count);
        Collections.sort(list);
        for ( int i=0 ; i<count; i++ ) {
            System.out.println(list.get(i));
        }
    }

    // dfs
    static void dfs( int x, int y ) {
        num++; // 아파트 누적
        visited[x][y] = true;
        for ( int i=0 ; i<4 ; i++ ) {
            int a = x+dx[i];
            int b = y+dy[i];
            if ( a >= 0 && a < n && b >= 0 && b < n ) {
                if ( map[a][b] == 1 && !visited[a][b] ) {
                    dfs(a,b);
                }
            }
            
        }
    }
}