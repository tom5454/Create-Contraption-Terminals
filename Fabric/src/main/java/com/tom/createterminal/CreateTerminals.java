package com.tom.createterminal;

import org.slf4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tom.storagemod.util.GameObject;

public class CreateTerminals implements ModInitializer {
	public static final String MODID = "createcontraptionterminals";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static CreateRegistrate registrate;

	public CreateTerminals() {
		registrate = CreateRegistrate.create(MODID);

		Registration.register();
	}

	public static CreateRegistrate registrate() {
		return registrate;
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Create Contraption Terminals starting");
		registrate.register();
		Registration.postRegister();
	}

	public static ResourceLocation getGameObjectID(GameObject<?> object) {
		if (object.get() instanceof Block b)
			return BuiltInRegistries.BLOCK.getKey(b);
		if (object.get() instanceof Item b)
			return BuiltInRegistries.ITEM.getKey(b);
		throw new IllegalArgumentException("Unknown type: " + object.getClass());
	}
}
