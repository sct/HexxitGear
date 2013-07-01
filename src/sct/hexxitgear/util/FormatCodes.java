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

package sct.hexxitgear.util;

/**
 * Enum of format codes used by the vanilla Minecraft font renderer
 *
 * @author MachineMuse
 */
public enum FormatCodes
{
    Black("\u00A70"),
    DarkBlue("\u00A71"),
    DarkGreen("\u00A72"),
    DarkAqua("\u00A73"),
    DarkRed("\u00A74"),
    Purple("\u00A75"),
    Gold("\u00A76"),
    Grey("\u00A77"),
    DarkGrey("\u00A78"),
    Indigo("\u00A79"),
    BrightGreen("\u00A7a"),
    Aqua("\u00A7b"),
    Red("\u00A7c"),
    Pink("\u00A7d"),
    Yellow("\u00A7e"),
    White("\u00A7f"),
    RandomChar("\u00A7k"),
    Bold("\u00A7l"),
    Strike("\u00A7m"),
    Underlined("\u00A7n"),
    Italic("\u00A7o"),
    Reset("\u00A7r");

    public String format;

    private FormatCodes(String s)
    {
        this.format = s;
    }
}
