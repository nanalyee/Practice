import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 사진틀의 개수
		int total = Integer.parseInt(br.readLine()); // 총 추천 횟수
		int[] arr = new int[101];
		ArrayList<Integer> list = new ArrayList<>(); // 사진틀 리스트
		st = new StringTokenizer(br.readLine());
		
		// 전체 학생들의 추천
		for (int i=0; i<total; i++) {
			int rec = Integer.parseInt(st.nextToken());
			arr[rec]++;
			
			// 사진틀에 넣자
			if (!list.contains(rec) && list.size()<n) list.add(rec);
			else if (!list.contains(rec)) {
				int minNum=0; int minVal=Integer.MAX_VALUE;
				for (int j=0; j<n; j++) {
					if (minVal>arr[list.get(j)]) {
						minNum = j;
						minVal = arr[list.get(j)];
					}
				}
				arr[list.get(minNum)]=0;
				list.remove(minNum);
				list.add(rec);
				
			}
			//System.out.println(Arrays.toString(arr));
			//System.out.println(list);
		}
		
		// 정렬해 출력
		list.sort(null);
		for (int i=0 ; i<list.size(); i++) {
			sb.append(list.get(i)+" ");
		}
		System.out.println(sb);
	}
}