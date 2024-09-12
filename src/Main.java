import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        System.out.print("환영합니다! ");
        label:
        while(true) {
            System.out.println("원하시는 번호를 입력하세요.");
            System.out.println("0. 자리수 설정 | 1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 종료하기");
            int n = 3;
            String input = sc.nextLine();
            try {
                if(!Pattern.matches("[0-3]", input)) {
                    throw new Exception("0~3 사이의 숫자만 입력해 주세요.");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            switch (input) {
                case "3": System.out.println("< 게임을 종료합니다. >"); break label;
                case "2":
                    if (!list.isEmpty()) {
                        System.out.println("< 게임 기록 보기 >");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + "번째 게임 : 시도 횟수 - " + list.get(i));
                        }
                    } else System.out.println("게임 전적이 없습니다.");
                    continue;
                case "0":
                    System.out.println("설정하고자 하는 자리수를 입력하세요.");
                     input = sc.nextLine();
                     try {
                         if(!Pattern.matches("[3-5]", input)) {
                             throw new Exception("3~5 사이의 숫자만 입력해주세요.");
                         }
                         n = Integer.parseInt(input);
                     } catch(Exception e) {
                         System.out.println(e.getMessage());
                     }
            }

            BaseballGame baseballGame = new BaseballGame(n);
            int count = 0;
            System.out.println("< 게임을 시작합니다. >");
            System.out.println(baseballGame.target);
            while (true) {
                try {
                    System.out.println("숫자를 입력하세요.");
                    input = sc.nextLine();
                    if (!Pattern.matches("^[1-9]*$", input)) {
                        throw new Exception("1~9 사이의 숫자만 입력해 주세요.");
                    }
                    if (input.length() != n) {
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
                    count++;
                    if (result.equals(n + "스트라이크 ")) {
                        System.out.println("정답입니다!");
                        list.add(count);
                        break;
                    }
                    if (result.isEmpty()) {
                        System.out.println("아웃");
                    } else {
                        System.out.println(result);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }
}