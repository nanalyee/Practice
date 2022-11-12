import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long x = Long.parseLong(st.nextToken()); // 집의 위치
        long y = Long.parseLong(st.nextToken()); 
        long w = Long.parseLong(st.nextToken()); // 한 블록 가는데 걸리는 시간
        long s = Long.parseLong(st.nextToken()); // 대각선 블록 가는데 걸리는 시간
        
        long straigt = 0; // 수직수평 만들어 이동
        long cross = 0; // 대각선으로만 이동 (전체 x+y가 홀수이면 1을빼주고 한블록 이동)
        long mix = 0; // 최소의 대각선 이동을 하고 나머지 수직수평 만들어 이동
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        
        straigt = (x + y) * w;
        
        cross = max;
        if ((x+y)%2!=0) cross = (cross-1) * s + w;
        else cross *= s;
        
        mix = (min*s + (max-min)*w);
        
        System.out.println(Math.min(Math.min(straigt, cross), mix));
	}
}