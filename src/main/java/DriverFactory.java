import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EventListener;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.NO_RESET;

public class DriverFactory {
    AppiumDriver<?> driver;

    public AppiumDriver<?> setUp(Platform platform) throws MalformedURLException {

        switch (platform) {
            case ANDROID:
                return createAndroidDriver();
            default:
                throw new IllegalArgumentException("No such homework.Platform");
        }
    }


    private AndroidDriver<?> createAndroidDriver () throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability(APP_PACKAGE, "ru.filit.mvideo.b2c");
        capabilities.setCapability(APP_ACTIVITY, "ui.splash.view.SplashScreen");
        capabilities.setCapability(NO_RESET, true);
        URL remoteURL = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(remoteURL, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return (AndroidDriver<?>) driver;
    }
}


