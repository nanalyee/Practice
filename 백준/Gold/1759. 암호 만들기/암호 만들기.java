import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	// 최소 1개의 모음, 최소 2개의 자음
	// 오름차순
	
	static StringBuilder sb;
	static int L, C;
	static char[] arr, result;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		result = new char[L];
		select = new boolean[C];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<C; i++) {
			char ch = st.nextToken().charAt(0);
			arr[i] = ch;
		}
		Arrays.sort(arr);
		comb(0); 
		
		System.out.println(sb);
 	}
	
	private static void comb(int idx) {
		if (idx==L) {
			//System.out.println(Arrays.toString(result));
			// 모음 1개, 자음 2개 이상 검사
			int a=0; int b=0;
			for (int i=0; i<L; i++) {
				char ch = result[i];
				if (ch=='a'|| ch=='e'|| ch=='i'|| ch=='o'|| ch=='u') {
					a++;
				} else b++;
			}
			if (a>=1 && b>=2) {
				for (int i=0; i<L; i++) {
					sb.append(String.valueOf(result[i]));
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i=0 ; i<C; i++) { 
			if (idx!=0 && result[idx-1]>arr[i]) continue;
			if (select[i]) continue;
			select[i] = true;
			result[idx] = arr[i];
			comb(idx+1);
			select[i] = false;
		}
	}
	
}