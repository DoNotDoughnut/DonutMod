package net.rhysholloway.donutmod2.config;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.google.gson.Gson;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.rhysholloway.donutmod2.DonutMod;

public class ConfigReloadListener implements SimpleSynchronousResourceReloadListener {

	private static Identifier id = new Identifier(DonutMod.modId, "reload_listener");
	
	private Gson gson = new Gson();
	
	@Override
	public Identifier getFabricId() {
		return id;
	}

	@Override
	public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void apply(ResourceManager manager) {
		
	}

}
