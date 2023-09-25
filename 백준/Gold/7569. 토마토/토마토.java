import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dz = {0,0,0,0,-1,1};
	static int[][][] map;
	static int[][][] visited;
	static int m,n,h,result,count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 가로
		n = Integer.parseInt(st.nextToken()); // 세로
		h = Integer.parseInt(st.nextToken()); // 높이
		
		map = new int[n][m][h];
		visited = new int[n][m][h];
		result = 0;
		count=0; // 방문해야 할 토마토 수 체크 위함
		
		// 토마토 창고
		for (int k=0; k<h; k++) {
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<m; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k]==0) count++;
				}
			}
		}
		//printmap();
		
		tomato();
		//printvisited();
		
		if (count!=0) System.out.println(-1);
		else System.out.println(result);
	}

	private static void tomato() {
		Queue<int[]> que = new LinkedList<>();
		
		// 익은 토마토 큐에 넣기
		for (int k=0; k<h; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					if (map[i][j][k]==1) {
						que.add(new int[] {i,j,k});
					}
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowx = now[0];
			int nowy = now[1];
			int nowz = now[2];
			
			for (int i=0; i<6; i++) {
				int x = nowx+dx[i];
				int y = nowy+dy[i];
				int z = nowz+dz[i];
				
				if (x<0 || y<0 || z<0 || x>=n || y>=m || z>=h) continue;
				if (visited[x][y][z]==0 && map[x][y][z]==0) {
					que.add(new int[] {x,y,z});
					map[x][y][z]=1;
					visited[x][y][z] = visited[nowx][nowy][nowz]+1;
					result = visited[x][y][z];
					count--;
				}
			}
			
			//printvisited();
		}
	}

	private static void printmap() {
		System.out.println("map----------------------------");
		for (int k=0; k<h; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					System.out.print(map[i][j][k]+" ");
				}
				System.out.println();
			}
		}		
	}
	
	private static void printvisited() {
		System.out.println("visited----------------------------");
		for (int k=0; k<h; k++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					System.out.print(visited[i][j][k]+" ");
				}
				System.out.println();
			}
		}		
	}
}