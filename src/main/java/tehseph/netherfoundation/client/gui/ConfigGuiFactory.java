package tehseph.netherfoundation.client.gui;

import tehseph.netherfoundation.NetherFoundation;
import tehseph.netherfoundation.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {}

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parent) {
        return new NFGuiConfig(parent);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public static class NFGuiConfig extends GuiConfig {

        public NFGuiConfig(GuiScreen parent) {
            super(parent, getConfigElements(), Reference.MOD_ID, true, false, Reference.NAME);
        }

        private static List<IConfigElement> getConfigElements() {

            List<IConfigElement> list = new ArrayList<>();
            ConfigElement element;

            element = new ConfigElement(NetherFoundation.CONFIG.getCategory("AngryPigmen"));
            list.add(new DummyConfigElement.DummyCategoryElement("AngryPigmen", "gui.netherfoundation.config.angrypigmen", element.getChildElements()));

            element = new ConfigElement(NetherFoundation.CONFIG.getCategory("Explosions"));
            list.add(new DummyConfigElement.DummyCategoryElement("Explosions", "gui.netherfoundation.config.explosions", element.getChildElements()));

            element = new ConfigElement(NetherFoundation.CONFIG.getCategory("Hellfish"));
            list.add(new DummyConfigElement.DummyCategoryElement("Hellfish", "gui.netherfoundation.config.hellfish", element.getChildElements()));

            element = new ConfigElement(NetherFoundation.CONFIG.getCategory("Processing"));
            list.add(new DummyConfigElement.DummyCategoryElement("Processing", "gui.netherfoundation.config.processing", element.getChildElements()));

            return list;

        }

    }

}
