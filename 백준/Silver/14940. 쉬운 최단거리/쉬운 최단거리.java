import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static int[][] map, visited;
	public static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken()); 
		m = Integer.parseInt(st.nextToken()); 
		map = new int[n][m];
		visited = new int[n][m];
		int sx = 0, sy = 0; // 시작지점
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==0) visited[i][j] = -1;
				if (map[i][j]==2) {
					sx = i;
					sy = j;
				}
			}
		}
		
		bfs(sx, sy);
		int value = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				//System.out.print(visited[i][j]+" ");
				value = visited[i][j];
				if (value==0) sb.append(-1);
				else if (value==-1) sb.append(0);
				else sb.append(value-1);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		visited[x][y] = 1;
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i=0; i<4; i++) {
				int nextx = now.x + dx[i];
				int nexty = now.y + dy[i];
				if (nextx<0 || nexty<0 || nextx>=n || nexty>=m) continue;
				if (visited[nextx][nexty]>0 || map[nextx][nexty]<=0) continue;
				queue.add(new Point(nextx, nexty));
				visited[nextx][nexty] = visited[now.x][now.y] + 1;
			}
		}
	}
	
}

class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}