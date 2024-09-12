import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final String INPUT_REG = "^[1-9]*$";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("환영합니다! ");
        while(true) {
            System.out.println("원하시는 번호를 입력하세요.");
            System.out.println("1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 종료하기");
            String input = sc.nextLine();
            try {
                if(!Pattern.matches("[1-3]", input)) {
                    throw new Exception("1~3 사이의 숫자만 입력해 주세요.");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(input.equals("3")) {
                System.out.println("게임을 종료합니다.");
                break;
            }
            if(input.equals("2")) {
                System.out.println("게임 기록***");
                continue;
            }

            BaseballGame baseballGame = new BaseballGame();
            System.out.println("< 게임을 시작합니다. >");
            System.out.println(baseballGame.target);
            while (true) {
                try {
                    System.out.println("숫자를 입력하세요.");
                    input = sc.nextLine();
                    if (!Pattern.matches(INPUT_REG, input)) {
                        throw new Exception("1~9 사이의 숫자만 입력해 주세요.");
                    }
                    if (input.length() != 3) {
                        throw new Exception("정해진 길이의 숫자만 입력해주세요.");
                    }
                    for (int i = 0; i < input.length(); i++) {
                        for (int j = i + 1; j < input.length(); j++) {
                            if (input.charAt(i) == input.charAt(j)) {
                                throw new Exception("중복된 숫자가 있습니다.");
                            }
                        }
                    }

                    String result = baseballGame.countstrike(input) + baseballGame.countball(input);
                    if (result.equals("3스트라이크 ")) {
                        System.out.println("정답입니다!");
                        break;
                    }
                    if (result.isEmpty()) {
                        System.out.println("아웃");
                    } else {
                        System.out.println(result);
                    }
                } catch (Exception e) {
                    System.out.println("올바르지 않은 입력값입니다.");
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }
}