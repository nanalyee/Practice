import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
//	[b. 물건팔기]
//	구매자가 지불하려는 금액보다 파는 가격이 높으면 안삼
//	세준이는 가장 큰 이익을 얻고자 함 -> 최대로 하는 가격은?
// (단, 세준이가 안 팔수도 있다.)
	
//	[제한 조건]
//	- 구매자 수 N <= 50
//	- 최대 금액과 배송비는 10^6보다 작거나 같은 음이 아닌 정수이고, 배송비는 0이 될 수도 있다.
//	- 같은 가격이 여러개이면 가장 낮은 가격을 출력
	
//	[풀이]
//	- 그리디 하게 접근하면 안될 것 같음 (몇 명이 살지 모른다)
//	- 구매자가 원하는 가격들을 오름차순으로 정렬해서 완탐??? (50개라서 완탐일 것 같음)
// - 손해이면 안팔수도 있다는 점!!!!
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Price[] arr = new Price[N]; // 금액 배열
		int maxValue = Integer.MIN_VALUE; // 최대 이익 (음수가 나올 수도 있음)
		int ans = 0; // 최대 이익일 때의 가격
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Price(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()));
		} // end input
		Arrays.sort(arr); 
		
//		for (int i=0; i<N; i++) {
//			System.out.println(arr[i].price);
//		} // check sort
		
		for (int i=0; i<N; i++) {
			int sum = 0; // 수익
			for (int j=i; j<N; j++) {
				if ( arr[i].price - arr[j].deliver < 0) continue;
				sum += arr[i].price;
				sum -= arr[j].deliver;
			}
			//System.out.println(sum);
			if (maxValue<sum) { 
				maxValue = sum;
				ans = arr[i].price;
			}
		}
		
		if (maxValue<=0) ans = 0;
		System.out.println(ans);
	}
	
	// 가격 정보 저장할 객체
	static class Price implements Comparable<Price> {
		int price, deliver;
		
		public Price(int price, int deliver) {
			super();
			this.price = price;
			this.deliver = deliver;
		}

		@Override
		public int compareTo(Price o) {
			return this.price-o.price;
		}
	}
}