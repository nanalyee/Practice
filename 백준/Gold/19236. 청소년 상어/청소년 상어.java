import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static public int[] dx = {-1,-1,0,1,1,1,0,-1};
	static public int[] dy = {0,-1,-1,-1,0,1,1,1};

	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[4][4];
		Fish[] fish = new Fish[16+1]; // 1~16
		result = 0;
		for (int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken()); // 번호
				int b = Integer.parseInt(st.nextToken()) -1; // 방향
				fish[a] = new Fish(i, j, b, true);
				map[i][j] = a;
			}
		}
		
		// 상어가 (0,0)물고기 먹음
		Shark shark = new Shark(0, 0, fish[map[0][0]].dir, map[0][0]);
		fish[map[0][0]].live = false;
		map[0][0] = -1; // 상어는 -1번
		// printMap(map, "시작합니다");
		
		dfs(map, shark, fish);
		
		System.out.println(result);
	}
	
	public static void dfs(int[][] map, Shark shark, Fish[] fish) {
		// printMap(map, "상어 이동 완료");

		result = Math.max(result, shark.eatSum); // 최댓값
		FishMove(map, fish); // 이동
		
		for (int dis=1; dis<4; dis++) {
			int x = shark.x + dx[shark.dir] * dis;
			int y = shark.y + dy[shark.dir] * dis;
			if (0 <= x && x < 4 && 0 <= y && y < 4 && map[x][y] > 0) {
				// copy
				int[][] copyMap = new int[4][4];
				for (int i=0; i<4; i++) {
					for (int j=0; j<4; j++) {
						copyMap[i][j] = map[i][j];
					}
				}
				Fish[] copyFish = new Fish[16+1];
				for (int i=1; i<=16; i++) 
					copyFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir, fish[i].live);
				
				// 냠
				copyMap[shark.x][shark.y] = 0;
				Fish f = copyFish[map[x][y]];
				Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum+map[x][y]);
				f.live = false;
				copyMap[f.x][f.y] = -1;
				dfs(copyMap, newShark, copyFish);
			}
		}
	}
	

	public static void FishMove(int[][] map, Fish[] fish) {
		for (int i=1; i<=16; i++) {
			Fish now = fish[i];
			if (!now.live) continue;
			int d = now.dir;
			for (int j = 0; j < 8; j++)  {
				int x = now.x + dx[d];
				int y = now.y + dy[d];
				if (x<0 || y<0 || x>=4 || y>=4 || map[x][y]==-1) {
					d++;
					if (d>=8) d=0;
				} else {
					int next = map[x][y];
					if (next == 0) {
						fish[i] = new Fish(x, y, d, now.live);
					}
					else  {						
						fish[i] = new Fish(x, y, d, now.live);
						fish[next] = new Fish(now.x, now.y, fish[next].dir, fish[next].live);
					}
					map[x][y] = i;
					map[now.x][now.y] = next;
					break;
				}
			}			
		}
		// printMap(map, "물고기 이동 완료");
	}
	
	public static void printMap(int[][] map, String str) {
		System.out.println("Map =============" + str);
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
    static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }
    
	public static class Fish {
		int x, y, dir;
		boolean live;
		Fish(int x, int y, int dir, boolean live) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.live = live;
		}
	}
}