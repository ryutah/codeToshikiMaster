package sample.players;

import sample.tactics.InterfaceTactics;

import java.util.Optional;

/**
 * インスタンス生成後に名前や戦術を変えられないように、それぞれのセッターを削除してる。
 * Created by ryuta on 16/10/02.
 *
 * @see janken.PlayerBase
 */
public class PlayerBase {
    private String           name;
    private InterfaceTactics tactics;
    private JankenHandType   nowHand;
    private int              winCount;


    /**
     * コンストラクタの可視性を同一パッケージのみに限定し、
     * ファクトリメソッド経由以外でインスタンス化されることを防ぐ
     *
     * @param name    プレイヤー名
     * @param tactics 戦術
     */
    PlayerBase(String name, InterfaceTactics tactics) {
        this.name = name;
        this.tactics = tactics;
    }

    // メソッドチェーンできるように、セッター系のメソッドはthisを返すようにしている
    // いわゆるビルダーパターン
    // こうすると、メンバが多い場合に↓のようにできたりして結構便利
    //  ex) someObj.setHoge(hoge).setFuga(fuga).setFoo(foo).setBar(bar)

    /**
     * じゃんけんの手ではなく、thisを返すように変更。<br>
     * この変更で、じゃんけん後に手を取得する場合は{@link PlayerBase#getNowHand()}を呼び出す必要あり。
     *
     * @return this
     * @see janken.PlayerBase#DoJanken()
     */
    public PlayerBase doJanken() {
        // 出した手をインスタンスが保持しないのはなんとなく使いづらかったためちょっと変更
        nowHand = tactics.readTactics();
        return this;
    }

    /**
     * @return this
     * @see janken.PlayerBase#plusWinCount()
     */
    public PlayerBase plusWinCount() {
        winCount++;
        return this;
    }

    /**
     * @return this
     * @see janken.PlayerBase#getWinCount()
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * @return this
     * @see PlayerBase#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * 追加ゲッター<br>
     * じゃんけんが開始されていない場合はランタイムエラーを発生。
     *
     * @return 出したじゃんけんの手
     */
    public JankenHandType getNowHand() {
        Optional<JankenHandType> hand = Optional.ofNullable(nowHand);
        if (hand.isPresent()) {
            return hand.get();
        }
        throw new RuntimeException("Has not started janken yet.");
    }

    @Override
    /**
     * 文字列変換時に、「{プレイヤー名}{勝数}」になるようにtoStringをオーバーライド
     */
    public String toString() {
        return getName() + getWinCount();
    }
}
