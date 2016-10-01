package janken;

/**
 * @author Toshi
 *
 */
public class MainJanken {
	public static void main(String[] args) {
		// ------インスタンス生成------
		JudgeBase judge = new JudgeBase();

		// ------Factory------
		JankenFactory jf = new JankenFactory();
		PlayerBase yamada = jf.create("山田");
		PlayerBase kume = jf.create("粂");

//		PlayerBase yamada = new PlayerBase("山田");// 山田さん生成
//		InterfaceTactics yamadaTactics = new TacticsImpl();// 戦略クラス生成
//		//SamaHandTacticsImpl yamadaTactics = new SamaHandTacticsImpl();
//		yamada.setTactics(yamadaTactics);// 山田さんに戦略を渡す
//
//		PlayerBase kume = new PlayerBase("粂");// 粂生成
//		InterfaceTactics kumeTactics = new TacticsImpl();// 戦略クラス生成
//		kume.setTactics(kumeTactics);// 粂に戦略を渡す
//		Connection conn = DriverManager.getConnection(url);
//		DriverManager dm = new DriverManager();

		// ------ジャンケン------
		judge.startJanken(yamada, kume);
	}
}