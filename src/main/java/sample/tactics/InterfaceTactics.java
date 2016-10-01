package sample.tactics;

import sample.players.JankenHandType;

/**
 * Created by ryuta on 16/10/02.
 *
 * @see janken.InterfaceTactics
 */
public interface InterfaceTactics {

    /**
     * 文字列を返していたのを、列挙型を返すように変更
     *
     * @return じゃんけんの手
     * @see janken.InterfaceTactics#readTactics()
     */
    JankenHandType readTactics();
}
