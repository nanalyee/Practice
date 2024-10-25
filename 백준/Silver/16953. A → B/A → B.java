import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long ans = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		makeB(A,B,1);
		System.out.println((ans == Long.MAX_VALUE)? -1 : ans);
	}
	
	public static void makeB(long a, long b, long cnt) {
		if (a == b) {
			if (cnt < ans) ans = cnt;
			return;
		}
		if (2*a <= b) makeB(2*a,b,cnt+1);
		if (10*a+1 <= b) makeB(10*a+1, b, cnt+1);
	}
}