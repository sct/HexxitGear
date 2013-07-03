/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import sct.hexxitgear.block.BlockHexbiscus;
import sct.hexxitgear.setup.HexxitGearRegistry;
import sct.hexxitgear.event.PlayerEventHandler;
import sct.hexxitgear.net.HGPacketHandler;
import sct.hexxitgear.tick.PlayerTracker;
import sct.hexxitgear.item.*;
import sct.hexxitgear.setup.HexxitGearConfig;
import sct.hexxitgear.world.HGWorldGen;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Mod(modid = HexxitGear.modId, name = "Hexxit Gear", useMetadata = true, version = HexxitGear.version)
@NetworkMod(serverSideRequired = false, clientSideRequired = true,
        clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { HexxitGear.modNetworkChannel }, packetHandler = HGPacketHandler.class),
        serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { HexxitGear.modNetworkChannel }, packetHandler = HGPacketHandler.class))
public class HexxitGear {

    public static final String modId = "hexxitgear";
    public static final String modNetworkChannel = "HexxitGear";
    public static final String version = "1.5.2R1.0";

    @Mod.Instance(modId)
    public static HexxitGear instance;

    @SidedProxy(clientSide="sct.hexxitgear.ClientProxy", serverSide="sct.hexxitgear.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;
    public static PlayerEventHandler playerEventHandler;

    public static Block hexbiscus;

    public static Item hexicalEssence;
    public static Item hexicalDiamond;

    public static Item tribalHelmet;
    public static Item tribalChest;
    public static Item tribalLeggings;
    public static Item tribalShoes;

    public static Item thiefHelmet;
    public static Item thiefChest;
    public static Item thiefLeggings;
    public static Item thiefBoots;

    public static Item scaleHelmet;
    public static Item scaleChest;
    public static Item scaleLeggings;
    public static Item scaleBoots;

    public static List<Integer> dimensionalBlacklist = new ArrayList<Integer>();

    @PreInit
    public void preInit(FMLPreInitializationEvent evt) {
        HexxitGearConfig.setConfigFolderBase(evt.getModConfigurationDirectory());

        HexxitGearConfig.loadCommonConfig(evt);

        HexxitGearConfig.extractLang(new String[]{"en_US"});
        HexxitGearConfig.loadLang();
        HexxitGearConfig.registerDimBlacklist();

        logger = evt.getModLog();
        playerEventHandler = new PlayerEventHandler();
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
    }

    @Init
    public void init(FMLInitializationEvent evt) {
        hexbiscus = new BlockHexbiscus(HexxitGearConfig.hexbiscus.getInt());

        tribalHelmet = new ItemTribalArmor(HexxitGearConfig.tribalHelmetId.getInt(), proxy.addArmor("tribal"), 0).setUnlocalizedName("hexxitgear.tribal.helmet");
        tribalChest = new ItemTribalArmor(HexxitGearConfig.tribalChestId.getInt(), proxy.addArmor("tribal"), 1).setUnlocalizedName("hexxitgear.tribal.chest");
        tribalLeggings = new ItemTribalArmor(HexxitGearConfig.tribalLeggingsId.getInt(), proxy.addArmor("tribal"), 2).setUnlocalizedName("hexxitgear.tribal.leggings");
        tribalShoes = new ItemTribalArmor(HexxitGearConfig.tribalShoesId.getInt(), proxy.addArmor("tribal"), 3).setUnlocalizedName("hexxitgear.tribal.boots");
        scaleHelmet = new ItemScaleArmor(HexxitGearConfig.scaleHelmetId.getInt(), proxy.addArmor("scale"), 0).setUnlocalizedName("hexxitgear.scale.helmet");
        scaleChest = new ItemScaleArmor(HexxitGearConfig.scaleChestId.getInt(), proxy.addArmor("scale"), 1).setUnlocalizedName("hexxitgear.scale.chest");
        scaleLeggings = new ItemScaleArmor(HexxitGearConfig.scaleLeggingsId.getInt(), proxy.addArmor("scale"), 2).setUnlocalizedName("hexxitgear.scale.leggings");
        scaleBoots = new ItemScaleArmor(HexxitGearConfig.scaleBootsId.getInt(), proxy.addArmor("scale"), 3).setUnlocalizedName("hexxitgear.scale.boots");
        thiefHelmet = new ItemThiefArmor(HexxitGearConfig.thiefHelmetId.getInt(), proxy.addArmor("thief"), 0).setUnlocalizedName("hexxitgear.thief.helmet");
        thiefChest = new ItemThiefArmor(HexxitGearConfig.thiefChestId.getInt(), proxy.addArmor("thief"), 1).setUnlocalizedName("hexxitgear.thief.chest");
        thiefLeggings = new ItemThiefArmor(HexxitGearConfig.thiefLeggingsId.getInt(), proxy.addArmor("thief"), 2).setUnlocalizedName("hexxitgear.thief.leggings");
        thiefBoots = new ItemThiefArmor(HexxitGearConfig.thiefBootsId.getInt(), proxy.addArmor("thief"), 3).setUnlocalizedName("hexxitgear.thief.boots");

        hexicalEssence = new ItemHexicalEssence(HexxitGearConfig.hexicalEssence.getInt());
        hexicalDiamond = new ItemHexicalDiamond(HexxitGearConfig.hexicalDiamond.getInt());

        GameRegistry.registerBlock(hexbiscus, hexbiscus.getUnlocalizedName());

        GameRegistry.registerWorldGenerator(new HGWorldGen());

        proxy.init();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent evt) {
        GameRegistry.registerPlayerTracker(PlayerTracker.instance);
        HexxitGearRegistry.init();
    }

    public static void addToDimBlacklist(int dimID) {
        if (!dimensionalBlacklist.contains(dimID))
            dimensionalBlacklist.add(dimID);
    }

    public static List<Integer> getDimBlacklist() {
        return dimensionalBlacklist;
    }
}
