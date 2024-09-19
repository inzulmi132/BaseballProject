import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BaseballGame {
    // 게임 난이도
    private int difficulty;
    // 게이머가 맞춰야 하는 목표
    private String target;
    // 게임 기록 List
    private final List<Integer> scores = new ArrayList<>();

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setTarget() {
        // 1~9 까지의 숫자를 shuffle 을 이용해 섞음
        List<String> list = new ArrayList<>();
        for(int i = 1; i < 10; i++)
            list.add(String.valueOf(i));
        Collections.shuffle(list);
        // 난이도에 알맞게 랜덤하게 섞인 1~9 중 특정 수 만큼 저장함.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.difficulty; i++)
            sb.append(list.get(i));
        this.target = sb.toString();
    }

    public String getTarget() {
        return this.target;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while(true) {
            try {
                System.out.println("숫자를 입력하세요.");
                String input = sc.nextLine();
                // 조건에 맞는 예외 처리
                validateInput(input);
                // 결과를 String 으로 리턴 받음
                String result = countStrike(input) + countBall(input);
                // 시도 횟수 카운팅
                count++;
                // 모두 스트라이크면 모든 자리에 알맞은 수가 있는 것이므로 정답
                if (result.equals(this.difficulty + "스트라이크 ")) {
                    System.out.println("정답입니다!");
                    // 전적 리스트에 시도 횟수 기록
                    scores.add(count);
                    break;
                }
                // 스트라이크와 볼이 없다면 아웃, 있다면 그대로 출력
                if (result.isEmpty()) System.out.println("아웃");
                else System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    void validateInput(String input) throws Exception {
        // 조건에 맞는 예외 처리
        if (!Pattern.matches("^[1-9]*$", input))
            throw new Exception("1~9 사이의 숫자만 입력해 주세요.");
        if (input.length() != this.difficulty)
            throw new Exception(this.difficulty +"개의 숫자를 입력해주세요.");
        for (int i = 0; i < this.difficulty; i++)
            for (int j = i + 1; j < this.difficulty; j++)
                if (input.charAt(i) == input.charAt(j))
                    throw new Exception("중복된 숫자가 있습니다.");
    }

    public void printScores() {
        if (!scores.isEmpty()) {
            System.out.println("< 게임 기록 보기 >");
            for (int i = 0; i < scores.size(); i++)
                System.out.println((i + 1) + "번째 게임 : 시도 횟수 - " + scores.get(i));
        } else System.out.println("게임 전적이 없습니다.");
        System.out.println();
    }
    // 스트라이크 수를 세는 메서드
    public String countStrike(String target) {
        int count = 0;
        for(int i = 0; i < this.target.length(); i++)
            if(this.target.charAt(i) == target.charAt(i))
                count++;
        // 스트라이크가 없으면 빈 String 리턴
        if(count == 0)  return "";
        return count + "스트라이크 ";
    }
    // 볼 수를 세는 메서드
    public String countBall(String target) {
        int count = 0;
        for(int i = 0; i < this.target.length(); i++)
            for(int j = 0; j < target.length(); j++)
                if (i != j)
                    if(this.target.charAt(i) == target.charAt(j))
                        count++;
        // 볼이 없으면 빈 String 리턴
        if(count == 0) return "";
        return count + "볼";
    }
}
