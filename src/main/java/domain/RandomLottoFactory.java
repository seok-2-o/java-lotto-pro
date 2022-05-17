package domain;

public class RandomLottoFactory implements LottoFactory{

    @Override
    public Lotto make() {
        return Lotto.auto(LottoNumber.randomNumbers(Lotto.LOTTO_SELECTABLE_SIZE));
    }
}
