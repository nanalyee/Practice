import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	static ArrayList<Integer> numbers;
	static ArrayList<Character> command;
	static boolean[] select;
	static int n, result;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		numbers = new ArrayList<>();
		command = new ArrayList<>();
		select = new boolean[n/2];
		result = Integer.MIN_VALUE;
		
		
		if (n==1) result = Integer.parseInt(str);
		else {
			for (int i=0; i<n; i++) {
				if (i%2==0) numbers.add(str.charAt(i)-'0');
				else command.add(str.charAt(i));
			}
			//System.out.println(numbers);
			//System.out.println(command);
			subset(0);
		}
		
		System.out.println(result);
	}
	
	
	// 괄호 부분집합
	private static void subset(int idx) {
		if (idx==n/2) {
			//System.out.println(Arrays.toString(select));
			list = new ArrayList<>();
			
			first(); // true인것부터 계산 (괄호 넣은거부터)
			second(); // 나머지 계산
			return;
		}
		
		if (idx==0) { // 첫 연산자는 괄호 추가 가능
			select[idx] = true;
			subset(idx+1);
		}
		if (idx!=0 && !select[idx-1]) { // 이전 연산자에 괄호가 없으면 추가 가능
			select[idx] = true;
			subset(idx+1);
		}
		select[idx] = false;
		subset(idx+1);
	}
	
	



	// 괄호 순서대로 연산하기
	private static void first() {
		for (int i=0; i<n/2; i++)  {
			int num = 0;
			if (select[i]) {
				char com = command.get(i); // 연산자 불러오기
				if (com=='+') {
					num = numbers.get(i) + numbers.get(i+1);
				}
				else if (com=='-') {
					num = numbers.get(i) - numbers.get(i+1);
				}
				else if (com=='*') {
					num = numbers.get(i) * numbers.get(i+1);
				}
				list.add(num);
			}
			else {
				if (i==0 && !select[i]) list.add(numbers.get(i));
				if (i!=n/2-1 && !select[i+1]) list.add(numbers.get(i+1)); 
				if (i==n/2-1) list.add(numbers.get(i+1));
			}
		}
		//System.out.println(list);
	}
	
	// 괄호를 계산한 후의 나머지 연산
	private static void second() {
		int num = list.get(0);
		int idx=0;
		for (int i=0; i<n/2; i++)  {
			if (!select[i]) {
				char com = command.get(i); // 연산자 불러오기
				if (com=='+') {
					num += list.get(idx+1);
				}
				else if (com=='-') {
					num -= list.get(idx+1);
				}
				else if (com=='*') {
					num *= list.get(idx+1);
				}
				idx++;
			}
		}
		//System.out.println(num);
		result = Math.max(result, num);
	}
}