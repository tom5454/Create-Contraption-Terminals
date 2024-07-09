package com.tom.createterminal.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.tom.storagemod.tile.CraftingTerminalBlockEntity;

@Mixin(value = CraftingTerminalBlockEntity.class, remap = false)
public interface CraftingTerminalBlockEntityAccessor {
	@Accessor
	void setCraftingCooldown(int v);
}
