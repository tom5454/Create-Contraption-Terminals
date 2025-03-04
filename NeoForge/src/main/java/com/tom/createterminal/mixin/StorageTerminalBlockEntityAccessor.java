package com.tom.createterminal.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraftforge.items.IItemHandler;

import com.tom.storagemod.tile.StorageTerminalBlockEntity;
import com.tom.storagemod.util.StoredItemStack;

@Mixin(value = StorageTerminalBlockEntity.class, remap = false)
public interface StorageTerminalBlockEntityAccessor {
	@Accessor
	Map<StoredItemStack, Long> getItems();

	@Accessor
	void setItemHandler(IItemHandler ih);

	@Accessor
	boolean isUpdateItems();

	@Accessor
	void setUpdateItems(boolean v);
}
