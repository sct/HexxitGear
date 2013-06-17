package sct.hexxitgear;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import sct.hexxitgear.core.HGPlayerTracker;
import sct.hexxitgear.core.HGTickHandler;
import sct.hexxitgear.item.*;
import sct.hexxitgear.net.ClientPacketHandler;
import sct.hexxitgear.setup.HexxitGearConfig;

import java.util.logging.Logger;

@Mod(modid = HexxitGear.modId, name = "Hexxit Gear", useMetadata = true, version = HexxitGear.version)
@NetworkMod(serverSideRequired = false, clientSideRequired = true,
        clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { HexxitGear.modNetworkChannel }, packetHandler = ClientPacketHandler.class))
public class HexxitGear {

    public static final String modId = "hexxitgear";
    public static final String modNetworkChannel = "HexxitGear";
    public static final String version = "1.5.2R1.0";

    @Mod.Instance(modId)
    public static HexxitGear instance;

    @SidedProxy(clientSide="sct.hexxitgear.ClientProxy", serverSide="sct.hexxitgear.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    public static Item skullHelmet;
    public static Item tribalChest;
    public static Item tribalLeggings;
    public static Item tribalShoes;

    public static Item hoodHelmet;
    public static Item thiefChest;
    public static Item thiefLeggings;
    public static Item thiefBoots;

    public static Item scaleHelmet;
    public static Item scaleChest;
    public static Item scaleLeggings;
    public static Item scaleBoots;

    @PreInit
    public void preInit(FMLPreInitializationEvent evt) {
        HexxitGearConfig.setConfigFolderBase(evt.getModConfigurationDirectory());

        HexxitGearConfig.loadCommonConfig(evt);

        HexxitGearConfig.extractLang(new String[]{"en_US"});
        HexxitGearConfig.loadLang();

        logger = evt.getModLog();
    }

    @Init
    public void init(FMLInitializationEvent evt) {
        skullHelmet = new ItemSkullHelmet(HexxitGearConfig.tribalHelmetId.getInt());
        tribalChest = new ItemTribalArmor(HexxitGearConfig.tribalChestId.getInt(), proxy.addArmor("tribal"), 1).setUnlocalizedName("hexxitgear.tribal.chest");
        tribalLeggings = new ItemTribalArmor(HexxitGearConfig.tribalLeggingsId.getInt(), proxy.addArmor("tribal"), 2).setUnlocalizedName("hexxitgear.tribal.leggings");
        tribalShoes = new ItemTribalArmor(HexxitGearConfig.tribalShoesId.getInt(), proxy.addArmor("tribal"), 3).setUnlocalizedName("hexxitgear.tribal.boots");
        scaleHelmet = new ItemScaleHelmet(HexxitGearConfig.scaleHelmetId.getInt());
        scaleChest = new ItemScaleArmor(HexxitGearConfig.scaleChestId.getInt(), proxy.addArmor("scale"), 1).setUnlocalizedName("hexxitgear.scale.chest");
        scaleLeggings = new ItemScaleArmor(HexxitGearConfig.scaleLeggingsId.getInt(), proxy.addArmor("scale"), 2).setUnlocalizedName("hexxitgear.scale.leggings");
        scaleBoots = new ItemScaleArmor(HexxitGearConfig.scaleBootsId.getInt(), proxy.addArmor("scale"), 3).setUnlocalizedName("hexxitgear.scale.boots");
        hoodHelmet = new ItemHoodHelmet(HexxitGearConfig.thiefHelmetId.getInt());
        thiefChest = new ItemThiefArmor(HexxitGearConfig.thiefChestId.getInt(), proxy.addArmor("thief"), 1).setUnlocalizedName("hexxitgear.thief.chest");
        thiefLeggings = new ItemThiefArmor(HexxitGearConfig.thiefLeggingsId.getInt(), proxy.addArmor("thief"), 2).setUnlocalizedName("hexxitgear.thief.leggings");
        thiefBoots = new ItemThiefArmor(HexxitGearConfig.thiefBootsId.getInt(), proxy.addArmor("thief"), 3).setUnlocalizedName("hexxitgear.thief.boots");

        proxy.init();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent evt) {
        TickRegistry.registerTickHandler(new HGTickHandler(), Side.SERVER);
        GameRegistry.registerPlayerTracker(HGPlayerTracker.instance);
    }
}
