package presentation;

import domain.*;
import presentation.ui.ConsoleView;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(ConsoleView.askPurchaseAmount());
        Ticket ticket = Ticket.buy(money, new RandomLottoFactory());
        ConsoleView.printTicket(ticket);
        Winning winning = new Winning(ConsoleView.askWinning(), ConsoleView.askBonusNumber());
        Rewards rewards = ticket.check(winning);
        ConsoleView.printRewards(rewards);
    }
}
