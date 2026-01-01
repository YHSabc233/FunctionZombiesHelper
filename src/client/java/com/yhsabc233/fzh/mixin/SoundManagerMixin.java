package com.yhsabc233.fzh.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public class SoundManagerMixin{
	
	@Inject(at = @At("HEAD"), method = "play(Lnet/minecraft/client/sound/SoundInstance;)V")
	private void onSoundPlayed(SoundInstance si, CallbackInfo ci) {
		Identifier id = si.getId();
		String idString = id.toString();
		/*
		int round = 0;
		
		if (id == SoundEvents.ENTITY_WITHER_SPAWN.id()) {
			round++;
			System.out.println("[FZH] round start sound with " + round);
		} else if (id == SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE.id()) {
			System.out.println("[FZH] giant warning sound");
		} else if (id == SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE.id()) {
			System.out.println("[FZH] giant spawned sound");
		} else {
			System.out.println("[FZH] UNKOWN SOUND ID: " + idString);
		}*/
	}
}
