package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;


import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {

    private Lotto lotto;

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseCost;
        try {

            purchaseCost = Integer.parseInt(readLine());    // 구입 금액

            if(purchaseCost % 1000 != 0) {
                throw new IllegalArgumentException("1000원 단위로 나누어 떨어져야 합니다.");
            }
            var lottos = purchaseCost / 1000;
            System.out.println(lottos+"개를 구매했습니다.");

            for(int i=0;i<lottos;i++) {
    //            lottoNum[i].add(Randoms.pickNumberInRange(1, 45, 6));
                System.out.println(Randoms.pickUniqueNumbersInRange(1, 45,6));
            }

            System.out.println("당첨 번호를 입력해 주세요.");
            String winningNum = readLine();
            String[] winningNumSplit = winningNum.split(",");

            System.out.println(winningNumSplit);

            System.out.println("보너스 번호를 입력해 주세요.");
            int bonusNum = Integer.parseInt(readLine());
            
            // 출력
            System.out.println("당첨 통계\n---");
            for(int i =0;i<lottos;i++){
                System.out.println("개 일치 ("+") - "+"개");

                System.out.println("개 일치, 보너스 볼 일치 ("+") - "+"개");
            }
            System.out.println("총 수익률은 "+"%입니다.");
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("정수형 변환 실패");
        }
    }
}
