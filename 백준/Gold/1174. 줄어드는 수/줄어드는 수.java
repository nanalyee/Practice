import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int n, cnt;
	static long result; // 최대 9876543210
	static boolean end;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		result = 0; 
		arr = new int[10]; // 최대 9876543210 = 10자리
		end = false;
		
		// 10보다 작으면 dfs 돌리지 않음
		if (n<=10) result = n-1; 
		
		// 최대 1023번째까지만 가능함
		else if (n>1023) result = -1;
		
		// 자리수를 늘려가며 순열 진행
		else {
			cnt = 10;
			int size = 2;
			while (!end) { // n번째가 되지 않으면 size 증가
				perm(0, size);
				size++;
			}
			
		}
		
		System.out.println(result);
	}

	
	// 줄어드는 수 순열
	private static void perm(int idx, int size) {
		
		if (idx==size) { // 사이즈만큼 배열 완성
			//System.out.println(cnt);
			//System.out.println(Arrays.toString(arr));
			cnt++;
			if (cnt==n) { // 정답 배열
				//System.out.println("end");
				//System.out.println(Arrays.toString(arr));
				for (int i=0; i<size; i++) {
					result += arr[i]*Math.pow(10, size-i-1);
				}
				end = true;
			}
			return;
		}

		for (int i=0; i<=9; i++) {
			if (end) break;
			// 0번째 자리가 0이면 안됨
			if (idx==0) {
				if (i==0) continue;
			}
			// 줄어드는 수로 배열을 만들어야 함
			else if (arr[idx-1]<=i) continue;
			arr[idx] = i;
			perm(idx+1, size);
		}
	}
}