package presentation;

import domain.*;
import presentation.ui.ConsoleView;

import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(ConsoleView.askPurchaseAmount());

        Ticket ticket = Ticket.buy(money, new RandomLottoFactory());
        ConsoleView.printTicket(ticket);

        Lotto previous = ConsoleView.askWinning().stream()
                .map(LottoNumber::of)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
        LottoNumber bonus = LottoNumber.of(ConsoleView.askBonusNumber());
        Winning winning = new Winning(previous, bonus);

        Rewards rewards = ticket.check(winning);
        ConsoleView.printRewards(rewards);
    }
}
