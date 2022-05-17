package presentation.ui;

import domain.*;
import dto.BuyManualLottosCommand;
import dto.CreateWinningCommand;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public final class ConsoleView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleView() {
    }

    public static void printTicket(Ticket ticket) {
        List<Lotto> lottos = ticket.getElements();
        long countOfManuals = lottos.stream()
                        .filter(Lotto::isManual)
                        .count();
        System.out.println("수동으로 " + countOfManuals +"장, 자동으로 " +  (lottos.size() - countOfManuals) + "장을 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println("[" + toJoinString(lotto) + "]");
        }

    }

    private static String toJoinString(Lotto lotto) {
        return lotto.getSortedNumbers().stream()
                .map(LottoNumber::value)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static void printRewards(Rewards rewards) {
        System.out.println("당첨 통계\n---------");
        System.out.println("3개 일치(5,000원)-" + rewards.count(Reward.FIFTH) + "개");
        System.out.println("4개 일치(50,000원)-" + rewards.count(Reward.FOURTH) + "개");
        System.out.println("5개 일치(1,500,000원)-" + rewards.count(Reward.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30,000,000원)-" + rewards.count(Reward.SECOND) + "개");
        System.out.println("6개 일치(2,000,000,000원)-" + rewards.count(Reward.FIRST) + "개");
        System.out.println("총 수익률은 " + rewards.calculateRateOfReturn() + "입니다.");
    }

    public static CreateWinningCommand askWinning() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Set<Integer> previous = Arrays.stream(SCANNER.nextLine().split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonus =  Integer.parseInt(SCANNER.nextLine());
        return new CreateWinningCommand(previous, bonus);
    }

    public static long askPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Long.parseLong(SCANNER.nextLine());
    }

    public static BuyManualLottosCommand askBuyManually() {
        List<Set<Integer>> manuals = new ArrayList<>();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfManual = Integer.parseInt(SCANNER.nextLine());
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int idx = 0; idx < numberOfManual; idx ++) {
            Arrays.stream(SCANNER.nextLine().split(","))
                    .map(Integer::valueOf)
                    .collect(collectingAndThen(Collectors.toSet(), manuals::add));
        }
        return new BuyManualLottosCommand(manuals);
    }
}
