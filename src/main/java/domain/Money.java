package domain;

import java.util.Objects;

public final class Money {

    private final long value;

    public Money(long value) {
        validate(value);
        this.value = value;
    }

    private void validate(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0보다 작을수 없습니다.");
        }
    }

    public boolean isGreaterThanEqual(Money other) {
        return this.value >= other.value;
    }

    public Money add(Money other) {
        if (other.value == 0) {
            return this;
        }
        return new Money(this.value + other.value);
    }

    public Money multiply(int multiplicand) {
        if (multiplicand == 1) {
            return this;
        }
        return new Money(this.value * multiplicand);
    }

    public Money subtract(Money other) {
        if (other.value == 0) {
            return this;
        }
        return new Money(this.value - other.value);
    }

    public double calculateRateOfReturn(Money investment) {
        double rateOfReturn = ((this.value - investment.value) / ((double) investment.value));
        return Math.round(rateOfReturn * 100) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}
