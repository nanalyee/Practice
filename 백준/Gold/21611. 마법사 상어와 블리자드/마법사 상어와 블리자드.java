import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 가장 왼쪽 윗 칸: (1, 1), 가장 오른쪽 아랫 칸: (N, N), 마법사 상어: ((N+1)/2, (N+1)/2)
	// 처음에 상어가 있는 칸을 제외한 나머지 칸에는 구슬 
	//   - 구슬은 1번 구슬, 2번 구슬, 3번 구슬
	//   - 연속하는 구슬: 같은 번호를 가진 구슬이 번호가 연속하는 칸에 있으면
	// 4가지 방향 ↑, ↓, ←, →가 있고, 정수 1, 2, 3, 4
	
	static public int[] dx = {-1,1,0,0}; // 파괴: ↑, ↓, ←, →
	static public int[] dy = {0,0,-1,1};
	static public int[] bx = {1,0,-1,0}; // 블리자드: ↓, →, ↑, ← 
	static public int[] by = {0,1,0,-1};

	static int N, d, s, boom1, boom2, boom3;
	static int[][] map;
	static Bubble[] bubbles;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		int M = Integer.parseInt(st.nextToken()); // 블리자드 시전 횟수
		map = new int[N][N];
		bubbles = new Bubble[N*N-1];
		boom1 = 0;
		boom2 = 0;
		boom3 = 0;
		d = 0; s = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//printMap("입력 테스트 ");
		
		MakeLine(); // 번호 순서대로 1차원으로 만들기
		
		// 블리자드 M번 시전
		for (int testcase=0; testcase<M; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			d = Integer.parseInt(st.nextToken())-1; // 방향
			s = Integer.parseInt(st.nextToken()); // 거리
			
			Destroy(); // 파괴
			while(true) {
				while(true) {
					if (!isEmpty()) break; // 빈칸이 없으면 이동 반복 종료
					Move(); // 빈칸 채우기
				}
				if (!Explode()) break; // 폭발할 곳 없으면 전체 반복 종료
			}
			Change(); // 변화
		}
		
		// 출력: 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)
		System.out.println(boom1+2*boom2+3*boom3);
	}
	
	// 구슬 1차원 배열에 번호 순서대로 담기
	public static void MakeLine() {
		int idx = 0;
		for (int size=1; size<=N/2; size++) { // N=7 -> 1, 2, 3
			int[] now = {(N+1)/2-1 -size, (N+1)/2-1 -size}; // 초기값을 한칸 위로 해버림
			for (int dir=0; dir<4; dir++) { // 4방향을 돕니다
				for (int j=0; j<size*2; j++) {
					now = new int[] {now[0] + bx[dir], now[1] + by[dir]};
					bubbles[idx] = new Bubble(now[0], now[1]);
					idx++;
				}
			}
		}
	}
	
	// 1. 상어는 di 방향으로 거리가 si 이하인 칸에 있는 구슬을 모두 파괴 => 빈 칸
	//    - 벽은 파괴되지 않지만, 벽에 상관없이 파괴 (막히지 않음).
	public static void Destroy() {
		int nowx = (N+1)/2-1;
		int nowy = (N+1)/2-1;
		for (int i=0; i<s; i++) {
			int x = nowx + dx[d];
			int y = nowy + dy[d];
			map[x][y] = 0;
			nowx = x;
			nowy = y;
		}
		//printMap("파괴 완료");
	}
	
	
	// 2. (1) A의 번호보다 번호가 하나 작은 칸이 빈 칸이면, A에 있는 구슬은 그 빈 칸으로 이동
	//        - 더 이상 구슬이 이동하지 않을 때까지 반복
	public static void Move() {
		for (int i=0; i<bubbles.length-2; i++) {
			int nowx = bubbles[i].x;
			int nowy = bubbles[i].y;
			int nextx = bubbles[i+1].x;
			int nexty = bubbles[i+1].y;
			if (map[nowx][nowy]==0) {
				map[nowx][nowy] = map[nextx][nexty];
				map[nextx][nexty] = 0;
			}
		}
		//printMap("이동 완료");
	}
	
	// 더 이동 가능한지 확인
	public static boolean isEmpty() {
		boolean empty = false;
		for (int i=0; i<bubbles.length-1; i++) {
			int nowx = bubbles[i].x;
			int nowy = bubbles[i].y;
			// 이미 0을 만난적이 있는데 다를 수를 만났다 => 이동할 곳이 더 있다.
			if (empty && map[nowx][nowy]!=0) return true; 
			if (!empty && map[nowx][nowy]==0) empty = true; // 0을 만났음
		}
		return false; // 종료
	}
	
	// 2. (2) 폭발하는 구슬은 4개 이상 연속하는 구슬이 있을 때 발생
	//     - (1)번으로 빈칸을 채우고, (2)번으로 폭발. 폭발하는 구슬이 없을때까지 반복
	public static boolean Explode() {
		boolean isExplode = false;
		int nowx = bubbles[0].x;
		int nowy = bubbles[0].y;
		int cnt = 1;
		int pre = map[nowx][nowy];
		for (int i=1; i<bubbles.length-1; i++) {
			nowx = bubbles[i].x;
			nowy = bubbles[i].y;
			if (pre == map[nowx][nowy]) {
				cnt++;
			}
			else {
				if (cnt >= 4) {
					for (int j=i-cnt; j<i; j++) {
						int num = map[bubbles[j].x][bubbles[j].y];
						if (num == 1) boom1++;
						if (num == 2) boom2++;
						if (num == 3) boom3++;
 						map[bubbles[j].x][bubbles[j].y] = 0;
						isExplode = true;
					}
				}
				cnt = 1;
			}
			pre = map[nowx][nowy];
		}
		//printMap("폭발"+isExplode);
		return isExplode;
	}
	
	
	// 3. 구슬이 변화
	//    - 연속하는 구슬은 하나의 그룹 => 하나의 그룹은 두 개의 구슬 A와 B로 변한다
	//    - 구슬 A의 번호는 그룹에 들어있는 구슬의 개수, B는 그룹을 이루고 있는 구슬의 번호
	//    - 구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우 그러한 구슬은 사라진다
	public static void Change() {
		int nowx = bubbles[0].x;
		int nowy = bubbles[0].y;
		int cnt = 1;
		int pre = map[nowx][nowy];
		int[] newBubbles = new int[bubbles.length];
		int idx = 0;
		for (int i=1; i<bubbles.length-1; i++) {
			nowx = bubbles[i].x;
			nowy = bubbles[i].y;
			if (pre == map[nowx][nowy]) {
				cnt++;
			}
			else {
				//System.out.println(cnt+" "+pre);
				if (idx<newBubbles.length) {
					newBubbles[idx++] = cnt;
					newBubbles[idx++] = pre;					
				}
				cnt = 1;
			}
			pre = map[nowx][nowy];
		}
		
		for (int i=0; i<bubbles.length-1; i++) {
			map[bubbles[i].x][bubbles[i].y] = newBubbles[i];
		}
		//printMap("변화 완료");
	}
	

	
	
	public static void printMap(String str) {
		System.out.println(str+"==============");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
	
	public static class Bubble {
		int x, y;
		Bubble(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}