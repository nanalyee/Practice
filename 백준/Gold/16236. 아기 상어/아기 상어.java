import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N, size, eat, result;
	static int[][] map;
	static Point shark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		size = 2;
		eat = 0;
		result = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==9) { // 시작 좌표
					map[i][j] = 0;
					shark = new Point(i,j); 
				}
			}
		}
		
		while(true) {
			if(!babyShark()) break;
		}
		
		System.out.println(result);
	}

	
	private static boolean babyShark() {
		Queue<Point> que = new LinkedList<>();
		
		boolean[][] visited = new boolean[N][N]; // bfs마다 visited 초기화
		
		// 시작 좌표 add, 방문처리
		que.add(shark);
		visited[shark.x][shark.y] = true;
		
		int time = 0;
		while (!que.isEmpty()) {
			ArrayList<Point> fish = new ArrayList<>();
			int queSize = que.size(); // 초 단위로 끊어주기
			
			for (int s=0; s<queSize; s++) { // 초마다 반복
				Point now = que.poll();
				
				for (int d=0; d<4; d++) { // 사방탐색
					int x = now.x + dx[d];
					int y = now.y + dy[d];
					
					if (x<0 || y<0 || x>=N || y>=N || visited[x][y]) continue;
					if (size>map[x][y] && map[x][y]!=0) // 먹을 수 있는 물고기면
						fish.add(new Point(x,y)); // 물고기 리스트에 추가
					if (size==map[x][y] || map[x][y]==0) // 못먹지만 지나갈 수 있으면
						que.add(new Point(x,y)); // 큐에 추가
					visited[x][y] = true; // 방문처리
				}
			} // end 1초
			time++; // 1초 지남
		
			// 우선순위 물고기 찾기
			if (fish.size()>0) {
				Collections.sort(fish);
				Point getcha = fish.get(0);
				
				// 잡아먹는다
				map[getcha.x][getcha.y] = 0; 
				eat++;
				if (size==eat) {
					size++;
					eat = 0;
				}
				
				result += time;
				shark = new Point (getcha.x, getcha.y);
				return true;
			}
			
		} // end bfs
		return false; // 물고기 못잡았다
	}
	
	
	// 좌표 객체
	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x==o.x) return this.y-o.y;
			return this.x-o.x;
		}
	}
}