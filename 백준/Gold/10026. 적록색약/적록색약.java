import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<int[]> que = new LinkedList<int[]>();
	static char[][] map; // 색상맵
	static boolean[][] visited, visitedWeak; // 방문지도
	static int n, result, resultWeak; // 크기, 범위 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		visited = new boolean[n][n]; 
		visitedWeak = new boolean[n][n];
		result = 0; 
		resultWeak = 0;
		
		for (int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (!visited[i][j]) {
					bfs(i,j);
					result++;
				}
				if (!visitedWeak[i][j]) {
					bfsWeak(i,j);
					resultWeak++;
				}
			}
		}
		
		System.out.println(result+" "+resultWeak);
	}

	private static void bfsWeak(int a, int b) {
		que.add(new int[] {a,b});
		char first = map[a][b];
		visitedWeak[a][b] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
				
			for (int i=0; i<4; i++) {
				int x = now[0]+dx[i];
				int y = now[1]+dy[i];
				
				if (x<0 || y<0 || x>=n || y>=n || visitedWeak[x][y]) continue;
				if (first=='R' || first=='G') {
					if (map[x][y]=='R' || map[x][y]=='G') {
						que.add(new int[] {x,y});
						visitedWeak[x][y] = true;
					}
				}
				else if (first==map[x][y]) {
					que.add(new int[] {x,y});
					visitedWeak[x][y] = true;
				}
			}
		}
	}

	private static void bfs(int a, int b) {
		que.add(new int[] {a,b});
		char first = map[a][b];
		visited[a][b] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
				
			for (int i=0; i<4; i++) {
				int x = now[0]+dx[i];
				int y = now[1]+dy[i];
				
				if (x<0 || y<0 || x>=n || y>=n ) continue;
				if (first!=map[x][y] || visited[x][y] ) continue;
				que.add(new int[] {x,y});
				visited[x][y] = true;
			}
		}
		
	}
	
}