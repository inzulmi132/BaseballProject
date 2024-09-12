import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseballGame {
    String target;

    public BaseballGame(int n) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Collections.shuffle(list);

        this.target = "";
        for(int i = 0; i < n; i++) {
            this.target += list.get(i);
        }
    }

    public String countstrike(String target) {
        int count = 0;
        for(int i = 0; i < this.target.length(); i++) {
            if(this.target.charAt(i) == target.charAt(i)) {
                count++;
            }
        }
        if(count == 0) {
            return "";
        }
        return count + "스트라이크 ";
    }
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
        if(count == 0) {
            return "";
        }
        return count + "볼";
    }
}
