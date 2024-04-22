import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 신청 수
		char game = st.nextToken().charAt(0); // 게임 종류
		
		HashSet<String> set = new HashSet<>();
		for (int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		int num = 0;
		if (game=='Y') num = 2;
		else if (game=='F') num = 3;
		else num = 4;
		System.out.println(set.size()/(num-1));
	}
}