package com.foofincworks.MyCalender.persistence;

import java.io.*;

//Singleton
public class EventIdPersistence {


    private static EventIdPersistence instance;
    private int eventId;
    private final File eventIdFile;

    private EventIdPersistence() {
        eventIdFile = new File("src/main/java/com/foofincworks/MyCalender/persistence/EventId");
        eventId = getEventIdFromFile();
    }

    //Static Factory Method
    public static EventIdPersistence getInstance() {
        if (instance == null) instance = new EventIdPersistence();
        return instance;
    }

    public int getEventId() {

        int id = eventId;

        saveEventIdToFile(++eventId);

        return id;
    }

    private int getEventIdFromFile() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(eventIdFile))) {

            return eventId = Integer.parseInt(bufferedReader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveEventIdToFile(int eventId) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(eventIdFile))) {

            bufferedWriter.write(String.valueOf(eventId));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
