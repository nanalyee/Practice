import java.util.Scanner;

public class Main {	
	static int N,r,c, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		ans = 0;
		task(0,0,(1<<N),(1<<N));
	}
	
	static void task(int si, int sj, int ei, int ej) {
		if (!(si<=r && r<ei && sj<=c && c<ej)) {
			ans += (ei-si)*(ej-sj);
			return;
		}
		
		if (ei-si==1) {
			if (si==r && sj==c) System.out.println(ans);
			ans++;
			return;
		}
		
		int mi = (si+ei)/2;
		int mj = (sj+ej)/2;
		
		task(si,sj,mi,mj); //1
		task(si,mj,mi,ej); //2
		task(mi,sj,ei,mj); //3
		task(mi,mj,ei,ej); //4
	}
}