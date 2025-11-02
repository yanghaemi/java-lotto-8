package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @Test
    @DisplayName("6개 일치 → 1등")
    void 여섯개_일치_1등() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto.Rank rank = ticket.correct(List.of(1, 2, 3, 4, 5, 6), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 + 보너스 일치 -> 2등")
    void 다섯개_보너스포함_2등() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto.Rank rank = ticket.correct(List.of(1, 2, 3, 4, 5, 40), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("5개 일치(보너스 X) → 3등")
    void 다섯개_보너스없음_3등() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto.Rank rank = ticket.correct(List.of(1, 2, 3, 4, 5, 40), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("4개 일치 → 4등")
    void 네개_일치_4등() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 40, 41));
        Lotto.Rank rank = ticket.correct(List.of(1, 2, 3, 4, 5, 6), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("3개 일치 → 5등")
    void 세개_일치_5등() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 40, 41, 42));
        Lotto.Rank rank = ticket.correct(List.of(1, 2, 3, 4, 5, 6), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("2개 이하 일치 → 낙첨(0)")
    void 두개_이하_낙첨() {
        Lotto ticket = new Lotto(List.of(1, 2, 40, 41, 42, 43));
        Lotto.Rank rank = ticket.correct(List.of(10, 20, 30, 40, 50, 60), 7);
        int result = rank.getNumber();
        assertThat(result).isEqualTo(0);
    }
}
