package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.Lotto;
import lotto.model.PrizeLotto;
import lotto.model.UserLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    NumberValidator numberValidator = new NumberValidator();
    UserLotto userLotto = new UserLotto();
    Lotto lotto;
    PrizeLotto prizeLotto;

    public void proceedLotto(){
        createUserLottoNum();
        for(Lotto lo : userLotto.getUsersLotto()){
            System.out.println("로또 번호:"+lo.getNumbers());
        }
        prizeLotto = createPrizeNum();
        System.out.println("당첨 번호 ="+prizeLotto.getPrizeNumbers());
        System.out.println("보너스 번호="+ prizeLotto.getBonusNum());
    }
    public PrizeLotto createPrizeNum(){
        return new PrizeLotto(numberValidator.prizeLottoNumValidator(inputView.enterPrizeLottoNum()), Integer.parseInt(inputView.enterBonusNum()));
    }

    public void createUserLottoNum(){
        int buyLottoNum = numberValidator.buyLottoMoneyValidator(inputView.enterBuyLottoAmount());
        List<Lotto> lotto = generateLottoNum(buyLottoNum);
        userLotto.setUsersLotto(lotto);
    }

    public List<Lotto> generateLottoNum(int num){
        List<Lotto> lotto = new ArrayList<Lotto>(num);
        for(int i = 0 ; i<num ; i++){
            Lotto lotto1 = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lotto.add(lotto1);
        }
        return lotto;
    }
}
