import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static int n,m;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int preCheese = 0;
		int bfsCnt = 0;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) preCheese++;
			}
		}
		
		while(true) {
			bfs();
			bfsCnt++;
			int cheeseCnt = cheese();
			if(cheeseCnt==0) break;
			preCheese = cheeseCnt;
		}
		
		System.out.println(bfsCnt);
		System.out.println(preCheese);
	}
	
	private static void bfs() {
		visited = new boolean[n][m];
		Queue<Point> que = new LinkedList<Point>();
		que.add(new Point(0,0));
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			for (int d=0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (nx<0 || ny<0 || nx>=n || ny>=m) continue;
				if (visited[nx][ny]) continue;
				if (map[nx][ny]==1) {
					map[nx][ny] = 0; 
					visited[nx][ny] = true;
				} else {
					visited[nx][ny] = true;
					que.add(new Point(nx,ny));
				}

			}
		}
		
		//checkMap();
	}

	private static void checkMap() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---");
	}

	private static int cheese() {
		int cnt = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j]==1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}