import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 익은 토마토들이 안익은 토마토에 영향을 줌 -> 상하좌우
	// 며칠 후에 모두 다 익는지 구하기
	
	// 상하좌우
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int[][] map; // 토마토 창고
	static int[][] visited; // 창고 방문 여부
	static int M, N, result, count; // 상자크기, 정답, 방문 칸 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		map = new int[N][M];
		
		int cnt=0, total=0; // -1 수, 이전 횟수에서 익은 토마토 총 수
		boolean done = false; // 다 익었는지 체크
		
		// 맵 입력
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==-1) cnt++;
			}
		}		

		//printMap();
		
		while (true) {
			
			// 다 익었는지 검사
			int count=0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[i][j]!=0) count++;
				}
			}
			
			// 종료 조건
			if (total==count) {
				result=-1;
				break;
			}
			if (count==M*N) break; 
			
			total=count; // 이전 익은 토마토 수 저장
			
			// 익어가는 토마토
			tomato();
			//printMap();
			
		}
		
		//printVisited();
		System.out.println(result);
		
	}

	
	// 토마토를 검사하자
	


	// 토마토를 익히자
	private static void tomato() {
		visited = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		
		// 익은 토마토 큐에 추가, 방문처리
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==1) {
					que.add(new int[] {i,j});
					visited[i][j]++;
				}
			}
		}
		
		// 큐가 빌 때까지 노드 poll 
		while(!que.isEmpty()) {
			int now[] = que.poll();
			int nowX = now[0];
			int nowY = now[1];
		
			// 상하좌우
			for (int i=0; i<4; i++) {
				int x = nowX+dx[i];
				int y = nowY+dy[i];
				
				// 이동 가능하면 -> 방문처리, 익히기
				if( x<0 || y<0 || x>=N || y>=M ) continue;
				if( visited[x][y]==0 && map[x][y]==0 ) {
					que.add(new int[] {x,y});
					map[x][y]=1;
					visited[x][y]+=visited[nowX][nowY]+1;
					result = visited[x][y]-1;
					count++;
				}
			}
		}
		
	}
	
	
	// 진행 확인용 출력 함수
	private static void printMap() {
		
		System.out.printf("result%d------------------\n", result);
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	private static void printVisited() {
		
		System.out.println("------------------");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}