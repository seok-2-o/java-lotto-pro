package presentation;

import domain.*;
import presentation.ui.ConsoleView;

import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(ConsoleView.askPurchaseAmount());

        Ticket ticket = Ticket.buy(money, new RandomLottoFactory());
        ConsoleView.printTicket(ticket);
        Winning winning = ConsoleView.askWinning().toWinning();
        Rewards rewards = ticket.check(winning);
        ConsoleView.printRewards(rewards);
    }
}
