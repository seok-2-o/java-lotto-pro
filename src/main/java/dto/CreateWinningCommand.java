package dto;

import domain.Lotto;
import domain.LottoNumber;
import domain.Winning;

import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public final class CreateWinningCommand {

    public final Set<Integer> previous;
    public final Integer bonus;

    public CreateWinningCommand(Set<Integer> previous, Integer bonus) {
        this.previous = previous;
        this.bonus = bonus;
    }

    public Winning toWinning() {
        Lotto lotto = previous.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), Lotto::new));
        return new Winning(lotto, LottoNumber.of(bonus));
    }
}
