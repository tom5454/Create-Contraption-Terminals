package com.tom.createterminal.client;

import net.createmod.ponder.foundation.PonderIndex;

public class ClientRegistration {

	public static void register() {
		PonderIndex.addPlugin(new CCTPonderPlugin());
	}
}
