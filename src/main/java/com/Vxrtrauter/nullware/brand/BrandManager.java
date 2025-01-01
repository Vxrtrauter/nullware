package com.Vxrtrauter.nullware.brand;

import java.util.Arrays;
import java.util.List;

public class BrandManager {
    private static final List<String> brands = Arrays.asList("vanilla", "lunarclient:v2.16.8-2433", "CB", "custom");
    private static int currentBrandIndex = 0;
    private static String customBrandName = "CustomBrandNameHere";

    public static String getCurrentBrand() {
        return brands.get(currentBrandIndex).equals("custom") ? customBrandName : brands.get(currentBrandIndex);
    }

    public static void cycleNextBrand() {
        currentBrandIndex = (currentBrandIndex + 1) % brands.size();
    }

    public static String getBrandButtonText() {
        String currentBrand = getCurrentBrand();
        return "Brand: " + currentBrand;
    }

    public static void setCustomBrandName(String name) {
        customBrandName = name;

        currentBrandIndex = brands.indexOf("custom");
    }

    public static String getCustomBrandName() {
        return customBrandName;
    }
}
