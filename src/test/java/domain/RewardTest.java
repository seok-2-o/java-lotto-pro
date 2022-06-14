package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RewardTest {

    private static Stream<Arguments> provideReward() {
        return Stream.of(
          Arguments.of(6, false, Reward.FIRST),
          Arguments.of(5, true, Reward.SECOND),
          Arguments.of(5, false, Reward.THIRD),
          Arguments.of(4, false, Reward.FOURTH),
          Arguments.of(3, false, Reward.FIFTH),
          Arguments.of(2, false, Reward.MISS),
          Arguments.of(1, false, Reward.MISS),
          Arguments.of(0, false, Reward.MISS)
        );
    }

    @DisplayName("일치 갯수에 따른 보상을 응답한다.")
    @MethodSource(value = "provideReward")
    @ParameterizedTest
    void valueOf(int matches, boolean bonus, Reward expected) {
        assertThat(Reward.of(matches, bonus)).isEqualTo(expected);
    }
}
