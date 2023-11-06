package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    private int prizeCount =0;
    private boolean bonus = false;


    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
    public boolean isBonus() {
        return bonus;
    }
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }
    public List<Integer> getNumbers(){
        return numbers;
    }

    // TODO: 추가 기능 구현
}
