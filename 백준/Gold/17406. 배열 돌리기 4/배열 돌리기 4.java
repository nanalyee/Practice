import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map, arr, rotations;
	static int n,m,k, result;
	static int[] order;
	static boolean[] used;
	
	static int[] dx = {1,0,-1,0}; //하우상좌
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		k = Integer.parseInt(st.nextToken()); // 연산 개수
		map = new int[n][m];
		arr = new int[n][m];
		rotations = new int[k][3];
		order = new int[k];
		used = new boolean[k];
		result = Integer.MAX_VALUE;
		
		// 배열 원본 입력
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연산 배열
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine()," ");
			rotations[i][0] = Integer.parseInt(st.nextToken()); // 중심x
			rotations[i][1] = Integer.parseInt(st.nextToken()); // 중심y
			rotations[i][2] = Integer.parseInt(st.nextToken()); // 반지름
		}
		
		perm(0);
		
		System.out.println(result);
	}

	private static void perm(int idx) {
		if (idx==k) {
			//System.out.println(Arrays.toString(order));
			// 배열 복사
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					arr[i][j] = map[i][j];
				}
			}
			// 회전
			for (int i=0; i<k; i++) {
				rotate(order[i]);
			}
			// 최소값
			result = Math.min(value(), result);
			return;
		}
		
		for (int i=0; i<k; i++) {
			if (used[i]) continue;
			used[i] = true;
			order[idx] = i;
			perm(idx+1);
			used[i] = false;
		}
	}


	// 회전
	private static void rotate(int cmd) {
		int r = rotations[cmd][0]-1; // 중심x
		int c = rotations[cmd][1]-1; // 중심y
		int s = rotations[cmd][2]; // 반지름
		//System.out.println(r+" "+c+" " +s);
		// 반지름 만큼 반복
		for (int round=s; round>0; round--) {
			int nowx = r-round; // 시작지점
			int nowy = c-round;
			int start = arr[nowx][nowy]; // 시작지점 저장
			
			int dir=0;
			while (dir<4) {
				int x = nowx + dx[dir];
				int y = nowy + dy[dir];
				
				if (x<r-round || x>r+round || y<c-round || y>c+round) dir++;
				else {
					arr[nowx][nowy] = arr[x][y];
					nowx=x; nowy=y;
				}

			}
			
			arr[r-round][c-round+1] = start; // 마지막에 시작지점 대입
		}
		
		//printArr();
	}


	// 배열값
	private static int value() {
		int min = Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int sum=0;
			for (int j=0; j<m; j++) {
				sum += arr[i][j];
			}
			//System.out.println(sum);
			min = Math.min(min, sum);
		}
		//System.out.println(min);
		return min;
	}
	
	
	// 배열 확인용 함수
	private static void printArr() {
		System.out.println();
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}