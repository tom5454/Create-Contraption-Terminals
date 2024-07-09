package com.tom.createterminal.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.tterrag.registrate.fabric.RegistryObject;
import com.tterrag.registrate.util.entry.ItemProviderEntry;

import com.tom.createterminal.CreateTerminals;
import com.tom.storagemod.util.GameObject;

public class GameObjectProvider extends ItemProviderEntry<Item> {

	public GameObjectProvider(GameObject<? extends Block> obj) {
		super(CreateTerminals.registrate(), RegistryObject.of(BuiltInRegistries.BLOCK.getKey(obj.get()), BuiltInRegistries.ITEM));
	}

}
