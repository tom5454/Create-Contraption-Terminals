package com.tom.createterminal;

import org.slf4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tom.createterminal.client.ClientRegistration;

@Mod(CreateTerminals.MODID)
public class CreateTerminals {
	public static final String MODID = "createcontraptionterminals";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static CreateRegistrate registrate;

	public CreateTerminals() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		registrate = CreateRegistrate.create(MODID).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());

		Registration.register();
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Create Contraption Terminals starting");
		Registration.postRegister();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(ClientRegistration::register);
	}

	public static CreateRegistrate registrate() {
		return registrate;
	}
}
