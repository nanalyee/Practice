import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	static int N, result;
	static ArrayList<Integer> numbers, A;
	static ArrayList<Character> command, B;
	static boolean[] selected;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		numbers = new ArrayList<>();
		command = new ArrayList<>();
		result = Integer.MIN_VALUE;

		selected = new boolean[N/2];
		arr = new int[N/2];
		
		if (N==1) result = Integer.parseInt(str); 
		else {
			for (int i=0; i<N; i++) {
				if (i%2==0) numbers.add(str.charAt(i)-'0');
				else command.add(str.charAt(i));
			}
			//System.out.println(numbers);
			//System.out.println(command);
			subset(0);
		}
		
		System.out.println(result);
	}

	private static void subset(int idx) {
		if (idx == N/2) {
			//System.out.println(Arrays.toString(selected));
			A = new ArrayList<>();
			B = new ArrayList<>();
			calculate();
			return;
		}
		
		selected[idx] = false;
		subset(idx+1);
		if (idx==0 || (idx!=0 && !selected[idx-1])) {
			selected[idx] = true;
			subset(idx+1);
		}
	}

	private static void calculate() {
		
		for (int i=0; i<N/2; i++) {
			char cmd = command.get(i);
			if (selected[i]) {
				int num = 0;
				if (cmd=='+') num = numbers.get(i) + numbers.get(i+1);
				else if (cmd=='-') num = numbers.get(i) - numbers.get(i+1);
				else if (cmd=='*') num = numbers.get(i) * numbers.get(i+1);
				A.add(num);
			}
			else {
				if (i==0) A.add(numbers.get(0));
				if (i==N/2-1 || !selected[i+1]) A.add(numbers.get(i+1));
				B.add(cmd);
			}
		}
		//System.out.println(A);
		//System.out.println(B);
		
		int size = B.size();
		int num = A.get(0);
		for (int i=0; i<size; i++) {
			char cmd = B.get(i);
			if (cmd=='+') num += A.get(i+1);
			else if (cmd=='-') num -= A.get(i+1);
			else if (cmd=='*') num *= A.get(i+1);
		}
		//System.out.println(num);
		result = Math.max(result, num);
	}
}