package com.example.cachecache;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import java.util.List;

public class CacheCacheMod implements ModInitializer {
    private HideAndSeekGame hideAndSeekGame;
    private boolean isGameRunning = false;

    @Override
    public void onInitialize() {
        this.hideAndSeekGame = new HideAndSeekGame();
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (isGameRunning) {
                hideAndSeekGame.update();
            }
        });
    }

    public void startGame(List<ServerPlayerEntity> players) {
        isGameRunning = true;
        hideAndSeekGame.startGame(players);
    }

    public void endGame() {
        isGameRunning = false;
        hideAndSeekGame.endGame();
    }
}