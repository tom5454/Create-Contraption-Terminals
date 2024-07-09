package com.tom.createterminal.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

import com.tom.storagemod.tile.StorageTerminalBlockEntity;
import com.tom.storagemod.util.StoredItemStack;

@Mixin(value = StorageTerminalBlockEntity.class, remap = false)
public interface StorageTerminalBlockEntityAccessor {
	@Accessor
	Map<StoredItemStack, Long> getItems();

	@Accessor
	void setItemHandler(Storage<ItemVariant> ih);

	@Accessor
	boolean isUpdateItems();

	@Accessor
	void setUpdateItems(boolean v);
}
