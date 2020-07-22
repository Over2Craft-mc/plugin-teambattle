package com.shyndard.over2craft.teambattle;

import com.shyndard.over2craft.teambattle.service.PlayerService;

public class AutoExecute implements Runnable {

	@Override
	public void run() {
		long currentTime = System.currentTimeMillis()/1000;
		PlayerService.getInstance().getPlayerProfiles().forEach((player, profile) -> {
			if(profile.isDead()) {
				if(profile.getLastDeadTime() + 5 < currentTime) {
					PlayerService.getInstance().respawn(player);
				}
			}
		});
	}

}