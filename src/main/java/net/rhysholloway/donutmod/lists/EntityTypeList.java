package net.rhysholloway.donutmod.lists;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.entity.mob.PossessedArmorEntity;
import net.rhysholloway.donutmod.entity.mob.wyvern.WyvernEntity;
import net.rhysholloway.donutmod.entity.mob.LivingRedstoneEntity;
import net.rhysholloway.donutmod.entity.projectile.LightDiscEntity;

public class EntityTypeList {
	
	public static EntityType<PossessedArmorEntity> POSSESSED_ARMOR = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PossessedArmorEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackable(8, 3).build();
	public static EntityType<WyvernEntity> WYVERN = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WyvernEntity::new).spawnableFarFromPlayer().specificSpawnBlocks(Blocks.AIR).fireImmune().trackable(64, 20).dimensions(EntityDimensions.fixed(1f, 1f)).build();
	public static EntityType<LivingRedstoneEntity> LIVING_REDSTONE = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LivingRedstoneEntity::new).fireImmune().dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackable(10, 3).specificSpawnBlocks(Blocks.STONE).build();
	
	public static EntityType<LightDiscEntity> LIGHT_DISC = new LightDiscEntity.Builder(SpawnGroup.MISC).dimensions(EntityDimensions.fixed(0.75F, 0.125F)).build();

	public static void register() {
		
		register(POSSESSED_ARMOR, "possessed_armor", PossessedArmorEntity.createHostileAttributes());
		register(LIVING_REDSTONE, "living_redstone", LivingRedstoneEntity.createMobAttributes());
		register(WYVERN, "wyvern", WyvernEntity.createMobAttributes());
		
		register(LIGHT_DISC, "light_disc");		
		
	}
	
	private static void register(EntityType<?> type, String id) {
		Registry.register(Registry.ENTITY_TYPE, new Identifier(DonutMod.modId, id), type);
	}

	private static void register(EntityType<? extends LivingEntity> type, String id, DefaultAttributeContainer.Builder attributes) {
		register(type, id);
		FabricDefaultAttributeRegistry.register(type, attributes);
	}
	
}
