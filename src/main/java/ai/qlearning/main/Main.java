package ai.qlearning.main;

import ai.qlearning.crud.EnvironmentManager;
import ai.qlearning.crud.model.Action;
import ai.qlearning.crud.model.Environment;
import ai.qlearning.crud.qlearning.QLearningAgent;

public class Main {

	public static void main(String[] args) {

		EnvironmentManager manager = new EnvironmentManager();

		// Create and setup environment
		manager.createEnvironment("GridWorld");
		manager.updateReward("GridWorld", 0, 0, Action.RIGHT, -0.1);
		manager.updateReward("GridWorld", 1, 1, Action.DOWN, 1.0); // positive goal
		manager.updateReward("GridWorld", 2, 2, Action.RIGHT, -1.0); // negative

		// Train agent
		Environment env = manager.readEnvironment("GridWorld");
		QLearningAgent agent = new QLearningAgent(env);
		agent.train(1000);

		// Show learned Q-values
		agent.printQTable();
	}
}
