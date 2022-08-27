import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static Point[] ladder, snake;
	static int N, M;
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ladder = new Point[N];
		snake = new Point[M];
		map = new int[101];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			ladder[i] = new Point(num, Integer.parseInt(st.nextToken()));
			map[num] = -1;
		}
		for (int i=0; i<M; i++) {	
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			snake[i] = new Point(num, Integer.parseInt(st.nextToken()));
			map[num] = -2;
		}
		
		bfs();
		System.out.println(map[100]);
	}
	
	
	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(1);
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for (int i=1; i<=6; i++) {
				int next = now+i;
				if (next>100) continue;
				if (map[next]==-1) {
					for (int j=0; j<N; j++) {
						if (next==ladder[j].x) {
							next=ladder[j].y;
						}
					}
				}
				if (map[next]==-2) {
					for (int j=0; j<M; j++) {
						if (next==snake[j].x) next=snake[j].y;
					}
				}
				if (map[next]!=0) continue;
				map[next] = map[now]+1;
				que.add(next);
			}
		}
		
	}

	static class Point {
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}