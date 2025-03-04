package com.tom.createterminal.client;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;

import com.tom.createterminal.CreateTerminals;
import com.tom.storagemod.Content;
import com.tom.storagemod.util.GameObject;

public class CCTPonderPlugin implements PonderPlugin {

	@Override
	public String getModId() {
		return CreateTerminals.MODID;
	}

	@Override
	public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
		PonderSceneRegistrationHelper<GameObject<?>> HELPER = helper.withKeyFunction(GameObject::getId);

		HELPER.forComponents(Content.terminal, Content.craftingTerminal)
		.addStoryBoard("terminal", PonderScenes::terminals, AllCreatePonderTags.LOGISTICS);
	}
}
