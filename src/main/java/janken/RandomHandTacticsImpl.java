package janken;

public class RandomHandTacticsImpl implements InterfaceTactics{

	public RandomHandTacticsImpl(){
		// コンストラクタ
	}

	/* (非 Javadoc)
	 * @see janken.InterfaceTactics#readTactics()
	 * 戦略を読みジャンケンの手を得る
	 */
	public String readTactics(){
		double hand = Math.random() * 3;
		String janHand = null;

		if(hand  < 1){
			janHand = JankenHandType.GU.get();
		}else if(hand  < 2){
			janHand = JankenHandType.CHOKI.get();
		}else if(hand  < 3){
			janHand = JankenHandType.PA.get();
		}

		return janHand;
	}
}
