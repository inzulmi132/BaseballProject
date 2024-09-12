import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 게임 기록 저장용 List
        List<Integer> list = new ArrayList<>();

        System.out.print("환영합니다! ");
// 쉬운 게임 종료를 위한 label
label:
        while(true) {
            System.out.println("원하시는 번호를 입력하세요.");
            System.out.println("0. 자리수 설정 | 1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 종료하기");
            String input = sc.nextLine();
            // 0~3 만 입력 받고 예외 처리
            try {
                if(!Pattern.matches("[0-3]", input))
                    throw new Exception("0~3 사이의 숫자만 입력해 주세요.\n");
            } catch(Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            // 난이도, 기본적으로는 3자리 게임
            int difficulty = 3;
            switch (input) {
                // 3 입력 시 break label 으로 전체 반복문 종료.
                case "3": System.out.println("< 게임을 종료합니다. >"); break label;
                // 2 입력 시 게임 전적 순서대로 출력
                case "2":
                    if (!list.isEmpty()) {
                        System.out.println("< 게임 기록 보기 >");
                        for (int i = 0; i < list.size(); i++)
                            System.out.println((i + 1) + "번째 게임 : 시도 횟수 - " + list.get(i));
                    } else System.out.println("게임 전적이 없습니다.");
                    System.out.println();
                    continue;
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
            }

            System.out.println("< 게임을 시작합니다. >");
            // BaseballGame 생성자로 target 생성
            BaseballGame baseballGame = new BaseballGame(difficulty);
            // 시도 횟수 기록
            int count = 0;
            System.out.println(baseballGame.target);
            while (true) {
                try {
                    System.out.println("숫자를 입력하세요.");
                    input = sc.nextLine();
                    // 조건에 맞는 예외 처리
                    if (!Pattern.matches("^[1-9]*$", input))
                        throw new Exception("1~9 사이의 숫자만 입력해 주세요.");
                    if (input.length() != difficulty)
                        throw new Exception("정해진 길이의 숫자만 입력해주세요.");
                    for (int i = 0; i < input.length(); i++)
                        for (int j = i + 1; j < input.length(); j++)
                            if (input.charAt(i) == input.charAt(j))
                                throw new Exception("중복된 숫자가 있습니다.");

                    // 결과를 String 으로 리턴 받음
                    String result = baseballGame.countstrike(input) + baseballGame.countball(input);
                    // 시도 횟수 카운팅
                    count++;
                    // n 스트라이크면 모든 자리에 알맞은 수가 있는 것이므로 정답
                    if (result.equals(difficulty + "스트라이크 ")) {
                        System.out.println("정답입니다!");
                        // 전적 리스트에 시도 횟수 기록
                        list.add(count);
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
    }
}