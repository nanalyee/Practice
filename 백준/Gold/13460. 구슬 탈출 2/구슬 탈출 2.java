import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
//	직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
//	세로 크기는 N, 가로 크기는 M, 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나
//	구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 
//	왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능
//	각각의 동작에서 공은 동시에 움직인다. 
//	- 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
//	- 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 
//	- 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 
//	- 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다.
//	- 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
//	보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
	
//	[접근 방법]
//	1. 최단거리로 탈출 - bfs
//	2. 빨강, 파랑 구슬이 동시에 움직임 -> 빨강 파랑 위치 둘다 고려한 4차원 visited 배열
//	3. #을 만날 때까지 상우하좌 방향 이동(사방탐색)
//		-> 파랑이 구멍에 빠지면? continue
//		-> 빨강만 구멍에 빠지면? return
//	4. 둘 다 구멍에 빠지지 않았는데 이동 예정 위치가 같으면?
//		-> 이동 방향이 위쪽이였으면? 보다 아래에 있던 구슬을 한칸 아래로
//		-> 이동 방향이 오른쪽이였으면? 보다 왼쪽에 있던 구슬을 한칸 왼쪽으로
//		-> 이동 방향이 아래쪽이였으면? 보다 위쪽에 있던 구슬을 한칸 위쪽으로
//		-> 이동 방향이 왼쪽이였으면? 보다 오른쪽에 있던 구슬을 한칸 오른쪽으로
//	5. 이동 예정위치가 방문한 적이 없었다면 큐에 추가
//  6. 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1
	
	static int[] dx = {-1, 0, 1, 0}; // 상우좌하
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int holeX, holeY;
	static Bubble blue, red;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O') {
					holeX = i;
					holeY = j;
				} else if(map[i][j] == 'B') {
					blue = new Bubble(0, 0, i, j, 0);
				} else if(map[i][j] == 'R') {
					red = new Bubble(i, j, 0, 0, 0);
				}
			}
		}
		
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Bubble> que = new LinkedList<>();
		que.add(new Bubble(red.rx, red.ry, blue.bx, blue.by, 1));
		visited[red.rx][red.ry][blue.bx][blue.by] = true;
		
		while(!que.isEmpty()) {
			Bubble now = que.poll();
			int nowRx = now.rx;
			int nowRy = now.ry;
			int nowBx = now.bx;
			int nowBy = now.by;
			int nowCnt = now.cnt;
			
			if(nowCnt > 10) return -1; // 이동 횟수가 10 초과시 실패 
			
			
			
			
//			3. #을 만날 때까지 상우하좌 방향 이동(사방탐색)
			for (int d=0; d<4; d++) {
				int nextRx = nowRx;
				int nextRy = nowRy;
				int nextBx = nowBx;
				int nextBy = nowBy;
				
				boolean isRedHole = false;
				boolean isBlueHole = false;
				
				// 빨강
				while(map[nextRx + dx[d]][nextRy + dy[d]] != '#') { 
					nextRx += dx[d];
					nextRy += dy[d];
					
					if(nextRx == holeX && nextRy == holeY) { 
						isRedHole = true; // 빨강 구멍 빠짐
						break;
					}
				}
				// 파랑
				while(map[nextBx + dx[d]][nextBy + dy[d]] != '#') { 
					nextBx += dx[d];
					nextBy += dy[d];
					if(nextBx == holeX && nextBy == holeY) { 
						isBlueHole = true; // 파랑 구멍 빠짐
						break;
					}
				}
				
//				-> 파랑이 구멍에 빠지면? continue
				if (isBlueHole) continue;
//				-> 빨강만 구멍에 빠지면? return
				if(isRedHole && !isBlueHole) { 
					return nowCnt;
				}
				
//				4. 둘 다 구멍에 빠지지 않았는데 이동 예정 위치가 같으면?
				if (nextRx == nextBx && nextRy == nextBy) {
//					-> 이동 방향이 위쪽이였으면? 보다 아래에 있던 구슬을 한칸 아래로
					if (d==0) {
						if(nowRx > nowBx) nextRx -= dx[d]; 
						else nextBx -= dx[d];
					}
//					-> 이동 방향이 오른쪽이였으면? 보다 왼쪽에 있던 구슬을 한칸 왼쪽으로
					else if (d==1) {
						if(nowRy < nowBy) nextRy -= dy[d]; 
						else nextBy -= dy[d];
					}
//					-> 이동 방향이 아래쪽이였으면? 보다 위쪽에 있던 구슬을 한칸 위쪽으로
					else if (d==2) {
						if(nowRx < nowBx) nextRx -= dx[d];
						else nextBx -= dx[d];
					}
//					-> 이동 방향이 왼쪽이였으면? 보다 오른쪽에 있던 구슬을 한칸 오른쪽으로
					else {
						if(nowRy > nowBy) nextRy -= dy[d];
						else nextBy -= dy[d];
					}
				}
				
//				5. 이동 예정위치가 방문한 적이 없었다면 큐에 추가
				if (!visited[nextRx][nextRy][nextBx][nextBy]) {
					visited[nextRx][nextRy][nextBx][nextBy] = true;
					que.add(new Bubble(nextRx, nextRy, nextBx, nextBy, nowCnt+1));
				}
			}
		}
		
		return -1; // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1
	}

	static class Bubble {
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;
		public Bubble(int rx, int ry, int bx, int by, int cnt) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
}