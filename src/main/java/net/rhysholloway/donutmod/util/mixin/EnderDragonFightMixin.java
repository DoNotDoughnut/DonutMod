package net.rhysholloway.donutmod.util.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.rhysholloway.donutmod.world.HardmodeState;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

	@Shadow
	private UUID dragonUuid;
	
	@Inject(method = "dragonKilled", at = @At("TAIL"))
	public void onDragonKilled(EnderDragonEntity dragon, CallbackInfo info) {
		if (dragon.getUuid().equals(this.dragonUuid)) {
			if(!dragon.world.isClient) {
				dragon.world.getServer().getOverworld().getPersistentStateManager().getOrCreate(HardmodeState::new, "hardmode").startHardmode(dragon.world.getServer());
			}
		}

	}

}
