package com.tom.createterminal.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.tom.storagemod.block.entity.StorageTerminalBlockEntity;
import com.tom.storagemod.inventory.StoredItemStack;

@Mixin(value = StorageTerminalBlockEntity.class, remap = false)
public interface StorageTerminalBlockEntityAccessor {
	@Accessor
	void setItems(Map<StoredItemStack, StoredItemStack> v);

	@Accessor
	boolean isUpdateItems();

	@Accessor
	void setUpdateItems(boolean v);

	@Accessor
	long getChangeTracker();

	@Accessor
	void setChangeTracker(long v);

	@Accessor
	void setChangeCount(int v);
}
