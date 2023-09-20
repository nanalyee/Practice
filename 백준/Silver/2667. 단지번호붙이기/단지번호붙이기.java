import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static StringBuilder sb;
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1};
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> houseCount;
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		houseCount = new ArrayList<>(); 
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		} // end input
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==1 && !visited[i][j]) {
					houseCount.add(bfs(i, j));					
				}
			}
		}

		Collections.sort(houseCount);
		int total = houseCount.size();
		sb.append(total+"\n");
		for (int i=0; i<total; i++) {
			sb.append(houseCount.get(i)+"\n");
		}
		System.out.println(sb);
	}
	
	static int bfs(int a, int b) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {a,b});
		int count = 0;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowx = now[0];
			int nowy = now[1];
			for (int i=0; i<4; i++) {
				int x = nowx + dx[i];
				int y = nowy + dy[i];
				if (x<0 || y<0 || x>=N || y>=N) continue;
				if (map[x][y]!=0 && !visited[x][y]) {
					visited[x][y] = true;
					que.add(new int[] {x,y});
					count++;
				}
			}
		}
		if (count == 0) count = 1;
		return count;
	} 
}