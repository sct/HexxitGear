package sct.hexxitgear.setup;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import sct.hexxitgear.HexxitGear;

import java.io.*;
import java.util.Properties;

public class HexxitGearConfig {

    public static Property tribalHelmetId;
    public static Property tribalChestId;
    public static Property tribalLeggingsId;
    public static Property tribalShoesId;
    public static Property scaleHelmetId;
    public static Property scaleChestId;
    public static Property scaleLeggingsId;
    public static Property scaleBootsId;
    public static Property thiefHelmetId;
    public static Property thiefChestId;
    public static Property thiefLeggingsId;
    public static Property thiefBootsId;

    public static File configFolder;

    public static void loadCommonConfig(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try {
            c.load();

            tribalHelmetId = c.getItem(Configuration.CATEGORY_ITEM, "ID.TribalHelmet", 26200);
            tribalChestId = c.getItem(Configuration.CATEGORY_ITEM, "ID.TribalChest", 26201);
            tribalLeggingsId = c.getItem(Configuration.CATEGORY_ITEM, "ID.TribalLeggings", 26202);
            tribalShoesId = c.getItem(Configuration.CATEGORY_ITEM, "ID.TribalShoes", 26203);

            scaleHelmetId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ScaleHelmet", 26204);
            scaleChestId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ScaleChest", 26205);
            scaleLeggingsId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ScaleLeggings", 26206);
            scaleBootsId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ScaleBoots", 26207);

            thiefHelmetId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ThiefHelmet", 26208);
            thiefChestId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ThiefChest", 26209);
            thiefLeggingsId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ThiefLeggings", 26210);
            thiefBootsId = c.getItem(Configuration.CATEGORY_ITEM, "ID.ThiefBoots", 26211);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.save();
        }
    }

    public static String getConfigBaseFolder()
    {
        return "sct";
    }

    public static void setConfigFolderBase(File folder)
    {
        configFolder = new File(folder.getAbsolutePath() + "/" + getConfigBaseFolder() + "/"
                + HexxitGear.modId + "/");
    }

    public static void extractLang(String[] languages)
    {
        String langResourceBase = "/sct/" + HexxitGear.modId + "/lang/";
        for (String lang : languages)
        {
            InputStream is = HexxitGear.instance.getClass().getResourceAsStream(langResourceBase + lang + ".lang");
            try
            {
                File f = new File(configFolder.getAbsolutePath() + "/lang/"
                        + lang + ".lang");
                if (!f.exists())
                    f.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = is.read(buffer)) != -1)
                {
                    os.write(buffer, 0, read);
                }
                is.close();
                os.flush();
                os.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void loadLang()
    {
        File f = new File(configFolder.getAbsolutePath() + "/lang/");
        for (File langFile : f.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".lang");
            }
        }))
        {
            try
            {
                Properties langPack = new Properties();
                langPack.load(new FileInputStream(langFile));
                String lang = langFile.getName().replace(".lang", "");
                LanguageRegistry.instance().addStringLocalization(langPack,
                        lang);
            }
            catch (FileNotFoundException x)
            {
                x.printStackTrace();
            }
            catch (IOException x)
            {
                x.printStackTrace();
            }
        }
    }
}
