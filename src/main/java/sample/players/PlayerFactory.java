package sample.players;

import sample.tactics.BasicTacticses;
import sample.tactics.InterfaceTactics;

/**
 * 名前が違うのは個人的な趣味
 * Created by ryuta on 16/10/02.
 *
 * @see janken.JankenFactory
 */
public class PlayerFactory {
    /**
     * @param name プレイヤー名
     * @return ランダムな手を出すプレイヤー
     */
    public static PlayerBase createRandomTacticsPlayer(String name) {
        return create(name, BasicTacticses.RANDOM.tactics);
    }

    /**
     * @param name プレイヤー名
     * @return グーしか出さないプレイヤー
     */
    public static PlayerBase createOnlyGuTacticsPlayer(String name) {
        return create(name, BasicTacticses.ONLY_GU.tactics);
    }

    /**
     * @param name    プレイヤー名
     * @param tactics 戦術
     * @return 指定された戦術を利用して手を出すプレイヤー
     */
    public static PlayerBase create(String name, InterfaceTactics tactics) {
        return new PlayerBase(name, tactics);
    }

    // このクラスはインスタンス化されて使用することはないため、コンストラクタをプライベート化
    private PlayerFactory() {
    }
}
