package net.minecraft.src;

import java.io.File;
import com.kaijin.ChargingBench.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.*;
import net.minecraft.src.ic2.api.*;

public class mod_IC2_ChargingBench extends NetworkMod
{
    static Configuration configuration = CommonProxy.getConfiguration();
    static int ChargingBenchBlockID;
    public static mod_IC2_ChargingBench instance;
    
    public static final Block ChargingBench = new BlockChargingBench(ChargingBenchBlockID, 0).setHardness(1.0F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("inventoryStocker");
    public static int guiIdChargingBench;

    public mod_IC2_ChargingBench()
    {
    	instance = this;
    }
    
    @Override
    public boolean clientSideRequired()
    {
            return true;
    }

    @Override
    public boolean serverSideRequired()
    {
            return false;
    }
   
    @Override
    public String getPriorities()
    {
    	return "required-after:mod_IC2";
    }
    
    public void load()
    {
    	MinecraftForge.versionDetect("IC2_Charging Bench", 3, 3, 8);
        ModLoader.registerBlock(ChargingBench, ItemChargingBench.class);
    	MinecraftForge.setGuiHandler(this.instance, new GuiHandlerIC2ChargingBench());
        ModLoader.registerTileEntity(TileEntityChargingBench1.class, "Charging Bench Mk1");
        ModLoader.registerTileEntity(TileEntityChargingBench2.class, "Charging Bench Mk2");
        ModLoader.registerTileEntity(TileEntityChargingBench3.class, "Charging Bench Mk3");
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 0), new Object[] {"UUU", "W W", "WWW", 'U', Items.getItem("copperCableItem"), 'W', Block.planks});
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 1), new Object[] {"UUU", "WCW", "WWW", 'U', Items.getItem("goldCableItem"), 'W', Block.planks, 'C', Items.getItem("electronicCircuit")});
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 2), new Object[] {"UUU", "WCW", "WWW", 'U', Items.getItem("ironCableItem"), 'W', Block.planks, 'C', Items.getItem("advancedCircuit")});
        ModLoader.addLocalization("blockChargingBench1.name", "Charging Bench Mk1");
        ModLoader.addLocalization("blockChargingBench2.name", "Charging Bench Mk2");
        ModLoader.addLocalization("blockChargingBench3.name", "Charging Bench Mk3");
        MinecraftForgeClient.preloadTexture("/ic2/sprites/ChargingBench.png");
        ModLoaderMp.registerGUI(this, mod_IC2_ChargingBench.guiIdChargingBench);

    }

    public String getVersion()
    {
        return "MC 1.2.5 - IC 1.102 - alpha 1";
    }
    
    static
    {
        try
        {
            config = new Configuration(new File(Platform.getMinecraftDir() + "/config/IC2ChargingBench.cfg"));
            config.load();
            idBlockChargingBench = Integer.valueOf(config.getOrCreateIntProperty("blockChargingBench", "block", 189).value).intValue();
            guiIdChargingBench = Integer.valueOf(config.getOrCreateIntProperty("guiIdChargingBench", "general", 110).value).intValue();
            config.save();
        }
        catch (Exception var1)
        {
            System.out.println("[ChargingBench] Error while trying to access configuration!");
            throw new RuntimeException(var1);
        }
    }
}
