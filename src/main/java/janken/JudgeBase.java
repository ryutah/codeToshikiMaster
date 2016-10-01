package janken;


/**
 * @author 「ジャンケン開始」のメッセージを受け取ったら、一連の勝負の流れを開始する
 *
 */
public class JudgeBase
{
	// 手
	private String p1Hand = null;
	private String p2Hand = null;

    /**
     * ジャンケンを3回勝負する
     */
    public void startJanken(PlayerBase p1, PlayerBase p2){

    	// 開始宣言
    	System.out.println("【Start janken】");

    	//3回勝負
    	int i = 0;
    	while(i < 3){

    		//それぞれ手を出す
    		p1Hand = p1.DoJanken();
    		p2Hand = p2.DoJanken();


            // 手を表示
            System.out.println(p1.getName() + p1Hand + " vs " + p2.getName() + p2Hand);

            // 勝敗判定
            judgeJanken(p1, p2);

            // 点数
            //System.out.println(p1.getName() + p1.getWinCount() + " ：" + p2.getName() + p2.getWinCount());

    		i++;
    	}

    	// 最終判定
    	System.out.println("Now Judging...");
    	System.out.println(p1.getName() + p1.getWinCount() + " ： " + p2.getName() + p2.getWinCount());
    	PlayerBase finalWinner = finalJudgeJanken(p1, p2);
    	if(finalWinner != null){
    		System.out.println("最終勝者は" + finalWinner.getName() + "です");
    	}else{
    		System.out.println("あいこです");
    	}

    	// 終了
    	System.out.println("【End janken】");

    }


    /**
     * ジャンケンの勝敗判定
     * @param
     * @return 勝者のインスタンス
     */
    public void judgeJanken(PlayerBase p1, PlayerBase p2){
    	PlayerBase winner = null;
    	JankenHandType janken = null;

    	if(p1Hand == janken.GU.get() && p2Hand == janken.CHOKI.get() ||
    	   p1Hand == janken.CHOKI.get() && p2Hand == janken.PA.get() ||
    	   p1Hand == janken.PA.get() && p2Hand == janken.GU.get()){
    		winner = p1;
    	}else if(p2Hand == janken.GU.get() && p1Hand == janken.CHOKI.get() ||
    			 p2Hand == janken.CHOKI.get() && p1Hand == janken.PA.get() ||
    			 p2Hand == janken.PA.get() && p1Hand == janken.GU.get()){
    		winner = p2;
    	}else{
    		// あいこ
    		winner = null;
    	}

    	//勝者の勝ち数を追加
    	if(winner != null){
    		notifyWinner(winner);
    	}
    }

    /**
     * 勝者の勝ち数を追加
     */
    public void notifyWinner(PlayerBase winner){
    	winner.plusWinCount();
    }


    /**
     * 最終判定
     * @param result01 プレイヤー01
     * @param result02 プレイヤー02
     * @return
     */
    public PlayerBase finalJudgeJanken(PlayerBase p1, PlayerBase p2){

    	int p1WinNum = p1.getWinCount();
    	int p2WinNum = p2.getWinCount();

    	if(p1WinNum > p2WinNum){
    		return p1;
    	}else if(p1WinNum < p2WinNum){
    		return p2;
    	}

    	// あいこはnull
    	return null;
    }

}
