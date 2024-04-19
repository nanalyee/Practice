import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 0 && b == 0 && c == 0) break;
			
			int max = Math.max(a, Math.max(b, c));
			int total = a+b+c;
			
			// 세 변의 길이가 모두 같은 경우
			if (a==b && b==c) sb.append("Equilateral"+"\n"); 
			
			// 삼각형 조건을 만족하지 않음
			else if (max >= total-max) {
				sb.append("Invalid"+"\n"); 
			}
			else {
				// 두 변의 길이만 같은 경우
				if (a == b || b == c || a == c) sb.append("Isosceles"+"\n"); 
				// 세 변의 길이가 모두 다른 경우
				else sb.append("Scalene"+"\n"); 
			}
		} 
		
		System.out.println(sb);
	}
}