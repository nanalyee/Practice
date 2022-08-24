import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 (BFS)
	// 이동 불가능하면 KAKTUS

	// 1. 고슴도치를 이동한다 (상하좌우)
	// -> 물'*'과 돌'X' 통과 X
	// -> 다음에 물이 퍼질 곳에는 가지 못한다. (해당 좌표 상하좌우 검사해 물이 있는지 확인!)
	// 2. 물과 인접한 곳에(상하좌우) 물을 채운다 ('.'을 '*'로 채우는 것)
	// -> 돌'X'과 비버소굴'D' 통과 X
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int R, C, endX, endY, result; // 크기, 비버굴 좌표, 최소 시간
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] watervisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		watervisited = new boolean[R][C];
//		endX = 0; endY = 0;
		result = 0;
		
		int startX = 0, startY = 0;
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
//				if (ch=='D') {
//					endX = i;
//					endY = j;
//				}
				if (ch=='S') {
					startX = i;
					startY = j;
					map[i][j] = '.';
				}
			}
		}
		//printmap();
		bfs(startX, startY);
		
		if (result==0) System.out.println("KAKTUS"); 
		else System.out.println(result); 
	}


	private static void bfs(int startX, int startY) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {startX, startY});
		visited[startX][startY] = true; // 방문 처리 위해 1부터 시작하기 때문에 결과는 -1 해줘야 함
		int cnt=0;
		while(!que.isEmpty()) {
			int size = que.size();
			water(); // 1. 물채우기
			
			for(int s=0; s<size; s++) {
				int[] now = que.poll();
				int nowx = now[0];
				int nowy = now[1];
				
				if (map[nowx][nowy]=='D') { // 비버 굴 도착!
					result = cnt;
					break;
				}
				
				//System.out.println(nowx +" " +nowy+" -----------");
				// 2. 고슴도치를 이동한다 (상하좌우)
				for (int d=0; d<4; d++) {
					int x = nowx+dx[d];
					int y = nowy+dy[d];
					
					if (x<0 || y<0 || x>=R || y>=C || visited[x][y]) continue; 
					// -> 물'*'과 돌'X'이 아니면 갈 수 있다.
					// -> 다음에 물이 퍼질 곳에는 가지 못한다. (해당 좌표 상하좌우 검사해 물이 있는지 확인!)
					if (map[x][y]=='.') {
						que.add(new int[] {x,y});
						visited[x][y] = true;
					}
					else if (map[x][y]=='D') {
						que.add(new int[] {x,y});
						visited[x][y] = true;
					}
				}
				//printvisited();
				//printmap();
			}
			
			cnt++;
		}
		
	}
	
	
//	// -> 다음에 물이 퍼질 곳에는 가지 못한다. (해당 좌표 상하좌우 검사해 물이 있는지 확인!)
//	private static boolean check(int a, int b) {
//		for (int d=0; d<4; d++) {
//			int x = a+dx[d];
//			int y = b+dy[d];
//			
//			if (x<0 || y<0 || x>=R || y>=C) continue; 
//			if (map[x][y]=='*') return false;
//		}
//		return true;
//	}


	// 2. 물과 인접한 곳에(상하좌우) 물을 채운다 ('.'을 '*'로 채우는 것)
	private static void water() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j]=='*' && !watervisited[i][j]) {
					watervisited[i][j] = true;
					for (int d=0; d<4; d++) {
						int x = i+dx[d];
						int y = j+dy[d];
						
						if (x<0 || y<0 || x>=R || y>=C) continue;
						if (map[x][y]=='.' && !watervisited[x][y]) {
							map[x][y] = '*';
							watervisited[x][y] = true;
						}
					}
				}
			}
			
		}
		
		// 방문 지도 초기화
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				watervisited[i][j] = false;
			}
		}
		
	}


	private static void printmap() {
		System.out.println("MAP ------------");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static void printvisited() {
		System.out.println("VISITED ------------");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
	}
}