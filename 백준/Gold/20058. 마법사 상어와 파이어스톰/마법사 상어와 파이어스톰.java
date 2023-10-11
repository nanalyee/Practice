import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] dx = {-1,1,0,0}; // 상하좌우
	public static int[] dy = {0,0,-1,1};
	public static int N, Q, L, size;
	public static int[][] map, rotateMap, iceMap;
	public static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		iceMap = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//printMap();
		
		// Q번 반복
		st = new StringTokenizer(br.readLine());
		for (int testcase = 0; testcase < Q; testcase++) {
			L = Integer.parseInt(st.nextToken());
			size = N / (int) Math.pow(2, L); // 단위 개수
			int unit = (int) Math.pow(2, L); // 단위 길이
			
			// 배열 돌리기
			rotateMap = new int[N][N];// 초기화
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
					FireStorm(i, j, unit);
				}
			}
			//printRotateMap();
			
			// 얼음 깨기
			iceMap = new int[N][N]; // 초기화
			IceBreak();
			
			// 맵 복사
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					map[i][j] = iceMap[i][j];
				}
			}
		}
		
		// 전체 얼음 수
		int total = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				total += map[i][j];
			}
		}
		System.out.println(total);
		
		// 얼음 덩어리
		int max = 0;
		visited = new boolean[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]>0 && !visited[i][j]) {
					max = Math.max(max, BfsIce(i,j));
				}
			}
		}
		System.out.println(max);
	}
	
	// 1. L 단계에 따라 배열 돌리기
	public static void FireStorm(int a, int b, int unit) {
		for (int i=0; i<unit; i++) {
			for (int j=0; j<unit; j++) {
				int x = (j + b*unit) % ((int) Math.pow(2, L)) + ((int) Math.pow(2, L) * a);
				int y = unit*(b+1) - i - 1;
				rotateMap[x][y] = map[i + a*unit][j + b*unit];
			}
		}
	}
	
	// 2. 상하좌우 인접한 얼음이 1개 이하인 칸은 -1
	public static void IceBreak() {
		// 맵 복사
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				iceMap[i][j] = rotateMap[i][j];
			}
		}
		// 브루트포스로 확인
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (iceMap[i][j]<=0) continue;
				int cnt = 0;
				for (int k=0; k<4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					if (x<0 || y<0 || x>=N || y>=N) continue;
					if (rotateMap[x][y]>0) cnt++; // 얼음이 있다!
				}
				if (cnt<=2) iceMap[i][j]--; // 탈락!
			}
		}
		//printIceMap();
	}
	
	// 남아있는 얼음 중 가장 큰 덩어리의 칸 수
	public static int BfsIce(int a, int b) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {a,b});
		visited[a][b] = true;
		int cnt = 1;

		while(!que.isEmpty()) {
			int[] now = que.poll();
			for (int i=0; i<4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				if (x<0 || y<0 || x>=N || y>=N) continue;
				if (map[x][y]<=0 || visited[x][y]) continue;
				que.add(new int[] {x, y});
				visited[x][y] = true;
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void printMap() {
		System.out.println("Map =============");
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void printRotateMap() {
		System.out.println("ROTATE Map =============");
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				System.out.print(rotateMap[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void printIceMap() {
		System.out.println("ICE Map =============");
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				System.out.print(iceMap[i][j]+" ");
			}
			System.out.println();
		}
	}
}