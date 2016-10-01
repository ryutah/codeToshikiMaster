package janken;

/**
 * ジャンケンプレイヤー
 *
 */
public class PlayerBase {

//----------属性----------
// プレイヤー名
private String name;
// プレイヤーが勝った回数
private int winCount;
// ジャンケンの手
private String hand;
// 戦略
private InterfaceTactics tactics;// newはメインクラスで行い、setTacticsでインスタンスを受け取る

//----------コンストラクタ----------
private PlayerBase(String name){
	this.setName(name);
}

//----------メソッド----------
/**
 * プレイヤーに戦略を渡す
 * @param tactics
 */
public void setTactics(InterfaceTactics tactics){
	// インスタンスを受け取る
	this.tactics = tactics;
}

/**
 * ジャンケンをする
 * @return ジャンケンの手
 */
public String DoJanken(){
	// 戦略を読んで手を決める
	hand = tactics.readTactics();// MainJankenでnewしたインスタンスを貰うか、このクラスでnewするかの違い
	return hand;
}

/**
 * 勝ち数を追加
 * @param winCount
 */
public void plusWinCount(){
	winCount++;
}

/**
 * 勝ち数をお知らせ
 * @return
 */
public int getWinCount(){
	return winCount;
}

// getter
public String getName() {
	return name;
}

// setter
public void setName(String name) {
	this.name = name;
}

// getter
//public String getJanHand() {
//	return janHand;
//}

}

