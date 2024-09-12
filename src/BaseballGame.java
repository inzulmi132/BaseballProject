import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseballGame {
    // 게이머가 맞춰야 하는 대상
    String target;

    public BaseballGame(int difficulty) {
        // 1~9 까지의 숫자를 shuffle 을 이용해 섞음
        List<String> list = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Collections.shuffle(list);

        // 난이도에 알맞게 랜덤하게 섞인 1~9 중 특정 수 만큼 저장함.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < difficulty; i++) {
            sb.append(list.get(i));
        }
        this.target = sb.toString();
    }

    // 스트라이크 수를 세는 메서드
    public String countstrike(String target) {
        int count = 0;
        for(int i = 0; i < this.target.length(); i++) {
            if(this.target.charAt(i) == target.charAt(i)) {
                count++;
            }
        }
        // 스트라이크가 없으면 빈 String 리턴
        if(count == 0)  return "";
        return count + "스트라이크 ";
    }
    // 볼 수를 세는 메서드
    public String countball(String target) {
        int count = 0;
        for(int i = 0; i < this.target.length(); i++) {
            for(int j = 0; j < target.length(); j++) {
                if (i == j) continue;
                if(this.target.charAt(i) == target.charAt(j)) {
                    count++;
                }
            }
        }
        // 볼이 없으면 빈 String 리턴
        if(count == 0) return "";
        return count + "볼";
    }
}
