package homework.stream;

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

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей).
        List<Transaction> sortedTransactionsFor2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println(sortedTransactionsFor2011);

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println(cities);

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println(tradersFromCambridge);

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.
        String sortedOfNameTraders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining("\t"));
        System.out.println(sortedOfNameTraders);

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean traderFromMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(traderFromMilan ? "There's a trader from Milan on the list." : "There's no trader from Milan on the list.");

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        List<Integer> sumTransactionFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .toList();
        System.out.println(sumTransactionFromCambridge);

        //7. Какова максимальная сумма среди всех транзакций?
        Integer maxSumTransactions = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();
        System.out.println(maxSumTransactions);

        //8. Найти транзакцию с минимальной суммой.
        Integer minSumTransactions = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo)
                .get();
        System.out.println(minSumTransactions);
    }
}