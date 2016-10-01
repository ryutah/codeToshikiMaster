package janken;

public class JankenFactory {

	private InterfaceTactics tactics;

	public final PlayerBase create(String name){
		// プレイヤーオブジェクト生成
		PlayerBase pb = new PlayerBase(name);

		// 戦略生成
		double tacticsNum = Math.random() * 2; // とりあえずランダムに決定
		if(tacticsNum < 1){
			tactics = new RandomHandTacticsImpl();
		}else if(tacticsNum < 2){
			tactics = new SameHandTacticsImpl(); // グー
		}

		// 戦略設定
		pb.setTactics(tactics);

		return pb;
	}

}