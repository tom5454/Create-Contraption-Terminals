package com.tom.createterminal.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import com.tom.createterminal.behaviour.ContraptionWorld;
import com.tom.createterminal.mixin.CraftingTerminalBlockEntityAccessor;
import com.tom.createterminal.mixin.StorageTerminalBlockEntityAccessor;
import com.tom.storagemod.Config;
import com.tom.storagemod.inventory.IInventoryAccess.IInventoryChangeTracker;
import com.tom.storagemod.inventory.StoredItemStack;
import com.tom.storagemod.util.Util;
import com.tom.storagemod.util.WorldStates;

public interface ITerminal extends MenuProvider {
	StoredItemStack pushStack(StoredItemStack stack);
	StoredItemStack pullStack(StoredItemStack stack, long max);
	void dropItem(ItemStack st);
	void updateServer();
	boolean canInteractWith(Player player, boolean menuCheck);
	Level getLevel0();
	int getChangeCount();

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
				IItemHandler itemHandler = getContraption().getContraption().getStorage().getAllItems();
				IInventoryChangeTracker tr = WorldStates.getTracker(itemHandler);
				long ct = tr.getChangeTracker(getLevel0());
				if (ac.getChangeTracker() != ct) {
					ac.setChangeTracker(ct);
					if (Config.get().runMultithreaded) {
						ac.setItems(tr.streamWrappedStacks(true).collect(Collectors.groupingByConcurrent(Function.identity(), Util.reducingWithCopy(null, StoredItemStack::merge, StoredItemStack::new))));
					} else {
						Map<StoredItemStack, StoredItemStack> items = new HashMap<>();
						tr.streamWrappedStacks(false).forEach(s -> items.merge(s, s, StoredItemStack::merge));
						items.replaceAll((k, v) -> new StoredItemStack(v));
						ac.setItems(items);
					}
					ac.setChangeCount(getChangeCount() + 1);
				}
				ac.setUpdateItems(false);
			}
		}

		@Override
		default boolean canInteractWith(Player player, boolean menuCheck) {
			return getContraption().isValid() && player.distanceToSqr(getContraption().getActualPosition()) < 64;
		}

		@Override
		default StoredItemStack pullStack(StoredItemStack stack, long max) {
			IItemHandler itemHandler = getContraption().getContraption().getStorage().getAllItems();
			if(stack != null && itemHandler != null && max > 0) {
				ItemStack st = stack.getStack();
				StoredItemStack ret = null;
				for (int i = itemHandler.getSlots() - 1; i >= 0; i--) {
					ItemStack s = itemHandler.getStackInSlot(i);
					if(ItemStack.isSameItemSameComponents(s, st)) {
						ItemStack pulled = itemHandler.extractItem(i, (int) max, false);
						if(!pulled.isEmpty()) {
							if(ret == null)ret = new StoredItemStack(pulled);
							else ret.grow(pulled.getCount());
							max -= pulled.getCount();
							if(max < 1)break;
						}
					}
				}
				return ret;
			}
			return null;
		}

		@Override
		default StoredItemStack pushStack(StoredItemStack stack) {
			IItemHandler itemHandler = getContraption().getContraption().getStorage().getAllItems();
			if(stack != null && itemHandler != null) {
				ItemStack is = ItemHandlerHelper.insertItemStacked(itemHandler, stack.getActualStack(), false);
				if(is.isEmpty())return null;
				else {
					return new StoredItemStack(is);
				}
			}
			return stack;
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
