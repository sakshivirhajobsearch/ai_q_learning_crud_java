package ai.qlearning.crud.model;

public class Environment {
	
	public static final int SIZE = 4;
	private double[][][] rewards = new double[SIZE][SIZE][4];

	public void setReward(int x, int y, Action action, double reward) {
		rewards[x][y][action.ordinal()] = reward;
	}

	public double getReward(int x, int y, Action action) {
		return rewards[x][y][action.ordinal()];
	}
}
