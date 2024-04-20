import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] map;
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static int M, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 0;
		map = new int[M][N];
		
		for (int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int i=x1; i<x2; i++) {
				for (int j=y1; j<y2; j++) {
					map[j][i] = 1;
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==0) {
					list.add(bfs(i, j));
					answer++;
				}
			}
		}
		Collections.sort(list);
		
		sb.append(answer+"\n");
		for (int i=0; i<list.size(); i++) {
			sb.append(list.get(i)+" ");
		}
		System.out.println(sb);
	}
	
	public static int bfs(int a, int b) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {a, b});
		map[a][b] = 2; // 방문
		int sum = 1;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			for (int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (nx<0 || ny<0 || nx>=M || ny>=N) continue;
				if (map[nx][ny]>0) continue;
				map[nx][ny] = 2; // 방문
				que.add(new int[] {nx, ny});
				sum++;
			}
		}
		
		return sum;
	}
}