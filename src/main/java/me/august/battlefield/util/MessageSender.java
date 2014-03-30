package me.august.battlefield.util;

import me.august.battlefield.player.BattlefieldPlayer;

/**
 * Created by August on 3/29/14.
 */
public class MessageSender implements Runnable {

	private BattlefieldPlayer player;
	private String message;

	public MessageSender(BattlefieldPlayer player, String message) {
		this.player = player;
		this.message = message;
	}

	@Override
	public void run() {
		player.sendMessage(message);
	}

}
