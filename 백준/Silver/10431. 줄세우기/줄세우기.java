import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int testcase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc<testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int result = 0;
			
			ArrayList<Integer> students = new ArrayList<>();
			for (int i=0; i<20; i++) {
				int now = Integer.parseInt(st.nextToken());
				if (i==0) {
					students.add(now);
					continue;
				}
				
				boolean stand = false;
				// 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면
				for (int j=0; j<students.size(); j++) {
					int other = students.get(j);
					if (other > now) {
						// 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다
						students.add(j, now);
						// 그 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다
						result += students.size()-(j+1);
						stand = true;
						break;
					}
				}
				if (!stand) students.add(now);
			}
			
			sb.append(T+" "+result+"\n");
			// System.out.println(result + " " + students.toString());
		}
		
		System.out.println(sb);
	}
}