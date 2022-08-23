import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	//static boolean[][] visited;
	static int I,endX,endY, result; // 크기,위치, 케이스별 최소 이동 횟수
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc=0; tc<T; tc++) {
			result=0;
			
			I = Integer.parseInt(br.readLine()); // 한변의 길이
			map = new int[I][I];
			//visited = new boolean[I][I];
			
			// 시작 좌표
			st = new StringTokenizer(br.readLine(), " ");
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			// 목표 좌표
			st = new StringTokenizer(br.readLine(), " ");
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			bfs(startX, startY);
			sb.append(result+"\n");
			//printmap();
		}
		
		System.out.println(sb);
	}

	private static void bfs(int a, int b) {
		if (endX==a && endY==b) return;

		int[] dx = {2,1,-1,-2,-2,-1,1,2};
		int[] dy = {1,2,2,1,-1,-2,-2,-1};
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {a,b});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowX = now[0];
			int nowY = now[1];
			if(nowX==endX && nowY==endY) {
				//System.out.println("exit");
				result = map[endX][endY];
				break;
			}
			
			for (int i=0; i<8; i++) {
				int x = nowX+dx[i];
				int y = nowY+dy[i];
				
				if (x<0 || y<0 || x>=I || y>=I || map[x][y]!=0) continue;
				map[x][y] = map[nowX][nowY] + 1;
				que.add(new int[] {x,y});
			}
		}
	}

	private static void printmap() {
		for (int i=0; i<I; i++) {
			for (int j=0; j<I; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}