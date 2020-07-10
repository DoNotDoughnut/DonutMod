package net.rhysholloway.donutmod.util;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Excluded;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import net.rhysholloway.donutmod.DonutMod;

@Config(name = DonutMod.modId)
public class ModConfig implements ConfigData {

	@CollapsibleObject
	public Bloodmoon bloodmoon = new Bloodmoon();

	public static class Bloodmoon {

		@CollapsibleObject
		public General general = new General();

		public static class General {

			@Comment("Whether players are not able to sleep on a bloodmoon")
			public boolean no_sleep = true;

			@Comment("Whether monsters spawned by a bloodmoon should die at dawn")
			public boolean vanish = false;

			@Comment("Whether bloodmoons should respect the doMobSpawning gamerule")
			public boolean respect_gamerule = true;

			@Comment("Whether all players in the overworld should receive a message when the bloodmoon starts")
			public boolean send_message = true;

		}

		@CollapsibleObject
		public Appearance appearance = new Appearance();

		public static class Appearance {

			public boolean red_moon = true;
			public boolean red_sky = true;
			public boolean red_light = true;
			public boolean black_fog = true;

		}

		@CollapsibleObject
		public Schedule schedule = new Schedule();

		public static class Schedule {

			@Comment("The chance of a bloodmoon occuring at the beginning of a night (0=Never;1=Every night;0.05=5% of all nights)")
			public double chance = 0.05;

			@Comment("Whether there should be a bloodmoon whenever there is a full moon")
			public boolean full_moon = false;

			@Comment("Every nth night there will be a bloodmoon (0 disables this, 1 would be every night, 2 every second night)")
			public int nth_night = 0;

		}

		@CollapsibleObject
		public Spawning spawning = new Spawning();

		public static class Spawning {

			@Comment("How much faster enemys spawn on a bloodmoon (0=Vanilla)")
			public int spawn_speed = 4;

			@Comment("With which number should the default entity limit be multiplicated on a blood moon")
			public int spawn_limit_multiplier = 4;

			@Comment("How close can enemys spawn next to the player on a bloodmoon in blocks? (Vanilla=24)")
			public int spawn_range = 2;

			@Comment("How close can enemys spawn next to the World Spawn (Vanilla=24)")
			public int spawn_distance = 24;

			@Comment("If this isn't empty only monsters which names are in this list will get spawned by the bloodmoon. (Example: \"Skeleton,Spider\")")
			public String[] spawn_whitelist = new String[0];

			@Comment("Monsters which names are on this list won't get spawned by the bloodmoon (Has no effect when a whitelist is active). (Example: \"Skeleton,Spider\")")
			public String[] spawn_blacklist = new String[0];

		}

	}

	@CollapsibleObject
	public UndergroundCabin underground_cabin = new UndergroundCabin();

	public static class UndergroundCabin {

		public boolean enabled = true;
		public int chance = 80;

	}

	@CollapsibleObject
	public CobaltOre cobalt_ore = new CobaltOre();

	public static class CobaltOre {

		public int veinSize = 8;
		public int veinsPerChunk = 40;// 4;
		public int bottomOffset = 0;
		public int minHeight = 0;
		public int maxHeight = 54;

	}

	@CollapsibleObject
	public MythrilOre mythril_ore = new MythrilOre();

	public static class MythrilOre {

		public int veinSize = 8;
		public int veinsPerChunk = 2;
		public int bottomOffset = 0;
		public int minHeight = 0;
		public int maxHeight = 36;

	}

	@CollapsibleObject
	public AdamantiteOre adamantite_ore = new AdamantiteOre();

	public static class AdamantiteOre {

		public int veinSize = 5;
		public int veinsPerChunk = 1;
		public int bottomOffset = 0;
		public int minHeight = 0;
		public int maxHeight = 18;

	}

	@Excluded
	public static ModConfig config;
	
	public static void register() {
		DonutMod.logger.info("Registering config...");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();		
	}

}
