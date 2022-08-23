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
	static int[][][] visited;
	static int n,m, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m][2];
		result = -1;
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for (int j=0; j<m; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {0,0,0});
		visited[0][0][0] = 1; // 방문체크
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			
			if (now[0]==n-1 && now[1]==m-1) {
				result = visited[now[0]][now[1]][now[2]];
				break;
			}
			
			for(int i=0; i<4; i++) {
				int x = now[0]+dx[i];
				int y = now[1]+dy[i];
				int z = now[2];
				
				if (x<0 || y<0 || x>=n || y>=m || visited[x][y][z]!=0) continue;
				// 길이면 -> 0층 1층 구분없이 진행
				if (map[x][y]==0) {
					visited[x][y][z] += visited[now[0]][now[1]][now[2]]+1;
					que.add(new int[] {x,y,z});
				}
				// 벽인데 아직 벽을 뚫은 적이 없다면 -> 뚫고 1층으로 진행
				else if (map[x][y]==1 && z==0) {
					visited[x][y][1] += visited[now[0]][now[1]][now[2]]+1;
					que.add(new int[] {x,y,1});
				}
			}
		}
	}
}