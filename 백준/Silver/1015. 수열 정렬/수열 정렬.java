import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	// A = {2, 1, 3, 1};
	// P = {2, 0, 3, 1}; => A에서 B가 되기 위한 A의 인덱스 => 구할 것
	// B = {1, 1, 2, 3}; => 비내림차순
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[][] a = new int[n][2];
		int[] p = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i=0; i<n; i++) {
			a[i][0] = Integer.parseInt(st.nextToken()); // 값
			a[i][1] = i; // 인덱스
		}
		
		Arrays.sort(a, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0]; // 값을 기준으로 정렬
			}
		});
		
		for (int i=0; i<n; i++) {
			p[a[i][1]] = i; // p[a에 저장한 인덱스번호] = i 
		}
		for (int i=0; i<n; i++) {
			sb.append(p[i]+" ");
		}
		System.out.println(sb);
		
	}
}