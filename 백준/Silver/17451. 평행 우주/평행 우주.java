import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// n (1 ≤ n ≤ 3·10^5)
	// 1 ≤ i ≤ n에 대해 1 ≤ vi ≤ 10^9 -> long 사용
	// 지구 n 에서 올려야 하는 속도 최소화
	
	// [풀이] => 그리디
	// 1. 맨 마지막 행성부터 시작, 거꾸로 탐색 (마지막 행성에서는 그 속도와 같은 값이면 됨)
	// 2. 현재 속도보다 큰 값이면? 갱신
	// 3. 현재 속도보다 작은 값이면?
	//    - 현재 속도가 i의 양의 배수이면? continue
	//    - 현재 속도가 i의 양의 배수가 아니면? 현재 속도보다 크면서 i의 양의 배수로 만들기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) {
			arr[i] = Long.parseLong(st.nextToken()); // 행성 배열
		} // end input
		
		long vel = arr[n-1]; // 마지막 행성에서부터 시작
		for (int i=n-2; i>=0; i--) { // 거꾸로 탐색
			if (vel < arr[i]) vel = arr[i];  // 현재보다 크면 갱신
			else if (arr[i]<vel && vel%arr[i]!=0) vel = ((vel/arr[i])+1) * arr[i]; // 배수로 만들기
  		}
		
		System.out.println(vel);
	}
}