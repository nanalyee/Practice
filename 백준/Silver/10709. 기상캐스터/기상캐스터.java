import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		boolean cloud = false;
		
		int[][] arr = new int[h][w];
		char[][] map = new char[h][];
		for (int i=0; i<h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i=0; i<h; i++) {
			cloud = false;
			for (int j=0; j<w; j++) {
				if (map[i][j]=='c') {
					arr[i][j] = 0;
					cloud = true;
				}
				else if (cloud) arr[i][j] = arr[i][j-1]+1;
				else arr[i][j] = -1;
			}
		}
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}