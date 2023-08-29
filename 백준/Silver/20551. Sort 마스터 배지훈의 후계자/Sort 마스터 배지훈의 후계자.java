import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		for (int i=0 ; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(A); // 오름차순 정렬

		for (int i=0 ; i<M; i++) {
			int D = Integer.parseInt(br.readLine());
			sb.append(SearchD(D, 0, N-1)+"\n"); // 이분탐색
		}
		
		System.out.println(sb);
	}
	
	public static int SearchD(int D, int low, int high) {
		int mid = 0; 
		while (low <= high) {
			mid = (low+high)/2;
			if (D == A[mid]) {
				int num = mid;
				while (true) {
					if (num>0 && A[num-1] == A[num]) {
						num--;
					}
					else {
						mid = num;
						break;
					}
				}
				return mid;				
			}
			else if (D < A[mid]) 
				high = mid - 1 ;
			else
				low = mid + 1;
		}
		return -1;
	}
}