import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    static int [] dx = {0,-1,-1,-1,0,1,1,1};
    static int [] dy = {-1,-1,0,1,1,1,0,-1};
    
    static int N,M,res;
    static int [][] map;
    static Point [] cloudMove;
    static boolean [][] visited;
    static Queue<Point> que;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        cloudMove = new Point[M];
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 지도
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            cloudMove[i] = new Point(Integer.parseInt(st.nextToken()),
            						 Integer.parseInt(st.nextToken()));
        } // 구름 움직임
        
        que = new LinkedList<>();
        que.add(new Point(N,1));
        que.add(new Point(N,2));
        que.add(new Point(N-1,1));
        que.add(new Point(N-1,2)); // 구름 시작점 저장
        
        for(int i = 0; i < M; ++i) {
            int dir = cloudMove[i].x - 1;
            int num = cloudMove[i].y;
            res = 0;
            bfs(dir,num);
        }
        
        System.out.println(res);
	}
    
    
    static void bfs(int dir, int dist) {
        Queue<Point> queMove = new LinkedList<>();
        while(!que.isEmpty()) {
        	Point cur = que.poll();

            int nx = cur.x + (dx[dir] * dist);
            int ny = cur.y + (dy[dir] * dist);

            while(true) {
            	if (nx > 0 && nx <= N) break;
            	if (nx<1) nx = nx+N;
            	else nx = nx-N;
            }
            while(true) {
            	if (ny > 0 && ny <= N) break;
            	if (ny<1) ny = ny+N;
            	else ny = ny-N;
            }
            queMove.add(new Point(nx,ny));
            map[nx][ny]++; // 빗물 증가
            visited[nx][ny] = true; // 방문처리
        }

        // 대각선 체크
        while(!queMove.isEmpty())
        {
            Point now = queMove.poll();

            int cnt = 0;
            for(int idx = 1; idx <= 7; idx += 2)
            {
                int nx = now.x + dx[idx];
                int ny = now.y + dy[idx];

                if(nx<=0 || nx>N || ny<=0 || ny>N || map[nx][ny] < 1) continue;
                cnt++;
            }
            map[now.x][now.y] += cnt;
        }
        
        // 구름 다시 생성
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                if((map[i][j] >= 2) && !visited[i][j]) {
                    que.add(new Point(i,j));
                    map[i][j] -= 2; // 2 감소
                }
                else visited[i][j] = false;
                res += map[i][j]; // 빗물 누적
            }
        }
    }
    
    
    static class Point {
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}