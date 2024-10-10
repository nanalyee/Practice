import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		int[] totalSum = new int[N];
		totalSum[0] = A[0];
		for (int i=1; i<N; i++) {
			totalSum[i] = totalSum[i-1] + A[i];
		}

		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken()) -1;
			int R = Integer.parseInt(st.nextToken()) -1;
			int left = 0;
			int right = totalSum[R];
			if (L-1>=0) left = totalSum[L-1];
			sb.append(right-left+"\n");
		}
		
		System.out.println(sb);
	}
}