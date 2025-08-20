package ai.qlearning.crud;

import java.util.HashMap;
import java.util.Map;

import ai.qlearning.crud.model.Action;
import ai.qlearning.crud.model.Environment;

public class EnvironmentManager {
	private Map<String, Environment> environments = new HashMap<>();

	public void createEnvironment(String name) {
		environments.put(name, new Environment());
	}

	public Environment readEnvironment(String name) {
		return environments.get(name);
	}

	public void updateReward(String name, int x, int y, Action action, double reward) {
		Environment env = environments.get(name);
		if (env != null) {
			env.setReward(x, y, action, reward);
		}
	}

	public void deleteEnvironment(String name) {
		environments.remove(name);
	}

	public Map<String, Environment> getAllEnvironments() {
		return environments;
	}
}
