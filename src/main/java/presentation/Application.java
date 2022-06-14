package presentation;

import domain.*;
import dto.BuyManualLottosCommand;
import presentation.ui.ConsoleView;

import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(ConsoleView.askPurchaseAmount());
        BuyManualLottosCommand manuals = ConsoleView.askBuyManually();
        Ticket ticket = Ticket.buy(money, manuals, new RandomLottoFactory());
        ConsoleView.printTicket(ticket);
        Winning winning = ConsoleView.askWinning().toWinning();
        Rewards rewards = ticket.check(winning);
        ConsoleView.printRewards(rewards);
    }
}
