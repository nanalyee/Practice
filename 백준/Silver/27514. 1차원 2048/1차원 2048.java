import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] logArr = new int[63];
		Arrays.fill(logArr, 0);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<N; i++) {
			long num = Long.parseLong(st.nextToken());
			if (num!=0) {
				//System.out.println(i + "번째" + num);
				logArr[log2(num)]++;
			}
		}
		for (int i=0; i<62; i++) {
			logArr[i+1] += logArr[i]/2;
		}
		//System.out.println(Arrays.toString(logArr));
		for (int i=0; i<63; i++) {
			if (logArr[62-i]!=0) {
				//System.out.println(i);
				System.out.println((long)(Math.pow(2, 62-i)));
				break;
			}
		}
	}
	
	public static int log2(long x) {
	    return (int) (Math.log(x) / Math.log(2));
	}
}