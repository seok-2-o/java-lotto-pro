package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Lotto {
    public static final Money PRICE = new Money(1_000L);
    public static final int LOTTO_SELECTABLE_SIZE = 6;

    private final Set<LottoNumber> numbers;
    private final boolean manual;

    public Lotto(Set<LottoNumber> numbers, boolean manual) {
        validate(numbers);
        this.numbers = numbers;
        this.manual = manual;
    }

    public static Lotto auto(Set<LottoNumber> numbers) {
       return new Lotto(numbers, false);
    }

    public static Lotto manual(Set<LottoNumber> numbers) {
        return new Lotto(numbers, true);
    }

    private void validate(Set<LottoNumber> numbers) {
        if(numbers.size() != LOTTO_SELECTABLE_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개 보다 작거나 클수 없습니다.");
        }
    }

    public int compareTo(Lotto other) {
        Set<LottoNumber> copy = new HashSet<>(numbers);
        copy.retainAll(other.numbers);
        return copy.size();
    }

    public List<LottoNumber> getSortedNumbers() {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(LottoNumber bonus) {
        return this.numbers.contains(bonus);
    }

    public boolean isManual() {
        return manual;
    }
}
