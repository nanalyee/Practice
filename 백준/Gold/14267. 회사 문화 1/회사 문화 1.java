import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M;
	public static ArrayList<Integer> adj[];
	public static int res[];
	public static int praise[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for(int i=1;i<=N;i++) { 
			adj[i] = new ArrayList<Integer>();
		} // 초기화
		
		praise = new int[N+1]; // 1~N번
		res = new int[N+1]; // 1~N번까지 누적합 저장
		st = new StringTokenizer(br.readLine());
		st.nextElement();
		for(int i=2;i<=N;i++) { // 1번은 상사가 없기 때문에 제외
			int num = Integer.parseInt(st.nextToken());
			adj[num].add(i);
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			praise[num] += cnt; // 누적해줘야함
		}
		
		dfs(1,praise[1]);
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			sb.append(res[i] + " ");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int num, int cnt) {
		res[num]+= cnt;
		
		for(int i=0;i<adj[num].size();i++) {
			int next = adj[num].get(i);
			dfs(next,cnt + praise[next]);
		}
	}
}