package com.tom.createterminal.client;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;

import com.tom.createterminal.CreateTerminals;
import com.tom.createterminal.util.GameObjectProvider;
import com.tom.storagemod.Content;

public class ClientRegistration {
	static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreateTerminals.MODID);

	public static void register() {
		HELPER.forComponents(new GameObjectProvider(Content.terminal), new GameObjectProvider(Content.craftingTerminal))
		.addStoryBoard("terminal", PonderScenes::terminals, AllPonderTags.LOGISTICS);
	}
}
