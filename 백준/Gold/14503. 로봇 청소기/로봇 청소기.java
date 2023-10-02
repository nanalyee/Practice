import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {-1, 0, 1, 0}; // 북동남서 -> 상우하좌
	static int[] dy = {0, 1, 0, -1};
	static int N, M, r, c, d, result;
	static int[][] map;
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//CheckMap();
		
		result = 0;
		CheckNowRoom(r,c);
		System.out.println(result);
	}
	

	
	public static void CheckMap() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println(result+"--------------");
	}
	
	public static void Clean(int a, int b) {
		map[a][b] = 2;
		result++;
		//CheckMap();
	}
	
	public static void CheckNowRoom(int a, int b) {
		if (map[a][b]==0) { // 더러우면
			Clean(a,b); // 청소
		}
		CheckAround(a, b); // 4방향을 탐색하자
	}
	
	public static void CheckAround(int a, int b) {
		boolean needClean = false;
		for (int i=0; i<4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			if (map[x][y] == 0) { // 청소되지 않은 칸이 있으면
				needClean = true;
			}
		}
		
		if (needClean) {
			d = (d+3)%4; // 반시계 방향으로 90도 회전
			int nextx = a + dx[d]; // 바라보는 방향으로 전진했을 때
			int nexty = b + dy[d];
			if (map[nextx][nexty]==0) { // 전진할 방이 더러우면
				CheckNowRoom(nextx, nexty); // 전진
			}
			else {
				CheckNowRoom(a,b); // 아니면 1번으로 돌아가
			}
		} 
		else { // 청소되지 않은 빈칸이 없으면
			int x = a-dx[d]; // 후진
			int y = b-dy[d];
			if (map[x][y]==1) { // 후진한 곳이 벽이면
				//System.out.println("벽입니다");
				return; // 종료
			}
			CheckNowRoom(x, y); // 이동한 곳이 더러운지 체크한다
		}
	}
}