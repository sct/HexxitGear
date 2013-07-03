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

package sct.hexxitgear.setup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import sct.hexxitgear.HexxitGear;

public class HexxitGearRegistry {

    public static void init() {
        registerRecipes();
    }

    public static void registerRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.hexicalDiamond), new Object[]
                {
                        " I ",
                        "IDI",
                        " I ",
                        'I', HexxitGear.hexicalEssence,
                        'D', Item.diamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.tribalHelmet), new Object[]
                {
                        "BBB",
                        "BHB",
                        "   ",
                        'B', Item.bone,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.tribalChest), new Object[]
                {
                        "I I",
                        "LHL",
                        "ILI",
                        'I', "ingotIron",
                        'L', Item.leather,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.tribalLeggings), new Object[]
                {
                        "LLL",
                        "IHI",
                        "L L",
                        'I', "ingotIron",
                        'L', Item.leather,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.tribalShoes), new Object[]
                {
                        "   ",
                        "SHS",
                        "L L",
                        'S', Item.silk,
                        'L', Item.leather,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.thiefHelmet), new Object[]
                {
                        "RRR",
                        "RHR",
                        "   ",
                        'R', new ItemStack(Block.cloth, 1, 14),
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.thiefChest), new Object[]
                {
                        "R R",
                        "LHL",
                        "LLL",
                        'R', new ItemStack(Block.cloth, 1, 14),
                        'L', Item.leather,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.thiefLeggings), new Object[]
                {
                        "LSL",
                        "LHL",
                        "L L",
                        'L', Item.leather,
                        'S', Item.silk,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.thiefBoots), new Object[]
                {
                        "   ",
                        "LHL",
                        "B B",
                        'L', Item.leather,
                        'H', HexxitGear.hexicalDiamond,
                        'B', new ItemStack(Block.cloth, 1, 7)
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.scaleHelmet), new Object[]
                {
                        "GOG",
                        "OHO",
                        "   ",
                        'G', "ingotGold",
                        'O', Block.obsidian,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.scaleChest), new Object[]
                {
                        "G G",
                        "OHO",
                        "GOG",
                        'G', "ingotGold",
                        'O', Block.obsidian,
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.scaleLeggings), new Object[]
                {
                        "OOO",
                        "GHG",
                        "O O",
                        'O', Block.obsidian,
                        'G', "ingotGold",
                        'H', HexxitGear.hexicalDiamond
                }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(HexxitGear.scaleBoots), new Object[]
                {
                        "   ",
                        "OHO",
                        "O O",
                        'O', Block.obsidian,
                        'H', HexxitGear.hexicalDiamond
                }));


        /* Add repair recipes */
        GameRegistry.addShapelessRecipe(new ItemStack(HexxitGear.tribalHelmet, 1, 0), new ItemStack(HexxitGear.tribalHelmet, 1, Short.MAX_VALUE), HexxitGear.hexicalEssence);
    }
}
