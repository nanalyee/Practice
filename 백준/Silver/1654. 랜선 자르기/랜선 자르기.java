import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수 K
		int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수 N
		
		int[] arr = new int[K];
		long min = 1; // 범위 최소값
		long max = 0; // 범위 최대값
		for (int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i]>max) max = arr[i];
		}
		long mid;
		int sum;
		long res = 1;
		while(min<=max) {
			mid = (min+max)/2;
			sum = 0;
			for (int i=0; i<K; i++) {
				sum += (arr[i] / mid);
			}
			if (sum >= N) {
				if (res<mid) res = mid;
				min = mid + 1;
			}
			else if (sum < N) {
				max = mid - 1;
			}
		}
		System.out.println(res);
	}
}