import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args)  throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int M = Integer.parseInt(br.readLine());
		String command = "";
		int x = 0;
		Set<Integer> set = new HashSet<>();
		// 반복문과 다음과 같이 초기화 하는 법 둘다 성능적으로는 큰차이가 없겠지만
		// 처음보는 초기화 방법이라 시도해봄
		Set<Integer> allSet = new HashSet<>(Arrays.asList
				(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			command = st.nextToken();
			switch (command) {
				case "add":
					x = Integer.parseInt(st.nextToken());
					set.add(x);
					break;
				case "remove":
					x = Integer.parseInt(st.nextToken());
					set.remove(x);
					break;
				case "check":
					x = Integer.parseInt(st.nextToken());
					if (set.contains(x)) sb.append("1"+"\n");
					else sb.append("0"+"\n");
					break;
				case "toggle":
					x = Integer.parseInt(st.nextToken());
					if (set.contains(x)) set.remove(x);
					else set.add(x);
					break;
				case "all":
					set = new HashSet<>(allSet);
					break;
				case "empty":
					set.clear();
					break;
			}			
		}
		System.out.println(sb);
	}
}