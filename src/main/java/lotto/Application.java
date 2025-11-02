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
                throw new IllegalArgumentException("[ERROR] 1000원 단위로 나누어 떨어져야 합니다.");
            }
            int lottoes = purchaseCost / 1000;
            System.out.println("\n"+lottoes+"개를 구매했습니다.");

            List<List<Integer>> lottoNumbers = new ArrayList<>();
            for(int i=0;i<lottoes;i++) {
                List<Integer> raw = Randoms.pickUniqueNumbersInRange(1, 45, 6); // 랜덤 리스트가 불변 리스트라서
                List<Integer> ticket = new ArrayList<>(raw);    // 복사를 해준 뒤, 정렬하여 lottoNumbers에 추가
                ticket.sort(Comparator.naturalOrder());
                lottoNumbers.add(ticket);
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

            Map<Lotto.Rank, Integer> counts = new EnumMap<>(Lotto.Rank.class);
            int sumPrizeMoney = 0; // 총 상금
            List<Integer> result = new ArrayList<>(Arrays.asList(0,0,0,0,0));   // 각 등수에 당첨된 로또 개수
            for(int i =0;i<lottoes;i++){
                Lotto.Rank rank = lotto.correct(lottoNumbers.get(i), bonusNum); // Rank 반환
                if (rank != Lotto.Rank.NONE) {
                    counts.merge(rank, 1, Integer::sum);        // 개수 +1
                    sumPrizeMoney += rank.getPrize();           // 상금 누적
                }
            }


            // 출력
            System.out.println("\n당첨 통계\n---");
            lotto.resultOutput(counts);
            lotto.rateOfReturn(purchaseCost, sumPrizeMoney);
        }catch(NumberFormatException e){
            System.out.println("[ERROR] 정수형 변환 오류");
        }
    }
}
