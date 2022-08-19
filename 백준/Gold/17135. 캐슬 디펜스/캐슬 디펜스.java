import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	// 1. 궁수 3명 성에 배치 (N번째 줄, M개 중 3개 선택)
	// 2. 게임 플레이
	//   (1) 거리가 D 이하인 적 중 가장 가까운 적 섬멸 (동시에 공격)
	//   (1)-1. 가장 가까운 적이 여러명이면 가장 왼쪽 적 섬멸
	//   (1)-2. 적이 여러 궁수에게 공격받기 가능
	//   (2) 공격 받은 적은 게임에서 제외 (처치 완료) 
	//   (3) 공격이 끝나면 적은 아래로 한칸 이동 
	//   (3)-1. 적이 성에 닿으면 게임에서 제외 (처치 불가)
	//   (4) 다음 라운드 
	//   (5) 모든 적이 격자판에서 제외되면 게임 종료 
	// 3. 게임 종료 후 처치한 적 계산, 최고기록 갱신 -> 2번 다음 플레이 
	
	
	static int N, M, D, kill, ans;
	static int[][] map;
	static boolean[] used;
	static Point[] archer;
	static ArrayList<Point> enemy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		D = Integer.parseInt(st.nextToken()); // 궁수 공격 거리 제한
		
		map = new int[N][M];
		ans = 0;
		archer = new Point[3];
		enemy = new ArrayList<>();
		used = new boolean[M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) {
					enemy.add(new Point(i,j));
				}
			}
		}
		//printMap(); // 맵 올바르게 되어있는지 확인
		
		comb(0,0); // 궁수 배치
		System.out.println(ans);
	}

	// 궁수 조합
	private static void comb(int idx, int start) {
		if (idx == 3) {
			//printArcher(); // 조합 확인용
			kill = 0;
			play();
			ans = Math.max(kill, ans);
			return;
		}
		
		for (int i=start; i<M; i++) {
			if (used[i]) continue;
			used[i] = true;
			archer[idx] = new Point(N,i);
			comb(idx+1, i);
			used[i] = false;
		}
		
	}


	// 게임 시작
	private static void play() {
		// 적 배열 복사
		ArrayList<Point> copyEnemy = new ArrayList<>();
		for (int i=0; i<enemy.size(); i++) {
			copyEnemy.add(new Point(enemy.get(i).x, enemy.get(i).y));
		}
		//printEnemy(copyEnemy); // 적 배열 확인

		while (true) {
			// 적이 아무도 없으면 게임 종료
			if (copyEnemy.size()==0) {
				break;
			}
			
			boolean[] attack = new boolean[copyEnemy.size()];
			// 궁수 공격
			for (int i=0; i<3; i++) {
				// 가장 가까운 적과 거리 구하기
				int nearEnemyNum=-1, minDis = Integer.MAX_VALUE;
				for (int j=0; j<copyEnemy.size(); j++) {
					int dis = Math.abs(archer[i].x-copyEnemy.get(j).x) + Math.abs(archer[i].y-copyEnemy.get(j).y);
					if (dis>D) continue;
					if (minDis>dis) {
						nearEnemyNum = j;
						minDis = dis;
					}
					else if (minDis==dis) {
						if (copyEnemy.get(j).y<copyEnemy.get(nearEnemyNum).y) {
							nearEnemyNum = j;
							minDis = dis;
						}
					}
				}
				if (nearEnemyNum!=-1) attack[nearEnemyNum]=true;
			}
			
			// 적 처치 (게임 제외)
			for (int i=copyEnemy.size()-1; i>=0; i--) {
				if (attack[i]) {
					copyEnemy.remove(i);
					kill++;
				}
			}
			//System.out.print("처치되지 않은 ");
			//printEnemy(copyEnemy);
			
			// 살아있는 적 아래로 한칸 이동
			for (int i=copyEnemy.size()-1; i>=0; i--) {
				copyEnemy.get(i).x = copyEnemy.get(i).x+1;
				// 성에 닿으면 게임에서 제외
				if (copyEnemy.get(i).x==N) copyEnemy.remove(i);
			}
			//System.out.print("아래로 이동한 ");
			//printEnemy(copyEnemy);
		}
		
		//System.out.println(kill);
	}
	
	// 위치 객체
	static class Point {
		int x,y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// =========================================================================

	// 맵 출력
	private static void printMap() {
		System.out.println("맵출력 시작 =====================");
		for (int i=0; i<=N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("맵출력 종료 =====================");
	}
	
	// 적 리스트 확인
	private static void printEnemy(ArrayList<Point> enemy) {
		System.out.println("적 리스트 확인");
		for (int i=0; i<enemy.size(); i++) 
			System.out.print("("+enemy.get(i).x+","+enemy.get(i).y+") ");
		System.out.println();		
	}
	
	// 궁수 조합 확인
	private static void printArcher() {
		System.out.println("궁수 조합 확인");
		for (int i=0; i<3; i++)
			System.out.print("("+archer[i].x+","+archer[i].y+") ");
		System.out.println();
	}
	

	
}