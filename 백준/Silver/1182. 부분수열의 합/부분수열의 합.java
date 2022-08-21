import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, s, result;
	static int[] numbers;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		result=0;
		numbers = new int[n];
		selected = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		subset(0, 0);
		
		System.out.println(result);
	}

	private static void subset(int idx, int sum) {
		if (idx==n) {
			int total=0;
			boolean check = false; 
			for (int i=0; i<n; i++) {
				if (selected[i]) {
					total = total + numbers[i];
					check = true;
				}
			}
			if (total==s && check) result++;
			return;
		}
		
		selected[idx] = true;
		subset(idx+1, sum+numbers[idx]);
		selected[idx] = false;
		subset(idx+1, sum);
	}
}