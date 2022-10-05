import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] origin;
	static int cnt;
	static boolean done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		origin = new int[9][9];
		for (int i=0; i<9; i++) {
			String str = br.readLine();
			for (int j=0; j<9; j++) {
				origin[i][j] = str.charAt(j)-'0';
				if (origin[i][j]==0) cnt++;
			}
		} // end input
		
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				 if(origin[i][j] != 0) continue;
				 fill(i, j);
			}
		} 
		

		
	}

	private static void fill(int x, int y) {
		if (done) return;
		for (int i=1; i<=9; i++) {
			int xx = (x/3) * 3;
			int yy = (y/3) * 3;
			boolean skip = false;
			for (int j=xx; j<xx+3; j++) {
				for (int k=yy; k<yy+3; k++) {
					if (origin[j][k] == i) {
						skip = true;
						break;
					}
				}
				if (skip) break;
			}
			if (skip) continue;
			
			skip = false;
			for (int k=0; k<9; k++) {
				if (origin[x][k] == i) {
					skip = true;
					break;
				}
			}
			if (skip) continue;
			
			skip = false;
			for (int k=0; k<9; k++) {
				if (origin[k][y] == i) {
					skip = true;
					break;
				}
			}
			if (skip) continue;
			
			origin[x][y] = i;
			//System.out.println(x+" "+y+" : "+i);

			cnt--;
			if (cnt==0) {
				for (int k=0; k<9; k++) {
					for (int j=0; j<9; j++) {
						System.out.print(origin[k][j]);
					}
					System.out.println();
				} 
				done = true;
				return; // 모두 채우면 종료
			}
			
			skip = false;
			for(int j = 0; j < 9; j++) {
                for(int k = 0; k < 9; k++) {
                    if(origin[j][k] == 0)  {
                    	fill(j,k);
                    	skip = true;
                    	break;
                    }
                }
                if (skip) break;
            }
			
			origin[x][y] = 0;
			cnt++; // 백트래킹
			
		}
		
	}
}