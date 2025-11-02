package lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public static enum Rank{
        FIRST(1,6,false, 2000000000),
        SECOND(2,5,true, 30000000),
        THIRD(3, 5, false, 1500000),
        FOURTH(4,4,false, 50000),
        FIFTH(5,3,false, 5000),
        NONE(0,0,false,0);

        private final int number;   // 등수 번호
        private final int matches;  // 일치 개수
        private final boolean bonus;// 보너스 필요 여부(SECOND만 true)
        private final int prize;    // 상금

        Rank(int number, int matches, boolean bonus, int prize) {
            this.number = number;
            this.matches = matches;
            this.bonus = bonus;
            this.prize = prize;
        }

        public int getNumber() { return number; }
        public int getPrize()  { return prize;  }

        public static Rank rank(int correct, boolean bonusMatched) {
            if (correct == 6) return FIRST;
            if(correct == 5){
                if(bonusMatched) return SECOND; // 2등
                return THIRD; // 3등
            }
            if (correct == 4) return FOURTH;
            if (correct == 3) return FIFTH;
            return NONE;
        }
    }

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkForDuplicates(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현

    private void checkForDuplicates(List<Integer> numbers){

        Set<Integer> seen = new HashSet<>();

        for(int n : numbers){
            if(!seen.add(n)){   // 중복이 되면 set은 false가 뜸
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복 될 수 없습니다.");
            }
        }
    }

    public Rank correct(List<Integer> lottoNumbers, int bonusNum){
        int correct = (int) numbers.stream().filter(lottoNumbers::contains).count();
        boolean bonusMatched = numbers.contains(bonusNum);
        return Rank.rank(correct, bonusMatched);
    }

    public void rateOfReturn(int p_purchaseCost, int p_sumPrizeMoney){
        double purchaseCost = p_purchaseCost;
        double sumPrizeMoney = p_sumPrizeMoney;
        double rate = sumPrizeMoney/purchaseCost;
        rate*=100;
        System.out.println("총 수익률은 "+ Math.round(rate*100)/100.0 +"%입니다.");
    }

    public void resultOutput(Map<Rank, Integer> counts){
        System.out.println("3개 일치 (5,000원) - " + counts.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (50,000원) - " + counts.getOrDefault(Rank.FOURTH, 0) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + counts.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + counts.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + counts.getOrDefault(Rank.FIRST, 0) + "개");
    }
}
