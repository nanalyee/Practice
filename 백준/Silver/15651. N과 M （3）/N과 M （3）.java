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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M+1];
		
		perm(1);
		
		System.out.println(sb);
	}

	private static void perm(int idx) {
		if (idx>M) {
			for (int i=1; i<=M; i++) {
				sb.append(arr[i]+" ");
			}
				
			sb.append("\n");
			return;
		}
		
		for (int i=1; i<=N; i++) {
			arr[idx] = i;
			perm(idx+1);
		}
	}
	
	
}