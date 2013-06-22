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

package sct.hexxitgear.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import sct.hexxitgear.HexxitGear;

import java.util.Random;

public class HGWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (HexxitGear.getDimBlacklist().contains(world.provider.dimensionId))
            return;

        if (world.getWorldInfo().getTerrainType().getWorldTypeName().equals("flat"))
            return;

        int xMin = chunkX << 4;
        int zMin = chunkZ << 4;

        int startX = xMin + random.nextInt(16);
        int startZ = zMin + random.nextInt(16);

        int tries = random.nextInt(2);

        for (int i=0; i < tries; i++) {
            int x = startX + random.nextInt(8) - random.nextInt(8);
            int z = startZ + random.nextInt(8) - random.nextInt(8);
            int y = world.getHeightValue(x, z);

            if ((world.isAirBlock(x, y, z) || (world.getBlockId(x,y,z) == Block.snow.blockID)) && HexxitGear.hexbiscus.canBlockStay(world, x, y, z)) {
                if (random.nextInt(50) > 1)
                    continue;

                world.setBlock(x, y, z, HexxitGear.hexbiscus.blockID, 0, 0);
            }
        }
    }
}
