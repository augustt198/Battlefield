package me.august.battlefield.game.objective;

import me.august.battlefield.game.BattlefieldGamemode;

import java.util.List;

/**
 * Created by August on 3/23/14.
 */
public interface ObjectiveProvider {

	BattlefieldGamemode getGamemode();

	List<Objective> getObjectives();

	void update();

	void handle(ObjectiveEvent event);

}
