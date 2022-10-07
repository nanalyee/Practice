import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	// 1. 블리자드로 파괴 -> M번 반복
	// 2-1. 빈칸을 메꾸기 (땡겨오기)
	// 2-1. 폭발 (4개 이상 연속 구슬) -> 1,2,3번 구슬이 몇개 폭발했는지 각각 센다
	// -> 빈칸을 메꿀 때 4개 이상 연속구슬이 있으면 계속 반복
	// 3. 남은 연속 구슬(4개 미만)은 하나의 그룹 -> 두개의 구슬로 변화
	// 3-1. 구슬 A(그룹에 들어있는 구슬의 개수)와 B(그룹을 이루고 있는 구슬의 번호)
	// 3-2. 구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우 그러한 구슬은 사라진다.
	// 4. 블리자드 M번 반복이 끝나면, 
	//    1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)
	

	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1};
	static int[] dcx = {0,1,0,-1}; // 좌하우상
	static int[] dcy = {-1,0,1,0};
	
	static int N, M, center, boom1, boom2, boom3;
	static int[][] map;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 블리자드 횟수
		map = new int[N][N];
		center = N/2;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input map
		
		// 1. 블리자드로 파괴 -> M번 반복
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()); // 1:상, 2:하, 3:좌, 4:우
			int dis = Integer.parseInt(st.nextToken());
			blizzard(dir-1, dis); // 블리자드
			//printMap("blizzard");
			pull(); // 빈 곳 있으면 빈곳을 없애서 하나의 리스트로 만들기
			while (true) {
				if (!boom()) break; // 폭발할 곳 있으면 폭발
			}
			change(); // 구슬 변화하기
		} 
		
		System.out.println(1*boom1 + 2*boom2 + 3*boom3);
	}
	
	private static void change() {
		ArrayList<Integer> newList = new ArrayList<>();
		int cnt = 1;
		for (int i=1; i<list.size(); i++) {
			if (list.get(i-1) == list.get(i)) {
				cnt++;
			}
			if (list.get(i-1) != list.get(i)) {
 				if (list.get(i-1)!=0) {
 					newList.add(cnt);
 					newList.add(list.get(i-1));
 				}
 				cnt = 1;
			} 
			if (i==list.size()-1) {
				if (list.get(i)!=0) {
					newList.add(cnt);
					newList.add(list.get(i));
 				}
			}
		}
		//System.out.println("new list"+newList);
		
		draw(newList);
	}

	private static void draw(ArrayList<Integer> list) {
		// 리스트 다시 집어넣기
		int cx = center;
		int cy = center;
		int dir = 0; // 왼쪽부터 시작
		int cnt = 0;
		for (int i=1; i<N; i++) {
			for (int t=0; t<2; t++) {
				for (int j=0; j<i; j++) {
					cx = cx+dcx[dir];
					cy = cy+dcy[dir];
					if (list.size()>cnt) {
						map[cx][cy] = list.get(cnt);
						cnt++;
					} else {
						map[cx][cy]=0;
					}
				}
				dir = (dir+1)%4;
			}
		}
		for (int i=1; i<N; i++) {
			cx = cx+dcx[dir];
			cy = cy+dcy[dir];
			if (list.size()>cnt) {
				map[cx][cy] = list.get(cnt);
				cnt++;
			} else {
				map[cx][cy]=0;
			}
		}
		
		//printMap("draw");
	}

	private static boolean boom() {
		boolean boomable = false;
		int cnt = 1;
		// 리스트에서 폭발할 곳 찾아 없애주기
		ArrayList<Integer> removeList = new ArrayList<>();
		
		for (int i=1; i<list.size(); i++) {
			
			if (list.get(i-1) == list.get(i)) {
				cnt++;
			} 
			if (list.get(i-1) != list.get(i) && cnt<4) {
				cnt = 1;
			} 
			//System.out.println(i);
			if (list.get(i-1) != list.get(i) && cnt>=4) {
				//System.out.println(list.get(i-1));
				int num = list.get(i-1);
				for (int j=1; j<=cnt; j++) {
					if (num==1) boom1++;
					if (num==2) boom2++;
					if (num==3) boom3++;
					removeList.add(i-j);
					boomable = true;
				}
				cnt = 1;
			}
			if (i==list.size()-1 && cnt>=4) {
				int num = list.get(i);
				for (int j=0; j<cnt; j++) {
					if (num==1) boom1++;
					if (num==2) boom2++;
					if (num==3) boom3++;
					removeList.add(i-j);
					boomable = true;
				}
				cnt = 1;
			}
		}
		
		removeList.sort(null);
		//System.out.println(removeList);

		for (int j=removeList.size()-1; j>=0; j--) {
			//System.out.println(removeList.get(j));
			int num = removeList.get(j);
			list.remove(num);
		}
		removeList = new ArrayList<>();

		//System.out.println("boom"+list);
		if (boomable) return true;
		else return false;
	}


	private static void pull() {
		// 리스트로 뽑아오기
		list = new ArrayList<>();
		int cx = center;
		int cy = center;
		int dir = 0; // 왼쪽부터 시작
		for (int i=1; i<N; i++) {
			for (int t=0; t<2; t++) {
				for (int j=0; j<i; j++) {
					cx = cx+dcx[dir];
					cy = cy+dcy[dir];
					if (map[cx][cy]!=0) list.add(map[cx][cy]);
				}
				dir = (dir+1)%4;
			}
		}
		for (int i=1; i<N; i++) {
			cx = cx+dcx[dir];
			cy = cy+dcy[dir];
			if (map[cx][cy]!=0) list.add(map[cx][cy]);
		}
		//System.out.println(list);
	}

	private static void blizzard(int dir, int dis) {
		int cx = center;
		int cy = center;
		for (int i=0; i<dis; i++) {
			cx += dx[dir];
			cy += dy[dir];
			map[cx][cy] = 0;
		}
	}
	
	private static void printMap(String method) {
		System.out.println(method+"-----");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		} 
		System.out.println("-------------");
	}
	
}