import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] gear, newGear;
	static int K, result;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8];
		for (int i=0; i<4; i++) {
			String str = br.readLine();
			for (int j=0; j<8; j++) {
				gear[i][j] = str.charAt(j) - '0';
			}
		}
		//CheckStatus();
		
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken())-1; // 톱니 번호
			int dir = Integer.parseInt(st.nextToken()); // 1: 시계, -1: 반시계
			newGear = new int[4][8];
			
			// 첫번째 기어부터 돌려줍니다.
			// 시계방향
			if (dir == 1) {
				rotateRight(num);
			}
			// 반시계방향
			else if (dir == -1) {
				rotateLeft(num);
			}
			
			int nextGear = num;
			int nextDir = dir;
			while (true) { // 왼쪽부터
				nextGear--;
				nextDir = nextDir*-1;
				if (nextGear<0) break;
				if (gear[nextGear][2] == gear[nextGear+1][6])  {
					// 방향이 같으면 똑같이 넣고 끝냅니다
					for (int j=nextGear; j>=0; j--) {
						for (int k=0; k<8; k++) {
							newGear[j][k] = gear[j][k];
						}
					}
					break;
				}
				// 시계방향
				if (nextDir == 1) {
					rotateRight(nextGear);
				}
				// 반시계방향
				else if (nextDir == -1) {
					rotateLeft(nextGear);
				}
			}
			nextGear = num;
			nextDir = dir;
			while (true) { // 오른쪽부터
				nextGear++;
				nextDir = nextDir*-1;
				if (nextGear>3) break;
				if (gear[nextGear][6] == gear[nextGear-1][2]) {
					// 방향이 같으면 똑같이 넣고 끝냅니다
					for (int j=nextGear; j<4; j++) {
						for (int k=0; k<8; k++) {
							newGear[j][k] = gear[j][k];
						}
					}
					break;
				}
				// 시계방향
				if (nextDir == 1) {
					rotateRight(nextGear);
				}
				// 반시계방향
				else if (nextDir == -1) {
					rotateLeft(nextGear);
				}
			}
			for (int j=0; j<4; j++) {
				for (int k=0; k<8; k++) {
					gear[j][k] = newGear[j][k];
				}
			}
			//CheckStatus();
		}
		
		// 점수
		result = 0;
		for (int i=0; i<4; i++) {
			int twelve = gear[i][0];
			if (twelve==1) {
				if (i==0) result += 1;
				else if (i==1) result += 2;
				else if (i==2) result += 4;
				else if (i==3) result += 8;
			}
		}
		
		System.out.println(result);
	}
	
	public static void rotateRight(int num) {
		int last = gear[num][7]; // 마지막 톱니 저장
		for (int j=7; j>0; j--) {
			newGear[num][j] = gear[num][j-1]; // 한칸씩 밀기
		}
		newGear[num][0] = last; // 첫번째에 마지막 톱니 저장
	}
	
	public static void rotateLeft(int num) {
		int first = gear[num][0]; // 첫번째 톱니 저장
		for (int j=1; j<8; j++) {
			newGear[num][j-1] = gear[num][j]; // 한칸씩 땡기기
		}
		newGear[num][7] = first; // 마지막에 첫째 톱니 저장
	}
	

	public static void CheckStatus() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<8; j++) {
				System.out.print(gear[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------");
			
	}
}