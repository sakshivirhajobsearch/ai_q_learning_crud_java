package ai.qlearning.crud.qlearning;

import java.util.Random;

import ai.qlearning.crud.model.Action;
import ai.qlearning.crud.model.Environment;

public class QLearningAgent {
	
	private double[][][] qTable;
	private Environment env;
	private double alpha = 0.1;
	private double gamma = 0.9;
	private double epsilon = 0.2;
	private Random rand = new Random();

	public QLearningAgent(Environment env) {
		this.env = env;
		int size = Environment.SIZE;
		qTable = new double[size][size][Action.values().length];
	}

	public void train(int episodes) {
		for (int ep = 0; ep < episodes; ep++) {
			int x = rand.nextInt(Environment.SIZE);
			int y = rand.nextInt(Environment.SIZE);

			for (int step = 0; step < 50; step++) {
				Action action = chooseAction(x, y);
				int newX = x, newY = y;

				switch (action) {
				case UP:
					newY = Math.max(0, y - 1);
					break;
				case DOWN:
					newY = Math.min(Environment.SIZE - 1, y + 1);
					break;
				case LEFT:
					newX = Math.max(0, x - 1);
					break;
				case RIGHT:
					newX = Math.min(Environment.SIZE - 1, x + 1);
					break;
				}

				double reward = env.getReward(x, y, action);
				double maxQ = maxQ(newX, newY);
				qTable[x][y][action.ordinal()] += alpha * (reward + gamma * maxQ - qTable[x][y][action.ordinal()]);

				x = newX;
				y = newY;
			}
		}
	}

	private Action chooseAction(int x, int y) {
		if (rand.nextDouble() < epsilon) {
			return Action.values()[rand.nextInt(Action.values().length)];
		} else {
			double[] qValues = qTable[x][y];
			int bestIndex = 0;
			for (int i = 1; i < qValues.length; i++) {
				if (qValues[i] > qValues[bestIndex]) {
					bestIndex = i;
				}
			}
			return Action.values()[bestIndex];
		}
	}

	private double maxQ(int x, int y) {
		double max = qTable[x][y][0];
		for (int i = 1; i < qTable[x][y].length; i++) {
			if (qTable[x][y][i] > max)
				max = qTable[x][y][i];
		}
		return max;
	}

	public void printQTable() {
		for (int x = 0; x < Environment.SIZE; x++) {
			for (int y = 0; y < Environment.SIZE; y++) {
				System.out.print("Q[" + x + "," + y + "]: ");
				for (Action a : Action.values()) {
					System.out.printf("%s=%.2f ", a, qTable[x][y][a.ordinal()]);
				}
				System.out.println();
			}
		}
	}
}
