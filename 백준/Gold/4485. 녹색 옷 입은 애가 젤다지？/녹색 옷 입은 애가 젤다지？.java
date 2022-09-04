import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = 0;
		int N = 0;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N==0) break;
			
			int map[][] = new int[N][N];
			int visited[][] = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			
			
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(map[0][0],0,0));
			visited[0][0] = map[0][0];
			
			while(!pq.isEmpty()) {
				Point now = pq.poll();
				
				for(int d=0; d<4; d++) {
					int x = now.x + dx[d];
					int y = now.y + dy[d];
					
					if (x<0 || y<0 || x>=N || y>=N) continue;
					int total = map[x][y]+visited[now.x][now.y];
					if (visited[x][y] > total) {
						visited[x][y] = total;
						pq.add(new Point(total,x,y));
					}
				}
			}
			
			tc++;
			sb.append("Problem "+tc+": "+visited[N-1][N-1]+"\n");
		}
		
		System.out.println(sb);
	}
	
	
	static class Point implements Comparable<Point>{
		int weight,x,y;
		public Point(int weight, int x, int y) {
			super();
			this.weight = weight;
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return this.weight - o.weight;
		}
		
	}
}