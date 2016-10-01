package janken;


/**
 * @author Toshi
 * インターフェースの実装クラス
 */
public class SameHandTacticsImpl implements InterfaceTactics{

	public String readTactics(){
		return JankenHandType.GU.get();
	}

}
