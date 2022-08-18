import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] map, visited;
	static int[][] ladder, snake;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[101];
		visited = new int[101];
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ladder = new int[n][2];
		snake = new int[m][2];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[i][0] = Integer.parseInt(st.nextToken());
			ladder[i][1] = Integer.parseInt(st.nextToken());
			map[ladder[i][0]] = -1;
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			snake[i][0] = Integer.parseInt(st.nextToken());
			snake[i][1] = Integer.parseInt(st.nextToken());
			map[snake[i][0]] = -2;
		}
		//System.out.println(Arrays.toString(map));
		bfs();
		System.out.println(visited[100]);
	}


	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(1);
		
		while(!que.isEmpty()) {
			int node = que.poll();
			if (node==100) break;
			
			for (int i=1; i<=6; i++) {
				int next = node+i;
				if (next>100 || visited[next]>0) continue;
				
				if (map[next]==-1) {
					for (int j=0; j<ladder.length; j++) {
						if (ladder[j][0] == next && visited[ladder[j][1]]==0 ) {
							que.add(ladder[j][1]);
							visited[ladder[j][1]] = visited[node]+1;
							break;
						}
					}
				}
				else if (map[next]==-2) {
					for (int j=0; j<snake.length; j++) {
						if (snake[j][0] == next && visited[snake[j][1]]==0 ) {
							que.add(snake[j][1]);
							visited[snake[j][1]] = visited[node]+1;
							break;
						}
					}
				}
				else {
					que.add(next);
					visited[next] = visited[node]+1;
				}
			}
			
			
			//System.out.println(Arrays.toString(visited));
		}
	} 
}