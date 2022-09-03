import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int a = 0, b=0, sum = 0;
		int result = Integer.MAX_VALUE;
		while (a<=b && b<=N) {
			if (sum < S) 
				sum+=arr[b++]; // 오른쪽 포인터 옮김
			else {
				result = Math.min(result, b-a); // 가장 길이가 짧은 것
				sum -= arr[a++]; // 왼쪽 포인터 옮김
			}
		}
		
		if (result==Integer.MAX_VALUE) System.out.println("0");
		else System.out.println(result);
	}
}