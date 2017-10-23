package texts;

import java.util.ResourceBundle;

/**
 * Created by Cedric on 23.10.2017.
 * Provides all "ui bundle" messages
 */
public class UIMessages {
    private static ResourceBundle gc_resourceBundle = ResourceBundle.getBundle("languageBundles/ui");

    public static String getTestMessage() {
        return gc_resourceBundle.getString("test");
    }
}
