package sample.judges;

import sample.players.PlayerBase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 各所で{@link Optional}を使うようになってる。<br>
 * Java8移行は、nullを扱うときはなるべく{@link Optional}を使うようにしたほうがいいと思う
 * Created by ryuta on 16/10/02.
 *
 * @see janken.JudgeBase
 */
public class JudgeBase {
    /**
     * なんとなく、じゃんけんの試行回数は引数で指定されるように変更<br>
     * あと、コードの見通しを良くするためにループ回す処理をここでやらせて、
     * ループ内の処理を{@link JudgeBase#startJanken(PlayerBase, PlayerBase)}に分けている。
     *
     * @param player1
     * @param player2
     * @param jankenCount じゃんけん試行回数
     * @see janken.JudgeBase#startJanken(janken.PlayerBase, janken.PlayerBase)
     */
    public void startJanken(PlayerBase player1, PlayerBase player2, int jankenCount) {
        System.out.println("【Start janken】");
        // 結構無理やりだと思ってるStreamAPIの使い方
        // 何でもStreamAPI使えばいいってわけじゃないのはわかってるど使ってしまう。
        IntStream.range(0, jankenCount).forEach(i -> startJanken(player1, player2));

        System.out.println(player1 + " : " + player2);

        System.out.println("Now Judging...");
        Optional<PlayerBase> winner = finalJudgeJanken(player1, player2);
        System.out.println(getWinnerMessage(winner));

        System.out.println("【End janken】");
    }

    /**
     * じゃんけんを一回実行する。<br>
     * {@link janken.JudgeBase#startJanken(janken.PlayerBase, janken.PlayerBase)}のメソッドを分けた感じ
     *
     * @param player1
     * @param player2
     * @see janken.JudgeBase#startJanken(janken.PlayerBase, janken.PlayerBase)
     */
    private void startJanken(PlayerBase player1, PlayerBase player2) {
        List<PlayerBase> handDesidePlayers = notifyStart(player1, player2);
        System.out.println(printFormatOf(handDesidePlayers));
        Optional<PlayerBase> winner = winnerOf(handDesidePlayers.get(0), handDesidePlayers.get(1));
        winner.ifPresent(PlayerBase::plusWinCount);
    }

    /**
     * {@link janken.JudgeBase#judgeJanken(janken.PlayerBase, janken.PlayerBase)}的なメソッド<br>
     * 一番違うのはじゃんけんの勝者を戻り値として返すようにしてることろ。<br>
     * メソッド名が違うのはただの趣味
     *
     * @param player1
     * @param player2
     * @return
     * @see janken.JudgeBase#judgeJanken(janken.PlayerBase, janken.PlayerBase)
     */
    private Optional<PlayerBase> winnerOf(PlayerBase player1, PlayerBase player2) {
        if (player1.getNowHand().equals(player2.getNowHand())) {
            return Optional.empty();
        }

        return player1.getNowHand().strongerThan(player2.getNowHand()) ?
               Optional.of(player1) :
               Optional.of(player2);
    }

    /**
     * 各プレイヤーに、じゃんけんの開始を通知する。<br>
     * 一応関数型を意識して、じゃんけん開始通知を受けたプレイヤーのリストを返すようにしてるが中途半端
     *
     * @param players
     * @return じゃんけん開始通知を受け取ったプレイヤーのリスト
     */
    private List<PlayerBase> notifyStart(PlayerBase... players) {
        return Arrays.stream(players)
                     .map(PlayerBase::doJanken)
                     .collect(Collectors.toList());
    }

    /**
     * 各プレイヤーのじゃんけんの手を表示する文字列を生成する。<br>
     * 「{プレイヤー名}{じゃんけんの手} vs {プレイヤー名}{じゃんけんの手}...」の形式
     *
     * @param players
     * @return じゃんけんの手表示文字列
     */
    private String printFormatOf(List<PlayerBase> players) {
        return players.stream()
                      .map(player -> player.getName() + player.getNowHand().identify)
                      .collect(Collectors.joining(" vs "));
    }

    /**
     * 基本的に元と一緒
     *
     * @param player1
     * @param player2
     * @return
     * @see janken.JudgeBase#finalJudgeJanken(janken.PlayerBase, janken.PlayerBase)
     */
    private Optional<PlayerBase> finalJudgeJanken(PlayerBase player1, PlayerBase player2) {
        if (player1.getWinCount() == player2.getWinCount()) {
            return Optional.empty();
        }

        return player1.getWinCount() > player2.getWinCount() ?
               Optional.of(player1) :
               Optional.of(player2);
    }

    /**
     * 最終結果のメッセージを生成
     * <pre>
     *     勝者がいる場合   :  最終勝者は{プレイヤー名}です。
     *     勝者がいない場合 :  あいこです
     * </pre>
     *
     * @param winner
     * @return 最終結果のメッセージ
     */
    private String getWinnerMessage(Optional<PlayerBase> winner) {
        String winnerMsg = "最終勝者は%sです";
        return winner.isPresent() ? String.format(winnerMsg, winner.get().getName()) : "あいこです";
    }

    // TODO: 複数人の場合の勝者取得をやってみるといいかも
    private List<PlayerBase> winnersOf(List<PlayerBase> players) {
        throw new UnsupportedOperationException("Not implements yet.");
    }

    // TODO: 複数人の場合の最終勝者取得処理もやってみるといいかも
    private List<PlayerBase> chanpionsOf(List<PlayerBase> players) {
        throw new UnsupportedOperationException("Not implements yet.");
    }
}
