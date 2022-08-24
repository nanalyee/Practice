import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, result;
	static int[] numbers, arr;
	static boolean[] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		result = 0;
		numbers = new int[n];
		arr = new int[n];
		used = new boolean[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		//System.out.println(Arrays.toString(numbers));
		
		perm(0);
		System.out.println(result);
	}
	
	
	// 순열 arr 생성
	private static void perm(int idx) {
		if (idx==n) {
			//System.out.println(Arrays.toString(arr));
			result = Math.max(makeHalf(), result);
			return;
		}
		
		for (int i=0; i<n; i++) {
			if (used[i]) continue;
			used[i] = true;
			arr[idx] = numbers[i];
			perm(idx+1);
			used[i] = false;
		}
	}

	// 중심 지나는 선 (반으로 쪼개는) 확인
	private static int makeHalf() {
		int cnt=0;
		for (int i=0; i<n; i++) {
			int sum = 0;
			for (int j=i; j<n; j++) {
				sum += arr[j];
				if (sum==50) { // 합이 50이면 cnt+1 후 종료
					cnt++;
					break;
				}
				if (sum>50) break; // 합이 50을 넘으면 더하기 종료
			} 
		}
		//System.out.println(cnt-1);
		return cnt-1;
	}
}