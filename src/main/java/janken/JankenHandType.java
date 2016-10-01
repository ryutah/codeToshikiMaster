package janken;

public enum JankenHandType {

	//ジャンケンの手を表す定数
//		public static final String GU = "グー";
//		public static final String CHOKI = "チョキ";
//		public static final String PA = "パー";
		GU("グー"),
		CHOKI("チョキ"),
		PA("パー");

	    // 変数
	    private String hand;

	    // コンストラクタ
	    private JankenHandType(String hand) {
	        this.hand = hand;
	    }

	    // 取得メソッド
	    public String get() {
	        return this.hand;
	    }
}
