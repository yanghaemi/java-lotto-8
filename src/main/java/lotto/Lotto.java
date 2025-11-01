package lotto;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

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
        Set<Integer> dup = new LinkedHashSet<>();

        for(int n : numbers){
            if(!seen.add(n)){   // 중복이 되면 set은 false가 뜸
                dup.add(n);     // 중복 해시셋에 넣어준다.
            }
        }

        if(!dup.isEmpty()){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복 될 수 없습니다.");
        }
    }

    public int correct(List<Integer> lottoNumbers, int bonusNum){
        int correct= 0;
        Boolean bonusFlag = false;

        for(int i =0;i<lottoNumbers.size();i++){
            for(int j=0;j<lottoNumbers.size();j++){
                correct = compare(lottoNumbers.get(i), numbers.get(j), correct);
            }
            bonusFlag = bonus(lottoNumbers.get(i), bonusNum);
        }

        return rank(correct, bonusFlag);
    }

    private int compare(int lottoNumber, int number, int correct){

        if(lottoNumber == number){
            return ++correct;
        }
        return correct; // 증가 안 함
    }

    private Boolean bonus(int lottoNumber, int bonusNum){
        if(lottoNumber == bonusNum){
            return true;
        }
        return false;
    }

    private int rank(int correct, Boolean bonusFlag){
        if(correct == 6) return 1; // 1등
        if(correct == 5){
            if(bonusFlag) return 2; // 2등
            return 3; // 3등
        }
        if(correct == 4) return 4;  // 4등
        if(correct == 3) return 5;  // 5등

        return 0;
    }

    public int prizeMoney(int rank){
        if(rank==1) return 2000000000;
        if(rank==2) return 30000000;
        if(rank==3) return 1500000;
        if(rank==4) return 50000;
        if(rank==5) return 5000;

        return 0;
    }

    public void rateOfReturn(int p_purchaseCost, int p_sumPrizeMoney){
        double purchaseCost = p_purchaseCost;
        double sumPrizeMoney = p_sumPrizeMoney;
        double rate = sumPrizeMoney/purchaseCost;
        rate*=100;
        System.out.println("총 수익률은 "+ Math.round(rate*100)/100.0 +"%입니다.");
    }

    public void resultOutput(List<Integer> result){
        System.out.println("3개 일치 (5,000원) - "+result.get(4)+"개");
        System.out.println("4개 일치 (50,000원) - "+result.get(3)+"개");
        System.out.println("5개 일치 (1,500,000원) - "+result.get(2)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+result.get(1)+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+result.get(0)+"개");
    }
}
