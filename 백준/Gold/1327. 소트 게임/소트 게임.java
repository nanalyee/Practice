import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n]; // 초기 배열
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // end input
		
		// 배열을 문자열로 다루기
		String originStr = ""; // 원본 배열 저장할 문자열
		for (int i=0; i<n; i++) {
			originStr += arr[i];
		}
		Arrays.sort(arr);
		String sortStr = ""; // 정렬한 배열 저장할 문자열
		for (int i=0; i<n; i++) {
			sortStr += arr[i];
		}
		//System.out.println(sortStr);
		
		System.out.println(bfs(originStr, sortStr));

	}
	
	private static int bfs(String originStr, String sortStr) {
		// bfs로 모든 경우를 나눠서 생각해보자
		// sortStr을 만나면 종료! 그때의 횟수가 최소!
		Queue<pair> que = new LinkedList<pair>();
		HashSet<String> hs = new HashSet<>(); // 방문체크용 
		que.add(new pair(originStr, 0));
		while(!que.isEmpty()) {
			pair now = que.poll();
			String nowStr = now.s;
			int nowCnt = now.cnt;
			//System.out.println(nowStr);
			if (nowStr.equals(sortStr)) return nowCnt;
			if (!hs.contains(nowStr)) {
				hs.add(nowStr);
				for(int i=0; i<=n-k; i++) {
					String nextStr = reverseString(nowStr, i, i+k);
					que.add(new pair(nextStr, nowCnt+1));
				}
			}

		}
		return -1;
	}

	private static String reverseString(String nowStr, int i, int j) {
		StringBuilder sb = new StringBuilder();  // 문자열 하나로 합치기 위해 사용
		sb.append(nowStr.substring(0,i)); // 0~i까지 그대로 넣기
		String revStr = nowStr.substring(i,j); // i~j까지 뒤집을 준비
		for (int a = k-1; a>=0; a--) {
            sb.append(revStr.charAt(a)); // 뒤집어 넣기
        }
		sb.append(nowStr.substring(j,nowStr.length())); // 나머지 넣기
		return sb.toString();
	}

	static class pair {
		String s; // 문자열
		int cnt; // 횟수
		public pair(String s, int cnt) {
			this.s = s;
			this.cnt = cnt;
		}
	}
}