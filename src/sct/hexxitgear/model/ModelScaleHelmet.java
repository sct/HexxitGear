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

package sct.hexxitgear.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScaleHelmet extends ModelBiped {

    ModelRenderer head;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;

    public ModelScaleHelmet() {

        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        Shape1 = new ModelRenderer(this, 0, 16);
        Shape1.addBox(-5F, -9F, -5F, 10, 9, 10);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 24, 0);
        Shape2.addBox(-6F, -7F, -2F, 1, 5, 3);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 4, 20);
        Shape3.addBox(-7F, -8F, 0F, 1, 4, 2);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 2, 2);
        Shape4.addBox(-9F, -9F, 2F, 2, 1, 1);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 4, 4);
        Shape5.addBox(-8F, -8F, 1F, 1, 3, 1);
        Shape5.setRotationPoint(0F, 0F, 0F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 2, 2);
        Shape6.addBox(-9F, -7F, 2F, 2, 1, 1);
        Shape6.setRotationPoint(0F, 0F, 0F);
        Shape6.setTextureSize(64, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 24, 0);
        Shape7.addBox(5F, -7F, -2F, 1, 5, 3);
        Shape7.setRotationPoint(0F, 0F, 0F);
        Shape7.setTextureSize(64, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 4, 20);
        Shape8.addBox(6F, -8F, 0F, 1, 4, 2);
        Shape8.setRotationPoint(0F, 0F, 0F);
        Shape8.setTextureSize(64, 64);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 4, 4);
        Shape9.addBox(7F, -9F, 1F, 1, 3, 1);
        Shape9.setRotationPoint(0F, 1F, 0F);
        Shape9.setTextureSize(64, 64);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 2, 2);
        Shape10.addBox(7F, -9F, 2F, 2, 1, 1);
        Shape10.setRotationPoint(0F, 0F, 0F);
        Shape10.setTextureSize(64, 64);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 2, 2);
        Shape11.addBox(7F, -7F, 2F, 2, 1, 1);
        Shape11.setRotationPoint(0F, 0F, 0F);
        Shape11.setTextureSize(64, 64);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaledUp = f5 + 0.009F;
        float suLarge = f5 + 0.002F;
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(scaledUp);
        Shape1.render(suLarge);
        Shape2.render(suLarge);
        Shape3.render(suLarge);
        Shape4.render(suLarge);
        Shape5.render(suLarge);
        Shape6.render(suLarge);
        Shape7.render(suLarge);
        Shape8.render(suLarge);
        Shape9.render(suLarge);
        Shape10.render(suLarge);
        Shape11.render(suLarge);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newX = this.bipedHeadwear.rotateAngleX;
        float newY = this.bipedHeadwear.rotateAngleY;
        setRotation(head, newX, newY, 0);
        setRotation(Shape1, newX, newY, 0);
        setRotation(Shape2, newX, newY, 0);
        setRotation(Shape3, newX, newY, 0);
        setRotation(Shape4, newX, newY, 0);
        setRotation(Shape5, newX, newY, 0);
        setRotation(Shape6, newX, newY, 0);
        setRotation(Shape7, newX, newY, 0);
        setRotation(Shape8, newX, newY, 0);
        setRotation(Shape9, newX, newY, 0);
        setRotation(Shape10, newX, newY, 0);
        setRotation(Shape11, newX, newY, 0);

    }
}
