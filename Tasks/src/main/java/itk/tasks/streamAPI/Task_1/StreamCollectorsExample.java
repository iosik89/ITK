package itk.tasks.streamAPI.Task_1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        /*Группировка заказов по продуктам*/
        Map<String,List<Double>> sortedOrders = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                         Collectors.mapping(
                                 Order::getCost,
                                 Collectors.toList())));

        /*Общая стоимость для каждого продукта*/
        Map<String,Double> totalCost = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                        Collectors.summingDouble(Order::getCost)));

        /*Сортировка продуктов по убыванию общей стоимости*/
        Map<String,Double> sortedProdOnCost = totalCost.entrySet().stream()
                .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
                .collect( Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1,v2)->v1,
                        LinkedHashMap::new
                       )
                );

        /*Три самых дорогих продукта*/
        Map<String, Double> maxPriceProduct = orders.stream()
                .collect(Collectors.toMap(Order::getProduct,Order::getCost,Double::max))
                .entrySet().stream()
                .sorted(Map.Entry.<String,Double>comparingByValue())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        /*Три самых дорогих продукта и их общая стоимость*/
        Map<List<String>,Double> totalSum = maxPriceProduct.entrySet().stream()
                .collect(Collectors.teeing(
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList()),
                        Collectors.summingDouble(Map.Entry::getValue),
                        Map::of
                ));

    }
}