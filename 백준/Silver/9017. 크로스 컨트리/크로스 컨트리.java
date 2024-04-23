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
		
		int T = Integer.parseInt(br.readLine()); // test case
		for (int tc = 0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			int[][] visited = new int[201][7]; // 최대 201팀 최대 6명
			int[] score = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				visited[arr[i]][0]++; // 몇명이 참가했는지 확인
			}
			//System.out.println(Arrays.toString(arr));
			//System.out.println(Arrays.toString(visited[1]));
			
			int index = 1;
			for (int i=1; i<=N; i++) {
				if (visited[arr[i]][0]<6) continue; // 6명이 참가하지 않은 경우 제외
				score[i] = index; // 점수 등록
				for (int j=1; j<=6; j++) { // 팀원 점수 등록
					if (visited[arr[i]][j]==0) {
						visited[arr[i]][j] = index;
						break;
					}
				}
				index++;
			}
			//System.out.println(Arrays.toString(score));
			//System.out.println(Arrays.toString(visited[1]));
			
			int winner = 0; // 현재까지의 가장 상위 팀
			int minSum = Integer.MAX_VALUE; // 현재까지의 가장 상위 합산 점수
			for (int i=1; i<=200; i++) {
				if (visited[i][0]<6) continue; // 6명이 참가하지 않은 경우 제외
				
				int sum = 0;
				for (int j=1; j<=4; j++) { // 상위 4명 점수 합산
					sum += visited[i][j];
				}
				if (sum < minSum) { // 기록 갱신
					winner = i;
					minSum = sum;
				}
				else if (sum == minSum) { // 동점
					if (visited[winner][5] > visited[i][5]) { // 5번째 기록으로 경쟁
						winner = i;
						minSum = sum;
					}
					else if (visited[winner][5] == visited[i][5]) { // 5번째도 동점이면
						if (visited[winner][6] > visited[i][6]) { // 6번째 기록으로 경쟁
							winner = i;
							minSum = sum;
						}
					}
				}
			}
			
			sb.append(winner+"\n");
		}
		
		System.out.println(sb);
	}
}