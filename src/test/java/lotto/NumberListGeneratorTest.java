package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberListGeneratorTest {
    @Test
    void generateRandomNumbers() {
        final List<Integer> result = NumberListGenerator.generateRandomNumbers(6, 1, 45);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(6);
        for (Integer integer : result) {
            assertThat(integer).isGreaterThanOrEqualTo(1);
            assertThat(integer).isLessThanOrEqualTo(45);
        }
    }
}