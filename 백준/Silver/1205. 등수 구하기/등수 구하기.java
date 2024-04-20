import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Taesu = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int ans = 1;
		ArrayList<Integer> list = new ArrayList<>();
		
		// 둘째 줄은 N이 0보다 큰 경우에만 주어진다.
		if (N!=0) { 
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list); // 오름차순
			Collections.reverse(list); // 거꾸로
			// System.out.println(list.toString()); 
			
			int[] rank = new int[P]; // 랭킹리스트
			int[] index = new int[P];
			for (int i=0; i<P; i++) {
				if (list.size()>i) {
					rank[i] = list.get(i);
					if (i!=0) {
						if (rank[i]==rank[i-1]) index[i] = index[i-1];
						else index[i] = i+1;
					}
					else index[i] = 1;
				}
				else rank[i] = -1;
			}
			// System.out.println(Arrays.toString(rank));
			// System.out.println(Arrays.toString(index));
			// 태수의 새로운 점수가 랭킹 리스트에서 몇 등 하는지
			for (int i=0; i<P; i++) {
				if (rank[i] < Taesu) {
					if (i!=0 && rank[i-1] == Taesu) ans = index[i-1];
					else {
						if (index[i]==0) ans = i+1;
						else ans = index[i];
					}
					break;
				}
				if (i==P-1) ans = -1;
			}
		}
		
		System.out.println(ans);
	}
}