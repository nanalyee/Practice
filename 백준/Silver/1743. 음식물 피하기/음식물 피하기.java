import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// bfs로 가장 큰 음식물 덩어리 크기 출력
	
	static int dx[]= {-1,1,0,0}; // 상하좌우
	static int dy[]= {0,0,-1,1};
	static int N, M, K, res, cnt;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c]=1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(!visited[i][j] && map[i][j]==1) {
					cnt=0; // 덩어리 크기 초기화
					bfs(i,j);
					res = Math.max(res, cnt); // 큰값으로 갱신
				}
			}
		}
		System.out.println(res);
	}

	private static void bfs(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x, y));
		visited[x][y] = true;
		cnt++;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for (int k = 0; k < 4; k++) {
				int nx = now.x +dx[k];
				int ny = now.y +dy[k];
				if(nx<1 || ny<1 || nx>=N+1 || ny>=M+1)continue;
				if(!visited[nx][ny] && map[nx][ny]==1) {
					que.add(new Point(nx, ny));
					visited[nx][ny]=true;
					cnt++;
				}
			}
		}
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}