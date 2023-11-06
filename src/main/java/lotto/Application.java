package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Application {
    static PrizeMoney prizeMoney = new PrizeMoney();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ArrayList<Lotto> lottos = new ArrayList<Lotto>();


        System.out.println("구입금액을 입력해 주세요.");
        int myMoney = Integer.parseInt(Console.readLine());
        int buyLottoNum = myMoney / 1000 ;

        connectLotto(lottos,buyLottoNum);

        printMyLottoNums(lottos,buyLottoNum);


        List<Integer> prizeNums = inputPrizeNumMoneys();
        System.out.println(prizeNums);
        int bonusNum = inputBonusNum();
        howManyPrizeNum(lottos,prizeNums);

        int[] arr = calculateHowManyNum(lottos,prizeMoney);

        calculateSumMoney(prizeMoney,arr);

        System.out.println(prizeMoney.getPrizeSumMoney());

        printPrizeNum(arr);
        System.out.println("prizeMoneySum == "+prizeMoney.getPrizeSumMoney());

        prizeGetMoneyRate(prizeMoney , myMoney);

    }
    public static void prizeGetMoneyRate(PrizeMoney p ,int myMoney){
        double d = (p.getPrizeSumMoney()/(double)myMoney)*100;
        System.out.println("총 수익률은 "+d+"%입니다.");
    }
    public static void printPrizeNum(int[] arr){
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - "+arr[3]+"개");
        System.out.println("4개 일치 (50,000원) - "+arr[4]+"개");
        System.out.println("5개 일치 (1,500,000원) - "+arr[5]+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+arr[7]+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+arr[6]+"개");
    }

    public static void calculateSumMoney(PrizeMoney p,int[] arr){
        for(int i=3 ; i<8 ; i++){
            if(i==3){
                p.setPrizeSumMoney(arr[i]*5000);
            }
            if(i==4){
                p.setPrizeSumMoney(arr[i]*50000);
            }
            if(i==5){
                p.setPrizeSumMoney(arr[i]*1500000);
            }
            if(i==6){
                p.setPrizeSumMoney(arr[i]*2000000000);
            }
        }
    }

    public static int[] calculateHowManyNum(ArrayList<Lotto> lottos,PrizeMoney p){
        int[] arr = new int[8];
        for(Lotto lo : lottos){
            if(lo.isBonus()){
                arr[7]++;
                p.setPrizeSumMoney(30000000);
            }
            if(!lo.isBonus()){
                arr[lo.getPrizeCount()]++;
            }
        }
        return arr;

    }

    public static void howManyPrizeNum(ArrayList<Lotto> lottos,List<Integer> list) {
        // 로또 산 갯수만큼 반복하고 각각 몇개씩 맞췄는지 계산
        for(Lotto lo : lottos){
            lo.setPrizeCount(countLottoNum(lo.getNumbers(),list));
            if(lo.getPrizeCount() == 5){
                isBonusNum(lo,list);
            }
        }
    }
    public static void isBonusNum(Lotto lo,List<Integer> list){
        if(lo.getNumbers().contains(list.get(list.size())-1)){
            lo.setBonus(true);
        }
    }

    public static int countLottoNum(List<Integer> lo,List<Integer> list){
        int count =0;
        for(int i=0 ; i< lo.size() ; i++){
            if(lo.contains(list.get(i))){
                count++;
            }
        }
        return count;
    }

    public static int inputBonusNum(){
        System.out.println("");
        System.out.println("보너스 번호를 입력해 주세");
        return Integer.parseInt(Console.readLine());
    }

    public static List<Integer> inputPrizeNumMoneys(){
        System.out.println("");
        System.out.println("당첨 번호를 입력해 주세요.");
        List<String> s = List.of(Console.readLine().split(","));
        List<Integer> newList = new ArrayList<>();
        for(String ss: s){
            newList.add(Integer.parseInt(ss));
        }
        return newList;
    }

    public static List<Integer> generateLottoNum(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }

    public static void connectLotto(ArrayList<Lotto> lottos,int tryNum){
        for(int i=0 ; i<tryNum ;i++){
            lottos.add(new Lotto(generateLottoNum()));
        }
    }

    public static void printMyLottoNums(ArrayList<Lotto> lottos,int tryNum){
        System.out.println(tryNum+"개를 구매했습니다.");
        for(Lotto lo : lottos){
            System.out.println(lo.getNumbers());
        }
//        for(int i=0; i<tryNum ;i++){
//            System.out.println(lottos.get(i).getNumbers());
//        }
    }
}
class PrizeMoney{
    private int prizeSumMoney = 0;

    public int getPrizeSumMoney() {
        return prizeSumMoney;
    }

    public void setPrizeSumMoney(int prizeSumMoney) {
        this.prizeSumMoney += prizeSumMoney;
    }

    enum PriceRoll{
        THREE(3,"5000"), FOUR(4,"50000"), FIVE(5,"1500000"),SIX(6,"2000000000");

        private final int value;
        private final String symbol;

        PriceRoll(int i, String value){
            this.value = i;
            this.symbol = value;
        }
    }
}