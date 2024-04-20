import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 지방 수
		int[] arr = new int[N]; // 예산 요청
		int sum = 0;
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			max = Math.max(max, arr[i]);
		}
		int M = Integer.parseInt(br.readLine()); // 총 예산
		
		
		// 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
		if (sum <= M) {
			System.out.println(max);
		}
		// 모든 요청이 배정될 수 없는 경우
		else {
			int min = 1;
			while(min < max-1) {
				int half = (min+max)/2;
				
				sum = 0; 
				for (int i=0; i<N; i++) {
					if (arr[i]<half) sum += arr[i];
					else sum += half;
				}
				
				// 예산 체크
				if (sum > M) max = half;
				else min = half;
			}
			System.out.println(min);
		}
	}
}