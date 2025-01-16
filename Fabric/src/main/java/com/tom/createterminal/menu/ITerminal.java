package com.tom.createterminal.menu;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.tom.createterminal.behaviour.ContraptionWorld;
import com.tom.createterminal.mixin.CraftingTerminalBlockEntityAccessor;
import com.tom.createterminal.mixin.StorageTerminalBlockEntityAccessor;
import com.tom.storagemod.util.StoredItemStack;
import com.tom.storagemod.util.Util;

public interface ITerminal extends MenuProvider {
	StoredItemStack pushStack(StoredItemStack stack);
	void dropItem(ItemStack st);
	void updateServer();
	boolean canInteractWith(Player player);

	ContraptionWorld getContraption();

	public static interface ITerminalImpl extends ITerminal {

		@Override
		public default void dropItem(ItemStack st) {
			getContraption().popItem(st);
		}

		@Override
		public default void updateServer() {
			StorageTerminalBlockEntityAccessor ac = (StorageTerminalBlockEntityAccessor) this;
			if(ac.isUpdateItems()) {
				var items = ac.getItems();
				items.clear();
				Storage<ItemVariant> itemHandler = getContraption().getContraption().getSharedInventory();
				ac.setItemHandler(itemHandler);
				if(itemHandler != null) {
					Util.stream(itemHandler.iterator()).
					filter(s -> !s.isResourceBlank()).distinct().map(s -> new StoredItemStack(s.getResource().toStack(), s.getAmount())).
					forEach(s -> items.merge(s, s.getQuantity(), (a, b) -> a + b));
				}
				ac.setUpdateItems(false);
			}
		}

		@Override
		default boolean canInteractWith(Player player) {
			return getContraption().isValid() && player.distanceToSqr(getContraption().getActualPosition()) < 64;
		}
	}

	public static interface ICraftingTerminalImpl extends ITerminalImpl {

		@Override
		default void updateServer() {
			ITerminalImpl.super.updateServer();
			((CraftingTerminalBlockEntityAccessor) this).setCraftingCooldown(0);
		}
	}
}
