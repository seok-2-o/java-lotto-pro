package domain;

import dto.BuyManualLottosCommand;

import java.util.ArrayList;
import java.util.List;

import static domain.Lotto.PRICE;
import static java.util.stream.Collectors.*;

public final class Ticket {

    private final List<Lotto> elements;

    private Ticket(List<Lotto> elements) {
        this.elements = elements;
    }

    public static Ticket buy(Money money, BuyManualLottosCommand command, LottoFactory factory) {

        List<Lotto> lottos = new ArrayList<>();

        List<Lotto> manuals = command.toManuals();
        money = money.subtract(PRICE.multiply(manuals.size()));
        lottos.addAll(manuals);

        while (money.isGreaterThanEqual(PRICE)) {
            lottos.add(factory.make());
            money = money.subtract(PRICE);
        }

        return new Ticket(lottos);
    }

    public Rewards check(Winning wining) {
        return elements.stream()
                .map(wining::check)
                .collect(collectingAndThen(toList(), Rewards::new));
    }

    public List<Lotto> getElements() {
        return elements.stream()
                .collect(toUnmodifiableList());
    }
}
