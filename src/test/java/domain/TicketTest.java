package domain;

import domain.helper.FixedLottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

import static domain.LottoNumber.of;
import static java.util.Set.of;
import static org.assertj.core.api.Assertions.assertThat;

class TicketTest {

    @DisplayName("입력받은 금액만큼 로또를 구매한다.")
    @Test
    void buy() {
        Ticket ticket = Ticket.buy(new Money(5_000L), new RandomLottoFactory());
        assertThat(ticket.getElements()).hasSize(5);
    }


    @DisplayName("구매한 로또와 당첨 번호를 비교한다.")
    @Test
    void check() {
        //given
        Deque<Lotto> fixture = new LinkedList<>();
        fixture.push(Lotto.manual(Set.of(of(1), of(2), of(3), of(4), of(5), of(6))));
        fixture.push(Lotto.manual(Set.of(of(1), of(2), of(3), of(4), of(5), of(7))));
        fixture.push(Lotto.manual(Set.of(of(1), of(2), of(3), of(7), of(8), of(9))));
        Lotto previous = Lotto.manual(Set.of(of(1), of(2), of(3), of(4), of(5), of(6)));
        Ticket ticket = Ticket.buy(new Money(3_000L), new FixedLottoFactory(fixture));
        //when
        Winning winning = new Winning(previous, LottoNumber.of(7));
        Rewards rewards = ticket.check(winning);

        assertThat(rewards.count(Reward.FIRST)).isEqualTo(1);
        assertThat(rewards.count(Reward.SECOND)).isEqualTo(1);
        assertThat(rewards.count(Reward.FIFTH)).isEqualTo(1);
        assertThat(rewards.count(Reward.MISS)).isZero();
    }
}
