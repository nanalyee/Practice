import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,0,1};
	static char[][] map;
	static boolean[][] visited;
	static boolean finished;
	static int r,c, result;
	static int startcheck, endcheck;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		result = 0;
		
		// 맵 입력
		for (int i=0; i<r; i++) {
			String str = br.readLine();
			for (int j=0; j<c; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 조사할 범위 정하기
		startcheck = -1; endcheck = 0;
		for (int i=0; i<c; i++) {
			int cnt=0;
			for (int j=0; j<r; j++) {
				if (map[j][i]=='x') cnt++;
			}
			if ( startcheck==-1 && cnt>0) startcheck = i;
			if (cnt>0) endcheck = i;
		}
		//System.out.println(startcheck+" "+endcheck);
		
		// 파이프 시뮬레이션
		for(int i=0; i<r; i++) {
			if (map[i][startcheck]=='.') {
				finished = false;
				dfs(i,startcheck);
			}
		}
		System.out.println(result);
		
		// 방문 확인해보기
		//printVisited();
		
	}



	private static void dfs(int a, int b) {
		
		if (finished) return;
		visited[a][b]=true;
		if(b==endcheck && !finished) {
			finished = true;
			result++;
			return;
		}
		
		
		for (int i=0; i<3; i++) {
			int x = a+dx[i];
			int y = b+1;
			if (x<0 || x>=r || visited[x][y]) continue;
			if (map[x][y]=='.') dfs(x,y);
		}
		
	}
	
	private static void printVisited() {
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (visited[i][j]) System.out.print("o ");
				else System.out.print("x ");
			}
			System.out.println();
		}
	}
}