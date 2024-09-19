import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BaseballGame baseballGame = new BaseballGame();

        System.out.print("환영합니다! ");
// 쉬운 게임 종료를 위한 label
label: while(true) {
            System.out.println("원하시는 번호를 입력하세요.");
            System.out.println("0. 자리수 설정 | 1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 종료하기");
            String input = sc.nextLine();

            // 난이도, 기본적으로는 3자리 게임
            int difficulty = 3;
            // switch 를 이용
            switch (input) {
                // 3 입력 시 break label 으로 전체 반복문 종료.
                case "3": System.out.println("< 게임을 종료합니다. >"); break label;
                // 2 입력 시 게임 전적 순서대로 출력
                case "2": baseballGame.printScores(); break;
                // 0 입력 시 난이도 재설정
                case "0":
                    // 예외처리를 위한 반복문
                    while(true) {
                        System.out.println("설정하고자 하는 자리수를 입력하세요.");
                        input = sc.nextLine();
                        // 난이도 입력 시 예외 처리
                        try {
                            if (!Pattern.matches("[3-5]", input))
                                throw new Exception("3~5 사이의 숫자만 입력해주세요.\n");
                            difficulty = Integer.parseInt(input);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                // break 없이 이어서 그대로 실행.
                case "1":
                    System.out.println("< 게임을 시작합니다. >");
                    baseballGame.setDifficulty(difficulty);
                    baseballGame.setTarget();
                    System.out.println(baseballGame.getTarget());
                    baseballGame.play();
                    break;
                // 0~3 이 아닌 입력은 무시.
                default: System.out.println("0~3 사이의 숫자만 입력해 주세요.\n");
            }
        }
    }
}