import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		int count = 0;
		
		while (true) {	
			String newN = "";
			for (int i=0; i<N.length(); i++) {
				if (N.charAt(i)!='1') {
					newN = newN + N.charAt(i);
				}
				else count++;
			}
			//System.out.println(newN);
			
			if (newN.equals("")) {
				//System.out.println("끝: " + count);
				break;
			}
			
			String renewN = newN;
			for (int i=0; i<newN.length(); i++) {
				if (newN.charAt(i)=='0') {
					renewN = newN.substring(i+1);
					//System.out.println(i+"번째 " +renewN);
				}
				else break;
			}
			//System.out.println(renewN);
			if (renewN.equals("")) break;
			
			if (Integer.parseInt(renewN)%10==0) {
				count += 9;
				renewN = Integer.toString(Integer.parseInt(renewN)-9);
			} 
			else {
				count += (Integer.parseInt(renewN)%10 - 1);
				renewN = renewN.substring(0,renewN.length()-1)+"1";
			}
			//System.out.println(count+ " " + renewN);
			N = renewN;
		}
		System.out.println(count);
	}
}