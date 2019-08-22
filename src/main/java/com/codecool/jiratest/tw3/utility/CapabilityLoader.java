package com.codecool.jiratest.tw3.utility;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.Platform;

public class CapabilityLoader {

    public static DesiredCapabilities setCapability(){
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setPlatform(Platform.LINUX);
        return capability;
    }

}
