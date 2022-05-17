package domain;

public class Winning {

    private final Lotto lotto;
    private final LottoNumber bonus;

    public Winning(Lotto lotto, LottoNumber bonus) {
        validate(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    private void validate(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복 될 수 없습니다.");
        }
    }

    public Reward check(Lotto lotto) {
        return Reward.of(lotto.compareTo(this.lotto), lotto.contains(bonus));
    }
}
