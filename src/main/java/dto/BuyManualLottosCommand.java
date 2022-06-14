package dto;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public class BuyManualLottosCommand {

    private final List<Set<Integer>> sheet;

    public BuyManualLottosCommand(List<Set<Integer>> sheet) {
        this.sheet = sheet;
    }

    public List<Lotto> toManuals() {
        return sheet.stream()
                .map(toManual())
                .collect(Collectors.toUnmodifiableList());
    }

    private Function<Set<Integer>, Lotto> toManual() {
        return it -> it.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), Lotto::manual));
    }
}
