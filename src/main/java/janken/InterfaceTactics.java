package janken;

public interface InterfaceTactics {
	/**
	 * 戦略を読み、ジャンケンの手を得る。
	 * GU, CHOKI, PAのいずれかをPlayerクラスに定義されたStringで返す。
	 * @return ジャンケンの手
	 */
	public String readTactics();
}

