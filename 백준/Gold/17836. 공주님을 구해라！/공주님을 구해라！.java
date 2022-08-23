import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m][2];
		result = Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		if (result<=t) System.out.println(result);
		else System.out.println("Fail");
	}

	private static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {0,0,0});
		visited[0][0][0] = 1; // 방문체크
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			
			if (now[0]==n-1 && now[1]==m-1) {
				result = visited[now[0]][now[1]][now[2]] -1;
				//System.out.println(result);
				break;
			}
			
			for (int i=0; i<4; i++) {
				int x = now[0]+dx[i];
				int y = now[1]+dy[i];
				int z = now[2];
				
				if (x<0 || y<0 || x>=n || y>=m || visited[x][y][z]!=0) continue;
				
				// 길이거나 그람을 가졌다면 구분 없이 이동
				if (map[x][y]==0 || z==1)  { 
					//System.out.println(x+" "+y+" "+ map[x][y]);
					visited[x][y][z] += visited[now[0]][now[1]][now[2]]+1;
					que.add(new int[] {x,y,z});
				}
				// 그람을 지금 가진자 -> 1층으로 시뮬레이션
				else if (map[x][y]==2) { 
					visited[x][y][0] += visited[now[0]][now[1]][now[2]]+1;
					visited[x][y][1] += visited[now[0]][now[1]][now[2]]+1;
					que.add(new int[] {x,y,1});
				}
			}
		}
	}
}