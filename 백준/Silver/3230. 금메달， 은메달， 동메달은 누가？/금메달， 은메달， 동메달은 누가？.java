import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        java.util.List<Integer> first_round = new ArrayList<Integer>();
        java.util.List<Integer> second_round = new ArrayList<Integer>();
        
        for (int i=0; i<N; i++) {
        	first_round.add(Integer.parseInt(br.readLine())-1, i+1);
        }
        //System.out.println(first_round.toString());
        
        for (int i=0; i<M; i++) {
        	second_round.add(Integer.parseInt(br.readLine())-1, first_round.get(M-i-1));
        }
        //System.out.println(second_round.toString());

        for (int i=0; i<3; i++) {
        	sb.append(second_round.get(i)+"\n");
        }
        System.out.println(sb);
	}
}