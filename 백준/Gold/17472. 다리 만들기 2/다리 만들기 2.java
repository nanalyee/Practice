import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<BridgeInfo> bridgeList;
	static int n, m, nodeCnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		bridgeList = new ArrayList<>();
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int num = 1;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j]==1 && !visited[i][j]) {
					bfs(i,j, num);
					num++;
				}
			}
		}
		
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<m; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 크루스칼 준비
		nodeCnt = num-1;
		parents = new int[nodeCnt + 1];
		for (int i=1; i<nodeCnt+1; i++) { 
			parents[i] = i; 
		} 
		
		makeBridge(); // 가능한 다리 리스트 만들기
		kruskal(); // 최소 신장 트리 만들기
		
	}
	
	
	// 최소 신장 트리 만들기
	private static void kruskal() {
		Queue<BridgeInfo> pq = new PriorityQueue<>();
		for(int i=0; i<bridgeList.size(); i++) {
			int to = bridgeList.get(i).start;
			int from = bridgeList.get(i).end;
			int value = bridgeList.get(i).weight;
			pq.add(new BridgeInfo(to, from, value));			
		}
		
		int size = pq.size();
		int total =0;
		// 간선 하나씩 조회 (비용이 작은 간선부터)
		for(int i=0; i< size; i++) {
			BridgeInfo node = pq.poll();
			int rx = find(node.start);
			int ry = find(node.end);
			 
			 // 사이클이 발생하지 않는 경우에만 집합에 포함 
			 if(!isSameParent(rx, ry)) {
				 //System.out.println(node.start+" "+ node.end+" "+node.weight);
				 total += node.weight;
				 union(node.start, node.end);
			 }
		}
		
        // 모두 연결되어있는지 확인
		boolean unionOne = true;
		int checkParent = find(1);
		for (int i=2; i<=nodeCnt; i++) {
			if (checkParent != find(i)) {
				unionOne = false;
				break;
			}
		}
		
		if (total==0 || !unionOne) total = -1;
		System.out.println(total);
	}


	// 간선 번호 매기기
	private static void bfs(int i, int j, int num) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(i,j));
		visited[i][j] = true;
		map[i][j] = num;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			for (int d=0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if (nx<0 || ny<0 || nx>=n || ny>=m) continue;
				if (visited[nx][ny] || map[nx][ny]!=1) continue;
				map[nx][ny] = num;
				visited[nx][ny] = true;
				que.add(new Point(nx, ny));
			}
		}
	}


	private static void makeBridge() {
		// 가로로 가능한 다리
		for (int i=0; i<n; i++) {
			boolean isIsland = false;
			boolean meetSea = false;
			int s = 0;
			int cnt = 0;
			for (int j=0; j<m; j++) {
				if (!isIsland && map[i][j]!=0) {
					isIsland = true;
					s = map[i][j];
				}
				if (isIsland && map[i][j]==0) {
					meetSea = true;
					cnt++;
				}
				if (meetSea && isIsland && map[i][j]!=0) {
					if (cnt>1) bridgeList.add(new BridgeInfo(s,map[i][j],cnt));
					cnt = 0;
					meetSea = false;
					s = map[i][j];
				}
			}
		}
		
		// 세로로 가능한 다리
		for (int i=0; i<m; i++) {
			boolean isIsland = false;
			boolean meetSea = false;
			int s = 0;
			int cnt = 0;
			for (int j=0; j<n; j++) {
				if (!isIsland && map[j][i]!=0) {
					isIsland = true;
					s = map[j][i];
				}
				if (isIsland && map[j][i]==0) {
					meetSea = true;
					cnt++;
				}
				if (meetSea && isIsland && map[j][i]!=0) {
					if (cnt>1) bridgeList.add(new BridgeInfo(s,map[j][i],cnt));
					cnt = 0;
					meetSea = false;
					s = map[j][i];
				}
			}
		}
		
//		for (int j=0; j<bridgeList.size(); j++) {
//			System.out.print(bridgeList.get(j).start+", "
//							+ bridgeList.get(j).end+", "
//							+ bridgeList.get(j).weight+"\n");
//		}
	}
	
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	static class BridgeInfo implements Comparable<BridgeInfo>{
		int start, end, weight;
		public BridgeInfo(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(BridgeInfo o) {
			return this.weight - o.weight;
		}
	}
	
	static int find(int x) { 
		if (parents[x] == x) { 
			return x; 
	     } 
		return parents[x] = find(parents[x]);
	} 
	     
	static void union(int x, int y) {
		x = find(x); 
		y = find(y); 
		// 더 find 값으로 부모 노드 설정
	    if (x < y) { 
	    	parents[y] = x; 
	    } 
	    else { 
	    	parents[x] = y; 
	    } 
	}
	
	static boolean isSameParent(int x, int y) {
		if(x == y) return true;
		return false;
	} 
}