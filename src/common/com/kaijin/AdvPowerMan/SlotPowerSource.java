/*******************************************************************************
 * Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 * Licensed as open source with restrictions. Please see attached LICENSE.txt.
 ******************************************************************************/
package com.kaijin.AdvPowerMan;

import ic2.api.IElectricItem;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotPowerSource extends Slot
{
	public int invIndex;

	public SlotPowerSource(IInventory inv, int index, int xpos, int ypos)
	{
		super(inv, index, xpos, ypos);
		invIndex = index;
	}

    /**
     * Check if the stack is a valid item for this slot.
     */
	@Override
    public boolean isItemValid(ItemStack stack)
    {
    	// Decide if the item is a valid IC2 power source
    	if (stack != null && stack.getItem() instanceof IElectricItem)
    	{
    		IElectricItem item = (IElectricItem)(stack.getItem());
    		if (item.canProvideEnergy() && item.getTier() <= ((TECommonBench)inventory).powerTier) return true;
    	}
        return false;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
	@Override
    public int getSlotStackLimit()
    {
        return 1;
    }

	@Override
    public void onSlotChanged()
    {
    	if (inventory instanceof TECommonBench)
    	{
            ((TECommonBench)inventory).onInventoryChanged(invIndex);
    	}
    	else
    	{
            inventory.onInventoryChanged();
    	}
    }
}