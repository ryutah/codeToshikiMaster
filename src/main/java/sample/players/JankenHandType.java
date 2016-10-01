package sample.players;

/**
 * Created by ryuta on 16/10/02.
 *
 * @see janken.JankenHandType
 */
public enum JankenHandType {
    GU("グー"),
    CHOKI("チョキ"),
    PA("パー");

    /**
     * じゃんけんの手の名前<br>
     * 列挙型のメンバは基本的に変更されることはないため、ゲッター作るよりfinalつけて
     * 可視性publicにしたほうがいろいろすっきりすると思ってる。
     */
    public final String identify;

    JankenHandType(String identify) {
        this.identify = identify;
    }

    /**
     * 引数のじゃんけんの手よりも自分のほうが強いかを判定する。<br>
     * 勝敗判定クラスが別に存在するため、ここに定義するのもちょっと微妙かもしれない
     * <pre>
     *    強い場合 : ture
     *    弱い場合 : false
     *    あいこの場合 : false
     * </pre>
     *
     * @param other 比較対象の手
     * @return 判定結果
     */
    public boolean strongerThan(JankenHandType other) {
        switch (this) {
        case GU:
            return other.equals(CHOKI);
        case CHOKI:
            return other.equals(PA);
        case PA:
            return other.equals(GU);
        default:
            // never enter this block
            throw new RuntimeException("Unknown type object");
        }
    }
}
