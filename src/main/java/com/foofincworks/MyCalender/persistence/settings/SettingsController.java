package com.foofincworks.MyCalender.persistence.settings;

import java.io.*;
import java.util.List;

//Singleton
public class SettingsController {

    private static SettingsController instance;

    private final File settingsTxtFile = new File("src/main/java/com/foofincworks/MyCalender/persistence/settings" +
                                                          "/settings.txt");
    private final Settings settings;

    private SettingsController() {
        settings = new Settings();
        configureSettings();
    }

    //Static Factory Method
    public static SettingsController getInstance() {
        if (instance == null) instance = new SettingsController();
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }

    public void saveSettings(){
        writeFile(settingsTxtFile);
    }

    private void configureSettings() {
        List<String> settingsStrings = readFile(settingsTxtFile);
        if (settingsStrings != null) mapSettings(settingsStrings);
    }

    private void mapSettings(List<String> settingsStrings) {
        settingsStrings.forEach(s -> {
            String[] keyAndParam = s.split("-");
            settings.setParameter(keyAndParam[0], keyAndParam[1]);
        });
    }

    private List<String> readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().toList();
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private void writeFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write("EnableEmailNotifications-" + settings.getStringOfParameter("EnableEmailNotifications"));
            writer.newLine();
            writer.write("EmailAddress-" + settings.getStringOfParameter("EmailAddress"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
