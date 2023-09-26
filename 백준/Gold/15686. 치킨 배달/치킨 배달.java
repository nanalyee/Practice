import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 집을 기준으로 가까운 치킨집 까지의 거리 = 치킨거리
	// 모든 치킨거리의 합 = 도시의 치킨거리
	// 치킨집 중 최대 m개를 고르고 
	// 도시의 치킨거리 최솟값 출력
	
	static int[][] map, chicken;
	static boolean[] selected;
	static int[] arr; 
	static int n, m, chickenMax, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		chicken = new int[13][2]; // 모든 치킨집 수 = 가능한 최대 치킨집 수 경우로 만들어줌
		arr = new int[m]; // 선택된 치킨집 배열
		chickenMax=0; // 실제 치킨집 수
		result = Integer.MAX_VALUE;
		
		// 맵 입력
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) { // 치킨집 배열에 저장해둠
					chicken[chickenMax][0] = i;
					chicken[chickenMax][1] = j;
					chickenMax++;
				}
			}
		}
		//System.out.println(chickenMax);
		selected = new boolean[chickenMax];
		// 살릴 치킨집 최대 m까지 조합 구하기
		comb(0,0);
		
		System.out.println(result);
	}

	private static void comb(int idx, int start) {
		if (idx==m) {
			//System.out.println(Arrays.toString(arr));
			result = Math.min(result, road());
			return;
		}
		
		for (int i=start; i<chickenMax; i++) {
			if (selected[i]) continue;
			arr[idx] = i;
			selected[i] = true;
			comb(idx+1, i+1);
			selected[i] = false;
		}
	}

	private static int road() {
		int sum=0; // 치킨 거리의 합
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (map[i][j]==1) { // 집이면
					int chickenRoad = Integer.MAX_VALUE;
					for (int c=0; c<m; c++) { // 고른 치킨집들과 거리 구하기
						int num = Math.abs(i-chicken[arr[c]][0]) + Math.abs(j-chicken[arr[c]][1]);
						chickenRoad = Math.min(chickenRoad, num);
					}
					sum += chickenRoad;
				}
			}
		}
		//System.out.println(sum);
		return sum;
	}
}