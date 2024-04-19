import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static boolean aeiou = false; // 모음(a,e,i,o,u) 하나를 반드시 포함하는지 체크.
	static boolean typeTriple = false; // 모음이 3개 혹은 자음이 3개 연속 오는지 체크.
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			String pwd = br.readLine();
			if (pwd.equals("end")) break;
			
			char[] spelling = new char[pwd.length()];
			for (int i=0; i<pwd.length(); i++) {
				spelling[i] = pwd.charAt(i);
			}
			
			aeiou = false; 
			typeTriple = false; 
			boolean acceptable = true;
			
			for (int i=0; i<spelling.length; i++) {
				if (i==0) {
					containsAeiou(spelling[i]);
				}
				else if (i==1) {
					containsAeiou(spelling[i]);
					if (isSameDouble(spelling[i-1], spelling[i])) {
						acceptable = false;
						break;
					};
				}
				else {
					containsAeiou(spelling[i]);
					if (isSameDouble(spelling[i-1], spelling[i])) {
						acceptable = false;
						break;
					};
					if (isAeiouTriple(spelling[i-2], spelling[i-1], spelling[i])) {
						acceptable = false;
						break;
					}
					if (isBcdfgTriple(spelling[i-2], spelling[i-1], spelling[i])) {
						acceptable = false;
						break;
					}
				}
			}
			if (!aeiou) acceptable = false;
			makeSentence(pwd, acceptable);
		}
		
		System.out.println(sb);
	}
	
	// 모음 검사
	public static boolean isAeiou(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ) return true;
		else return false;
	}
	
	// 모음(a,e,i,o,u) 하나를 반드시 포함하는지 체크.
	public static void containsAeiou(char ch) {
		if (isAeiou(ch)) aeiou = true;
	}
	
	// 같은 글자가 연속적으로 두번 오는지 체크 (ee 와 oo는 허용)
	public static boolean isSameDouble(char previous, char now) {
		if (previous == now) {
			if (now == 'e' || now == 'o') return false;
			else return true;
		}
		else return false;
	}
	
	// 모음이 3개 연속 오는지 체크.
	public static boolean isAeiouTriple(char a, char b, char c) {
		if (isAeiou(a)) {
			if (isAeiou(b)) {
				if (isAeiou(c)) {
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	// 자음이 3개 연속 오는지 체크.
	public static boolean isBcdfgTriple(char a, char b, char c) {
		if (!isAeiou(a)) {
			if (!isAeiou(b)) {
				if (!isAeiou(c)) {
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	// sb 완성
	public static void makeSentence(String pwd, boolean acceptable) {
		if (acceptable) sb.append("<"+pwd+"> is acceptable."+"\n");
		else sb.append("<"+pwd+"> is not acceptable."+"\n");
	}
}