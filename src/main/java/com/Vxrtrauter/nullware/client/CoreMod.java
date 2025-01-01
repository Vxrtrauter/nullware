package com.Vxrtrauter.nullware.client;

import com.Vxrtrauter.nullware.brand.BrandTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.TransformerExclusions("com.Vxrtrauter.nullware")
public class CoreMod implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{BrandTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
