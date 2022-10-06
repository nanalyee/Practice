import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1};
	static int R,C, wolf, sheep;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		wolf=0; sheep=0;
		
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		} // end input
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				char ch = map[i][j];
				if ((ch=='v' || ch=='k') && !visited[i][j]) bfs(i,j);
			}
		}
		
		System.out.println(sheep+" "+wolf);
	}
	
	// bfs + 양 vs 늑대
	private static void bfs(int a, int b) {
		int v=0, k=0; // 늑대와 양 수 저장
		
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(a, b));
		visited[a][b] = true;
		if (map[a][b]=='v') v++;
		if (map[a][b]=='k') k++;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			for (int d=0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny]=='#' || visited[nx][ny]) continue;
				if (map[nx][ny]=='v') v++;
				if (map[nx][ny]=='k') k++;
				visited[nx][ny] = true;
				que.add(new Point(nx, ny));
			}
		} 
		if (k>v) sheep += k;
		else wolf += v;
	}

	
	// 위치 정보에 사용할 객체
	static class Point {
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}