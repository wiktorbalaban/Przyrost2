package random;

import hibernate.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomWarrior {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> surnames = new ArrayList<>();
    private ArrayList<String> wifeNames = new ArrayList<>();
    private ArrayList<String> bodyParts = new ArrayList<>();
    private ArrayList<String> adjectives = new ArrayList<>();
    private ArrayList<String> techniquesNames = new ArrayList<>();
    private ArrayList<String> animalsNames = new ArrayList<>();
    private ArrayList<String> arenas = new ArrayList<>();
    private ArrayList<FightingSchool> fightingSchools = new ArrayList<>();
    private ArrayList<Technique> techniques = new ArrayList<>();


    public RandomWarrior() {
        dataGetter1(names, "data/random/names");
        dataGetter1(surnames, "data/random/surnames");
        dataGetter1(wifeNames, "data/random/wife-names");
        dataGetter2(bodyParts, "data/random/body_parts");
        dataGetter1(adjectives, "data/random/adjectives");
        dataGetter1(techniquesNames, "data/random/techniques");
        dataGetter1(animalsNames, "data/random/schools");
        dataGetter1(arenas, "data/random/arenas");

        createRandomSchools();
        createRandomTechniques();
    }

    public RandomWarrior(boolean test) {

    }

    public ArrayList<String> getNames() {
        return names;
    }

    //names, surnames, wifenames, adjectives *
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

    //bodyparts
    private void dataGetter2(ArrayList<String> arrayList, String pathFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(pathFile)))) {
            String line;
            while ((line = br.readLine()) != null) {
                //arrayList.add(line);
                String[] parts = line.split("\\s+");
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("n") || parts[i].equals("m") || parts[i].equals("f") || parts[i].equals("i"))
                        break;
                    else {
                        if (i > 0) builder.append(" ");
                        builder.append(parts[i]);
                    }
                }
                arrayList.add(builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public hibernate.model.Warrior get() {
        hibernate.model.Warrior result = new hibernate.model.Warrior();

        result.setName(getRandomName());
        result.setSurname(getRandomSurname());

        Nickname nickname = new Nickname();
        nickname.setName(getRandomNicknameName());
        result.setNickname(nickname);

        result.setPower(getRandomIntBetween(1, 10001));
        result.setFightingSchool(getRandomFightingSchool());
        result.setTechniques(getRandomTechniques());
        result.setWife(getRandomWife());

        return result;
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

    private String getRandomName() {
        return names.get(getRandomIntBetween(0, names.size()));
    }

    private String getRandomSurname() {
        return surnames.get(getRandomIntBetween(0, surnames.size()));
    }

    private String getRandomNicknameName() {
        return adjectives.get(getRandomIntBetween(0, adjectives.size()))
                + " " + bodyParts.get(getRandomIntBetween(0, bodyParts.size()));
    }

    private void createRandomSchools() {
        for ( String s: animalsNames
                ) {
            FightingSchool fightingSchool=new FightingSchool();
            fightingSchool.setName("Szkoła " + " " + s);
            fightingSchool.setPercentageToPower(getRandomIntBetween(50,201));
            fightingSchools.add(fightingSchool);
        }
    }

    private FightingSchool getRandomFightingSchool() {
        return fightingSchools.get(getRandomIntBetween(0, fightingSchools.size()));
    }

    ArrayList<Technique> getRandomTechniques(){
        ArrayList<Technique> result=new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();

        int howMany=getRandomIntBetween(0,11);
        for(int i=0;i<techniques.size();i++){
            integers.add(i);
        }
        for (int i = 0; i < howMany && i < techniques.size(); i++) {
            int integerIndex = getRandomIntBetween(0, integers.size());
            int toTake = integers.get(integerIndex);
            result.add(techniques.get(toTake));
            integers.remove(integerIndex);
        }
        return result;
    }

    private void createRandomTechniques() {
        for ( String s: techniquesNames
                ) {
            Technique technique=new Technique();
            technique.setName(s);
            technique.setPercentageToPower(getRandomIntBetween(0,10001));
            techniques.add(technique);
        }
//        ArrayList<Warrior> result = new ArrayList<>();
//        ArrayList<Integer> integers = new ArrayList<>();
//
//        for (int i = 0; i < warriors.size(); i++) {
//            integers.add(i);
//        }
//        for (int i = 0; i < howManyParticipants; i++) {
//            int integerIndex = getRandomIntBetween(0, integers.size());
//            int toTake = integers.get(integerIndex);
//            result.add(warriors.get(toTake));
//            integers.remove(integerIndex);
//        }
    }

    private Wife getRandomWife(){
        Wife result=new Wife();
        result.setName(getRandomName());
        result.setPercentageToPower(getRandomIntBetween(1,31));
        result.setSurname(getRandomWifeName());
        return result;
    }

    private String getRandomWifeName(){
        return wifeNames.get(getRandomIntBetween(0, wifeNames.size()));
    }
}
