import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, result;
	static ArrayList<ArrayList<Integer>> adjList;
	static boolean[] selected;
	static ArrayList<Integer> A,B;
	static int[] person;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList<>();
		selected = new boolean[N+1];
		result = Integer.MAX_VALUE;
		person = new int[N+1];
		
		// 인구 수
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접리스트
		adjList.add(new ArrayList<Integer>());
		for (int i=1; i<=N; i++) { // 1부터 시작 가능하도록
			st = new StringTokenizer(br.readLine(), " ");
			adjList.add(new ArrayList<Integer>());
			int num = Integer.parseInt(st.nextToken());
			for (int j=1; j<=num; j++) {
				adjList.get(i).add(Integer.parseInt(st.nextToken()));
			}
			//System.out.println(adjList.get(i));
		}
		
		// 구역 나누기
		subset(1, 0);
		
		if (result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	
	private static void subset(int idx, int cnt) {
		
		if (idx==N) {
			if (cnt==N || cnt==0) return; // 비어있는 그룹 제외
			A = new ArrayList<>();
			B = new ArrayList<>();
			for (int i=1 ;i<=N; i++) {
				if (selected[i]) A.add(i);
				else B.add(i);
			}
			//System.out.println("A: "+A);
			//System.out.println("B: "+B);
			
			if (!circle(A) || !circle(B)) return; // 연결되지 않는 그룹 제외
			result = Math.min(result, person()); // 인구 수 세서 갱신
			return;
		}
		selected[idx] = true;
		subset(idx+1, cnt+1);
		selected[idx] = false;
		subset(idx+1, cnt);
	}

	
	private static int person() {
		int aCnt = 0; int bCnt = 0;
		for (int i=0; i<A.size(); i++) {
			aCnt += person[A.get(i)];
		}
		for (int i=0; i<B.size(); i++) {
			bCnt += person[B.get(i)];
		}
		//System.out.println(aCnt+" "+bCnt);
		return Math.abs(aCnt-bCnt);
	}
	
	// 연결 됐는지 bfs로 확인
	private static boolean circle(ArrayList<Integer> list) {
		int first = list.get(0);
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		que.add(first);
		visited[first] = true;
		
		int count = 1; // 1개 연결된상태
		while(!que.isEmpty()) {
			int now = que.poll();
			for (int i=0; i<adjList.get(now).size(); i++) {
				int next = adjList.get(now).get(i);
				if (visited[next] || !list.contains(next)) continue;
				que.add(next);
				visited[next] = true;
				count++;
			}
		}
		
		if (count == list.size()) return true;
		else return false;
	}

}