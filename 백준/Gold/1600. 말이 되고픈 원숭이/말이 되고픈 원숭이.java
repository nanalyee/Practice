import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int hx[] = {-1,-2,-2,-1,1,2,2,1};
	static int hy[] = {-2,-1,1,2,2,1,-1,-2};
	static int dx[] = {-1,0,1,0}; // 상우하좌
	static int dy[] = {0,1,0,-1};
	static int k, w, h,  min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new boolean[h][w][k+1];
		
		for (int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}
	
	
	private static int bfs() {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(0,0,0,k));
		visited[0][0][k]=true;
		
		// 1. 말 이동이 남았을때 -> 말이동 or 단순이동
		// 2. 단순이동
		while(!que.isEmpty()) {
			Point now = que.poll();
			//System.out.println(now.x+" "+now.y);
			if (now.x==h-1 && now.y==w-1) return now.cnt;
					
			for (int d=0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (nx<0 || nx>=h || ny<0 || ny>=w || map[nx][ny]==1) continue;
				if (visited[nx][ny][now.k]) continue;
				visited[nx][ny][now.k] = true; 
				que.add(new Point (nx, ny, now.cnt+1, now.k));
			}
			if (now.k>0) {
				for (int d=0; d<8; d++) {
					int nx = now.x + hx[d];
					int ny = now.y + hy[d];
					if (nx<0 || nx>=h || ny<0 || ny>=w || map[nx][ny]==1) continue;
					if (visited[nx][ny][now.k-1]) continue;
					visited[nx][ny][now.k-1] = true; 
					que.add(new Point (nx, ny, now.cnt+1, now.k-1));
				}
			} 
			
		}
		return -1;
	}

	static class Point{
		int x, y, cnt, k;

		public Point(int x, int y, int cnt, int k) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}

}