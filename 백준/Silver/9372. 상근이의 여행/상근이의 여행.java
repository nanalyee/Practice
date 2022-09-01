import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, result;
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> adjList;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		
		for (int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine()); 
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adjList = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[N+1];
			result = 0;
			
			for (int j=0; j<=N; j++) {
				adjList.add(new ArrayList<>());
			}
			
			for (int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine()); 
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				adjList.get(n1).add(n2);
				adjList.get(n2).add(n1);
			}
			
			for(int j=0; j<=N; j++) {
	            Collections.sort(adjList.get(j));
	        }
			
			bfs();
			sb.append(result+"\n");
		}
		System.out.println(sb);
	}


	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(1);
		visited[0]=true;
		visited[1] = true;
		
		while (!que.isEmpty()) {
			result++;
			int now = que.poll();
			for (int i=0; i<adjList.get(now).size(); i++) {
				int next = adjList.get(now).get(i);
				if(!visited[next]) {
					visited[next] = true;
					que.add(next);
				}
			}
		}
		
		result = result-1;
	}
}