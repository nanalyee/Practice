import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Point> area;
	static int[][] map, clone;
	static int N, M, result;
	static int[] wall;
	static boolean[] check;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = new ArrayList<>();
		map = new int[N][M];
		clone = new int[N][M];
		wall = new int[3];
		check = new boolean[3];
		visited = new boolean[N][M];
		result = Integer.MIN_VALUE;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==0) {
					area.add(new Point(i,j));
				}
			}
		}
		//System.out.println(area.size());
		perm(0, 0);
		System.out.println(result);
	}
	
	private static void perm(int idx, int start) {
		if (idx==3) {
			//System.out.println(Arrays.toString(wall));
			visited = new boolean[N][M];
			result = Math.max(result, virus());
			//System.out.println(result);
			return;
		}
		
		for (int i=start; i<area.size(); i++) {
			if (check[idx]) continue;
			check[idx] = true;
			wall[idx] = i;
			perm(idx+1, i+1);
			check[idx] = false;
		}
		
	}

	private static int virus() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				clone[i][j] = map[i][j];
			}
		}
		
		for (int i=0; i<3; i++) {
			//System.out.println(area.get(wall[i]).x+" "+area.get(wall[i]).y);
			clone[area.get(wall[i]).x][area.get(wall[i]).y]=1;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visited[i][j] && clone[i][j]==2) bfs(i,j);
			}
		}
		
		int count = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				//System.out.print(clone[i][j]+" ");
				if (clone[i][j]==0) count++;
			}
			//System.out.println();
		}
		//System.out.println("-----");
		return count;
	}

	private static void bfs(int a, int b) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(a,b));
		visited[a][b] = true;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			for (int d=0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (nx<0 || ny<0 || nx>=N || ny>=M) continue;
				if (visited[nx][ny] || clone[nx][ny]!=0) continue;
				que.add(new Point(nx,ny));
				visited[nx][ny] = true;
				clone[nx][ny] = 2;
			}
		}
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}