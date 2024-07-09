package com.tom.createterminal;

import org.slf4j.Logger;

import net.fabricmc.api.ModInitializer;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

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
}
