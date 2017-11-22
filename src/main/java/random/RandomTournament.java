package random;

import hibernate.model.Arena;
import hibernate.model.Technique;
import hibernate.model.Tournament;
import hibernate.model.Warrior;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTournament {

    private ArrayList<Arena> arenas = new ArrayList<>();
    private ArrayList<String> arenasNames = new ArrayList<>();
    private int tournamentCount = 0;

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public RandomTournament() {
        dataGetter1(arenasNames, "data/random/arenas");
        createRandomArena();
    }

    private void createRandomArena() {
        for (String s : arenasNames
                ) {
            Arena arena = new Arena();
            arena.setName(s);
            arenas.add(arena);
        }
    }

    public Tournament get(final ArrayList<Warrior> warriors, final int howManyParticipants) {
        Tournament result = new Tournament();

        result.setName(getTournamentName());
        result.setArena(getRandomArena());
        result.setDate(getRandomDate());
        result.setParticipants(getRandomParticipants(warriors, howManyParticipants));

        return result;
    }

    public Tournament getWithoutArenaAndParticipants() {
        Tournament result = new Tournament();

        result.setName(getTournamentName());
        result.setDate(getRandomDate());

        return result;
    }

    private void dataGetter1(ArrayList<String> arrayList, String pathFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(pathFile)))) {
            String line;
            while ((line = br.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTournamentName() {
        return "Turniej " + ++tournamentCount;
    }

    private Arena getRandomArena() {
        return arenas.get(getRandomIntBetween(0, arenas.size()));
    }

    /***
     * Random number fom low to high-1
     * @param low
     * @param high
     * @return
     */
    private int getRandomIntBetween(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }


    private LocalDate getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private ArrayList<Warrior> getRandomParticipants(final ArrayList<Warrior> warriors, final int howManyParticipants) {
        ArrayList<Warrior> result = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < warriors.size(); i++) {
            integers.add(i);
        }
        for (int i = 0; i < howManyParticipants; i++) {
            int integerIndex = getRandomIntBetween(0, integers.size());
            int toTake = integers.get(integerIndex);
            result.add(warriors.get(toTake));
            integers.remove(integerIndex);
        }
        return result;
    }
}
