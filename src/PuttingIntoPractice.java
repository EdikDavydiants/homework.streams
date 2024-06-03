import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println(q1(transactions).toString());
        System.out.println(q2(transactions).toString());
        System.out.println(q3(transactions).toString());
        System.out.println(q4(transactions).toString());
        System.out.println(q5(transactions).toString());
        System.out.println(q6(transactions).toString());
        System.out.println(q7(transactions).toString());
        System.out.println(q8(transactions).toString());
    }


    public static List<Transaction> q1(List<Transaction> transactions) {
        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        return transactions.stream()
                .filter((tr) -> tr.getYear() == 2011)
                .sorted((tr1, tr2) -> {
                    return Integer.compare(tr1.getValue(), tr2.getValue());
                })
                .toList();
    }


    public static List<String> q2(List<Transaction> transactions) {
        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        return transactions.stream()
                .map(tr -> tr.getTrader().getCity())
                .distinct()
                .toList();
    }


    public static List<Trader> q3(List<Transaction> transactions) {
        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity() == "Cambridge")
                .distinct()
                .sorted((trader1, trader2) -> String.CASE_INSENSITIVE_ORDER.compare(trader1.getName(), trader2.getName()))
                .toList();
    }


    public static String q4(List<Transaction> transactions) {
        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        return transactions.stream()
                .map(tr -> tr.getTrader().getName())
                .distinct()
                .sorted((name1, name2) -> String.CASE_INSENSITIVE_ORDER.compare(name1, name2))
                .collect(Collectors.joining("\n", "", ""));
    }


    public static Boolean q5(List<Transaction> transactions) {
        // 5. Выяснить, существует ли хоть один трейдер из Милана.
        return transactions.stream()
                .anyMatch(tr -> tr.getTrader().getCity() == "Milan");
    }


    public static List<Integer> q6(List<Transaction> transactions) {
        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        return transactions.stream()
                .filter(tr -> tr.getTrader().getCity() == "Cambridge")
                .map(Transaction::getValue)
                .toList();
    }


    public static Integer q7(List<Transaction> transactions) {
        // 7. Какова максимальная сумма среди всех транзакций?
        return transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare)
                .get();
    }


    public static Transaction q8(List<Transaction> transactions) {
        // 8. Найти транзакцию с минимальной суммой.
        return transactions.stream()
                .min((tr1, tr2) -> {
                    if (tr1.getValue() > tr2.getValue()) { return 1;}
                    else if (tr1.getValue() == tr2.getValue()) { return 0;}
                    else {return -1;}
                })
                .get();
    }



}