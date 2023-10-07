import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0,-1,0,1}; // 우상좌하
	static int[] dy = {1,0,-1,0};
	static int N, K, L, dir, result;
	static int[][] map; // 1:뱀, 2:사과
	static Deque<int[]> snake;
	static boolean finish = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		map[0][0] = 1; // 뱀 시작 위치
		snake = new ArrayDeque<int[]>();
		snake.add(new int[] {0, 0});
		dir = 0; // 오른쪽 방향부터
		result = 0;
		
		K = Integer.parseInt(br.readLine());
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;
		}

		L = Integer.parseInt(br.readLine());
		for (int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken()) - result; // X초
			for (int j=0; j<time; j++) { // X초만큼 이동
				result++;
				Move();
				if (finish) break;
			}
			
			if (finish) break; // 게임오버 조건 확인
			
			// 방향전환
			if(st.nextToken().equals("L")) { // 왼쪽으로 90도 L
				dir++;
				if (dir>3) dir = 0;
			} else { // 오른쪽으로 90도 D
				dir--;
				if (dir<0) dir = 3;
			}
		}
		while(!finish) {
			result++;
			Move();
		}

		System.out.println(result);
	}

	public static void Move() {
		int[] now = snake.peekFirst(); // 머리
		int nx = now[0] + dx[dir];
		int ny = now[1] + dy[dir];
		// 벽 혹은 몸에 부딪히면 게임오버
		if (nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny]==1) {
			finish = true;
			return;
		}
		// 사과 없으면 꼬리 안늘어남
		if (map[nx][ny]!=2) {
			int[] tail = snake.pollLast(); // 마지막 제거하면서 리턴
			map[tail[0]][tail[1]] = 0;
		}
		map[nx][ny] = 1; // 이동
		snake.addFirst(new int[] {nx, ny}); // 머리에 추가
		//CheckStatus();
	}
	
	public static void CheckStatus() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------"+result+"초일때 방향:"+dir);
		System.out.println();
	}
}