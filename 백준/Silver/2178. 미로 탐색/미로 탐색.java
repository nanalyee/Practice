import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new int[n+1][m+1];
		map = new int[n+1][m+1];
		
		// map 채우기
		for ( int i=1 ; i<=n ; i++ ) {
			String str = br.readLine();
			for ( int j=0  ; j<m ; j++ ) {
				map[i][j+1] = str.charAt(j)-'0';
			}
		}
		
		bfs(1,1);
		System.out.println(visited[n][m]);
	}
	
	
	public static void bfs(int a, int b) {
		
		// 큐 생성 후 시작점 추가와 방문처리
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {a,b});
		visited[a][b]=1;
		
		// 큐가 빌 때까지 노드 poll 
		while(!que.isEmpty()) {
			int now[] = que.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			//-> 상하좌우 확인 
			for ( int i=0 ; i<4 ; i++ ) {
				int x = nowX + dx[i];
				int y = nowY + dy[i];
				if ( x<=0 || y<=0 || x>n || y>m ) continue;
				
				// -> 이동 가능하면 해당 노드 추가와 방문처리 후 이동 
				if ( map[x][y]==1 && visited[x][y]==0 ) {
					que.add(new int[] {x,y});
					visited[x][y]=visited[nowX][nowY]+1;
				}
			}
		}
		
	}
}