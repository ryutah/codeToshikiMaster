package sample.tactics;

import sample.players.JankenHandType;

import java.util.Random;

/**
 * この列挙型では、{@link InterfaceTactics}の基本的な実装を定義する。
 * Created by ryuta on 16/10/02.
 */
public enum BasicTacticses {
    /**
     * グーのみ出す戦術を定義
     */
    ONLY_GU(() -> JankenHandType.GU),

    /**
     * チョキのみ出す戦術を定義
     */
    ONLY_CHOKI(() -> JankenHandType.CHOKI),

    /**
     * パーのみ出す戦術を定義
     */
    ONLY_PA(() -> JankenHandType.PA),

    /**
     * ランダムに手を出す戦術を定義
     */
    RANDOM(() -> {
        int handType = new Random().nextInt(3);
        switch (handType) {
        case 0:
            return JankenHandType.GU;
        case 1:
            return JankenHandType.CHOKI;
        case 2:
            return JankenHandType.PA;
        default:
            // never enter this block
            throw new RuntimeException("Can't handle value : " + handType);
        }
    });

    /**
     * 戦術<br>
     * 列挙型のメンバは基本的に変更されることはないため、ゲッター作るよりfinalつけて
     * 可視性publicにしたほうがいろいろすっきりすると思ってる。
     */
    public final InterfaceTactics tactics;

    BasicTacticses(InterfaceTactics tactics) {
        this.tactics = tactics;
    }
}
