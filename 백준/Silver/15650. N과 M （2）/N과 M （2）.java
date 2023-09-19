import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	static int N,M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M+1];
		
		comb(1,1);
		
		System.out.println(sb);
	}

	private static void comb(int idx, int start) {
		// 종료 조건 
		if (idx>M) {
			for(int i=1; i<=M; i++) 
				sb.append(arr[i]+" ");
			sb.append("\n");
			return;
		}
		
		// 반복
		for (int i=start; i<=N; i++) {
			arr[idx]=i;
			comb(idx+1, i+1);
		}
	}
}