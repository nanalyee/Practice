import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
//	< 1번 문제: 17144 미세먼지 안녕 >
	
//	1. 확산
//		(1) 모든 배열을 돌아 미세먼지가 있으면 que에 add
//		(2) 인접한 방향 (next)이 (범위 안 && !공기청정기) 
//			-> next에 A/5 (소수점 없음)만큼 확산 , now는 Ar,c - (Ar,c/5)×(확산된 방향의 개수)로 갱신
//	2. 순환 -> 공기청정기 확인해서 옮기자
//		(1) top 배열 돌리기 
//			-> 첫 칸을 start로 저장해 둘째칸을 첫칸으로 땡겨오기
//			-> 반복이 끝나면 마지막칸 end에 start 넣기
//		(2) bottom 배열 돌리기
//			-> 둘째칸부터 시작해서 옮기기
//			-> 반복이 끝나면 첫칸에 end 넣기
//	3. T초가 지나면 모든 반복 종료, 남아있는 미세먼지 양 계산해 출력
	
	static int R,C,T; // 범위, 시간
	static Point[] airfresh = new Point[2];
	static int map[][];
	static int clone[][];
	static Queue<Point> que = new LinkedList<>();
	
	// 우하좌상
	static int[] dx = {0,1,0,-1}; 
	static int[] dy = {1,0,-1,0}; 
	// 하우상좌
	static int[] newdx = {1,0,-1,0}; 
	static int[] newdy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		clone = new int[R][C];
		int result = 0; // 출력할 미세먼지 총 개수
		
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==-1) { // 공기청정기 좌표 저장
					airfresh[1] = new Point(i,j);
				}
			}
		} 
		airfresh[0] = new Point(airfresh[1].x -1,airfresh[1].y); // 공기청정기 좌표 저장
		//printMap(); 
		// end input
		
		
//		T초 동안 확산과 순환 
		for (int t=0 ; t<T; t++) {
			spread(); // 확산
			//System.out.println("확산 후 입니다");
			//printMap();
			circle(); // 순환
			//System.out.println("순환 후 입니다");
			//printMap();
		}

//		T초가 끝나면 미세먼지 개수를 모두 세자
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j]>0) result += map[i][j];
			}
		} 
		
		System.out.println(result);
		//end output
	}

	
//	1. 확산 ------------------------------------------------------------------------
	private static void spread() {
		
		// clone 복사
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				clone[i][j] = map[i][j];
				if (map[i][j]>0) que.add(new Point(i,j));
			}
		} 
		
		// bfs 시작
//		(1) 모든 배열을 돌아 미세먼지가 있으면 que에 add
		while(!que.isEmpty()) {
			Point now = que.poll();
			int nowDust = map[now.x][now.y];
			
			int cnt = 0; // 확산될 미세먼지 수 세기
			for (int d=0; d<4; d++) {
				int x = now.x + dx[d];
				int y = now.y + dy[d];
				
//				(2) 인접한 방향 (next)이 (범위 안 && !공기청정기) 
				if (x<0 || y<0 || x>=R || y>=C || map[x][y]==-1) continue;
//				-> next에 A/5 (소수점 없음)만큼 확산 , now는 Ar,c - (Ar,c/5)×(확산된 방향의 개수)로 갱신
				clone[x][y] += nowDust/5;
				cnt++;
			}
			if (cnt>0) 
				clone[now.x][now.y] -= (nowDust/5)*cnt; // 현재 미세먼지 수 갱신
		}
		
		// clone에서 수정된 값을 map에 갱신해주기
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
//				if (clone[i][j]>0) map[i][j] = clone[i][j];
				map[i][j] = clone[i][j];
			}
		} 
	}


//	2. 순환 -> 공기청정기 확인해서 옮기자  --------------------------------------------------
	private static void circle() {
		// clone 복사
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				clone[i][j] = map[i][j];
			}
		} 
		
//		(1) top 배열 돌리기 (시계 반대 방향)
		int x = 0;
		int y = 0;
		int start = clone[x][y]; // 첫번째 항 저장 이거 맵인지 클론인지 확인하자 디버깅할때!!
		for (int d=0; d<4; d++) {
			while(true) {
				int nextx = x + dx[d];
				int nexty = y + dy[d];
				if (nextx<0 || nexty<0 || nextx>airfresh[0].x || nexty>=C) break;
				//System.out.println(nextx+" "+nexty);
				
				clone[x][y] = clone[nextx][nexty]; // 땡겨오기
				x = nextx;
				y = nexty;
			}
		}
		clone[1][0] = start; // 마지막칸에 첫째칸 대입
		clone[airfresh[0].x][airfresh[0].y] = -1; // 공기청정기와 다음칸은 수정
		clone[airfresh[0].x][airfresh[0].y+1] = 0;
		
//		(2) bottom 배열 돌리기 (시계 방향)
		x = airfresh[1].x;
		y = airfresh[1].y;
		for (int d=0; d<4; d++) {
			while(true) {
				int nextx = x + newdx[d];
				int nexty = y + newdy[d];
				if (nextx<airfresh[1].x || nexty<0 || nextx>=R || nexty>=C) break;
				//System.out.println(nextx+" "+nexty);
				clone[x][y] = clone[nextx][nexty]; // 땡겨오기
				x = nextx;
				y = nexty;
			}
		}
		clone[airfresh[1].x][airfresh[1].y] = -1; // 공기청정기와 다음칸은 수정
		clone[airfresh[1].x][airfresh[1].y+1] = 0;
		
		// clone에서 수정된 값을 map에 갱신해주기
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				map[i][j] = clone[i][j];
			}
		} 
	}

	
	// 좌표 관리할 객체 생성
	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}


//	디버깅용 ---------------------------------------------------------------------------
	private static void printMap() {
		System.out.println("------------");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
}