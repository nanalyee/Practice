import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//	[ 상어 규칙 ]
//	- 자신보다 큰 물고기는 지나갈 수 없다
//	- 자신보다 작은 물고기만 먹는다
//	-> 크기가 같으면 먹을수는 없지만 지나갈 수는 있다
//	- 1초에 한 칸씩 이동
//	- 먹은 물고기 수가 크키와 같을 때 크기가 증가
//	
//	[ 상어 이동 ]
//	- 더 이상 먹을 수 있는 물고기가 없다면 종료
//	- 먹을 수 있는 물고기가 1마리 -> 그 물고기 먹기
//	- 2마리 이상 -> 가장 가까운 물고기 (지나야하는 칸의 개수 최소값 의미)
//	-> 공동 순위가 있다면 가장 위, 그래도 공동순위라면 가장 왼쪽
//	
//	[ 결과 ]
//	몇 초 동안 물고기를 잡아 먹을 수 있는가?
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	
	static int N, stack, size, result;
	static int startX, startY;
	static int[][] map;
	//static boolean[][] visited;
	static int[][] visited;
	//static ;
	static int eatPoint[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		size = 2; // 상어 사이즈
		stack = 0; // 먹은 물고기 스택
		result = 0; // 시간
		map = new int[N][N];
		//visited = new boolean[N][N];
		visited = new int[N][N];
		eatPoint = new int[2]; 
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==9) {
					startX = i; startY = j;
					map[i][j] = 0;
				}
			}
		}
		
		// 아기 상어 시뮬레이션
		while(true) {
			// 먹을 수 있는 물고기 중 최우선순위 위치 저장 변수
			eatPoint[0] = Integer.MAX_VALUE; // 초기화 (초마다 비교해야 함)
			eatPoint[1] = Integer.MAX_VALUE;
			
			// bfs를 돌려서 물고기가 없으면 false
			if (!babyShark(startX, startY)) break;
			
			//printmap();
			
			// visited 초기화
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					visited[i][j] = 0;
				}
			}
		}
		
		System.out.println(result);
	}


	private static boolean babyShark(int a, int b) {
		//que = new PriorityQueue<int[]>();
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2]==o2[2]) {
					if (o1[0]==o2[0]) {
		            	return o1[1]-o2[1];
		            }
		            else return o1[0]-o2[0];
				}
				else return o1[2]-o2[2];
			}
		});
		
//		1. 시작점 add, 방문처리
		que.add(new int[] {a,b,1});
		visited[a][b] = 1;
		
//		2. 큐 빌때까지 반복
		while(!que.isEmpty()) {
			int[] now = que.poll();
		
//			3. if (now가 우선순위 변수와 같다면) 해당 물고기 먹기, 종료
			//System.out.println(eatPoint[0]+"!"+eatPoint[1]+" ?? "+now[0]+" "+now[1] );
			//if (now[0]==eatPoint[0] && now[1]==eatPoint[1]) {
			
			if (map[now[0]][now[1]]<size && map[now[0]][now[1]]!=0) {
				//System.out.println("yummy");
//				eatPoint[0] = Integer.MAX_VALUE; // 초기화
//				eatPoint[1] = Integer.MAX_VALUE;
				
				// 물고기 먹기
				map[now[0]][now[1]] = 0;
				stack++; 
				if (stack == size) { // 사이즈만큼 먹었으면
					size++; // 사이즈 증가
					//System.out.println("size UP! "+size);
					stack = 0; // 스택 초기화
				}
				startX = now[0]; startY = now[1]; // 시작지점으로 지정
				result = result + visited[now[0]][now[1]]-1;
				return true; // 먹었다! 종료
			}

//			4. 사방탐색으로 next 저장
			for (int d=0; d<4; d++) {
				int x = now[0] + dx[d];
				int y = now[1] + dy[d];
				
				// if (범위밖, 통과할 수 있는 칸이 아니면) continue
				if (x<0 || y<0 || x>=N || y>=N) continue;
				if (map[x][y]>size || visited[x][y]!=0) continue;
				
				// next가 먹을 수 있는 물고기인가?
				// -> 우선순위 변수 eat와 비교해 next 더 우선순위이면 eat=next
				// -> 공동 순위가 있다면 가장 위, 그래도 공동순위라면 가장 왼쪽
//				if (map[x][y]<size && map[x][y]!=0) {
//					System.out.println("fish"+x+" "+y);
//					if (eatPoint[0]>x) {
//						// 가장 위에 있는 것
//						eatPoint[0]=x; 
//						eatPoint[1]=y;
//					}
//						
//					if (eatPoint[0]==x) { // 가장 위가 여러개라면
//						if (eatPoint[1]>y) {
//							// 가장 왼쪽에 있는 것
//							eatPoint[0]=x; 
//							eatPoint[1]=y;
//						}
//							
//					}
//				}
				
				// 먹을 수 있든 없든 통과 가능하면 다 add
				que.add(new int[] {x,y,visited[now[0]][now[1]]+1});
				visited[x][y] = visited[now[0]][now[1]]+1;
			}
			//printvis();

		}
		return false; // 못 먹었다!
	}
	
	
	private static void printmap() {
		System.out.println("result: "+result);
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static void printvis() {
		System.out.println("Visit ===========");
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
//				if (visited[i][j]) System.out.print("o ");
//				else  System.out.print("x ");
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
	}
}