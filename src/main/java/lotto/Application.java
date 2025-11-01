package lotto;

import camp.nextstep.edu.missionutils.Randoms;


import java.util.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        try {
            System.out.println("구입금액을 입력해 주세요.");
            int purchaseCost = Integer.parseInt(readLine());    // 구입 금액

            if(purchaseCost % 1000 != 0) {
                throw new IllegalArgumentException("1000원 단위로 나누어 떨어져야 합니다.");
            }
            int lottoes = purchaseCost / 1000;
            System.out.println("\n"+lottoes+"개를 구매했습니다.");

            List<List<Integer>> lottoNumbers = new ArrayList<>();
            for(int i=0;i<lottoes;i++) {
                lottoNumbers.add(i,Randoms.pickUniqueNumbersInRange(1, 45, 6));
                lottoNumbers.get(i).sort(Comparator.naturalOrder());
                System.out.println(lottoNumbers.get(i));
            }

            System.out.println("\n당첨 번호를 입력해 주세요.");
            String winningNum = readLine();
            List<Integer> numbers = Arrays.stream(winningNum.split(","))
                    .map(s-> s.trim())  // 문자열 앞뒤 공백 없애는 메서드
                    .filter(s -> !s.isEmpty())
                    .map(s -> Integer.parseInt(s))
                    .toList();

            Lotto lotto = new Lotto(numbers);

            System.out.println(numbers);

            System.out.println("\n보너스 번호를 입력해 주세요.");
            int bonusNum = Integer.parseInt(readLine());

            int sumPrizeMoney = 0; // 총 상금
            List<Integer> result = new ArrayList<>(Arrays.asList(0,0,0,0,0));   // 각 등수에 당첨된 로또 개수
            for(int i =0;i<lottoes;i++){

                int rank = lotto.correct(lottoNumbers.get(i), bonusNum);

                if(rank > 0){
                    int dump = result.get(rank-1);
                    result.set(rank-1, ++dump);
                    sumPrizeMoney += lotto.prizeMoney(rank);
                }
            }


            // 출력
            System.out.println("\n당첨 통계\n---");
            lotto.resultOutput(result);
            lotto.rateOfReturn(purchaseCost, sumPrizeMoney);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("정수형 변환 실패");
        }
    }
}
