import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main  {
	
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1};
	
	static int r, c, result;
	static char[][] map;
	static boolean[] alphabet;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		alphabet = new boolean[26];
				
		for (int i=0; i<r; i++) {
			String str = br.readLine();
			for (int j=0; j<c; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		dfs(0,0,1);

		System.out.println(result);
	}

	private static void dfs(int a, int b, int cnt) {
		alphabet[map[a][b]-'0'-17] = true;
				
		for (int i=0; i<4; i++) {
			int x = a+dx[i];
			int y = b+dy[i];
			
			if (x<0 || y<0 || x>=r || y>=c) continue; 
			
			int num = map[x][y] -'0' - 17;
			if (alphabet[num]) continue;
			dfs(x,y,cnt+1);
		}
		
		result = Math.max(result, cnt);


		
		alphabet[map[a][b]-'0'-17] = false;
	}



	
	
}