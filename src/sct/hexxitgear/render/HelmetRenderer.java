package sct.hexxitgear.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import sct.hexxitgear.item.ItemSkullHelmet;
import sct.hexxitgear.model.SkullHelmet;

public class HelmetRenderer implements IItemRenderer {

    private static final SkullHelmet SKULL_HELMET = new SkullHelmet();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return item.getItem() instanceof ItemSkullHelmet && type.equals(ItemRenderType.EQUIPPED);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper) {
        switch (helper) {
            case ENTITY_ROTATION:
                return true;
            case ENTITY_BOBBING:
                return true;
            case EQUIPPED_BLOCK:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Entity player = null;
        for (Object obj : data) {
            if (obj instanceof EntityLiving) {
                player = (EntityLiving) obj;
            }
        }

        if (player != null) {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture("/textures/maps/SkullHelmet.png");
            SKULL_HELMET.render(player, 0, 0, 0, 0, 0, 0.10F);
        }

    }
}