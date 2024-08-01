package com.example.cachecache;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HideAndSeekGame {
    private ServerPlayerEntity seeker;
    private List<ServerPlayerEntity> hiders = new ArrayList<>();
    private int timer;

    public HideAndSeekGame() {
        this.timer = 300; // 5 minutes
    }

    public void startGame(List<ServerPlayerEntity> players) {
        Random random = new Random();
        this.seeker = players.get(random.nextInt(players.size()));
        for (ServerPlayerEntity player : players) {
            if (!player.equals(seeker)) {
                hiders.add(player);
            }
        }
        ServerTickEvents.END_SERVER_TICK.register(server -> this.update());
    }

    public void update() {
        if (timer > 0) {
            timer--;
        } else {
            endGame();
        }
    }

    public void endGame() {
        ServerTickEvents.END_SERVER_TICK.unregister(server -> this.update());
    }
}