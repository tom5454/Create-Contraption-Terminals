package com.tom.createterminal.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.tterrag.registrate.util.entry.ItemProviderEntry;

import com.tom.createterminal.CreateTerminals;
import com.tom.storagemod.util.GameObject;

public class GameObjectProvider extends ItemProviderEntry<Item> {

	public GameObjectProvider(GameObject<? extends Block> obj) {
		super(CreateTerminals.registrate(), RegistryObject.create(obj.getId(), ForgeRegistries.ITEMS));
	}

}
