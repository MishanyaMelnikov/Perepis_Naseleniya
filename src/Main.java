import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> family = persons.stream()
                .filter(sex -> sex.getSex().equals(Sex.MAN))
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(family);

        List<Person> workers = persons.stream()
                .filter(age -> age.getAge() >= 18)
                .filter(sex -> sex.getSex().equals(Sex.WOMAN) && sex.getAge() < 60 || sex.getSex().equals(Sex.MAN) && sex.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workers);

    }
}
