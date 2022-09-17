import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		int[] arr = new int[300001]; // 최단 거리 저장 배열
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		// 연결리스트에 노드 추가
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Integer>());
			arr[i]=-1; // 최단거리 -1로 초기화
		}
		
		// 간선 정보 입력 
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}
		
		arr[x] = 0; // 시작지점의 최단거리 값 0으로 초기화
		Queue<Integer> que = new LinkedList<>();
		que.offer(x);
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int i=0;i<graph.get(now).size();i++) {
				int next=graph.get(now).get(i);
				if(arr[next] == -1) {
					// 도로의 거리가 1이기 때문에 이전 최단거리에 +1한 값 저장
					arr[next] = arr[now]+1;
					que.offer(next);
				}
			}
		}
		
		// 최단거리가 k인 노드 찾기 
		boolean check = false;
		for(int i=1; i<=n; i++) {
			if(arr[i] == k) {
				System.out.println(i);
				check=true;
			}
		}
		
		// 최단거리가 k인 노드가 없다면 -1 출력 
		if(check == false)
			System.out.println(-1);
	}
}