import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Product{
    int id;
    String name;
    float price;
    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Float.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
public class Main
{
    public static void main(String[] args) {
System.out.println("#######..............Stream methods output ....######");
            List<Product> productsList = new ArrayList<Product>();
            //Adding Products
            productsList.add(new Product(1,"HP Laptop",25000f));
        productsList.add(new Product(1,"HP Laptop",25000f));
            productsList.add(new Product(2,"Dell Laptop",30000f));
        productsList.add(new Product(2,"Dell Laptop",30000f));
            productsList.add(new Product(3,"Lenevo Laptop",28000f));
            productsList.add(new Product(4,"Sony Laptop",28000f));
        productsList.add(new Product(4,"Sony Laptop",28000f));
            productsList.add(new Product(5,"Apple Laptop",90000f));
            List<Float> productPriceList = new ArrayList<Float>();

        List<Float> productPriceList2 =productsList.stream()
                .filter(p -> p.price > 30000)// filtering data
                .map(p->p.price)        // fetching price
                .collect(Collectors.toList()); // collecting as list
        System.out.println(productPriceList2);

        System.out.println(" stream iterate");

        // Stream iterate
        Stream.iterate(1, x->x+1)
                .filter(x->x%5==0)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("ForEach with filter ");

        productsList.stream()
                .filter(product -> product.price == 30000)
                .forEach(product -> System.out.println(product.name));
        System.out.println("accumulating price using reduce with lamda function ");
        Float totalPrice = productsList.stream()
                .map(product->product.price)
                .reduce(0.0f,(sum, price)->sum+price);   // accumulating price
        System.out.println(totalPrice);
        System.out.println("accumulating price using reduce with Float methods ");
        float totalPrice2 = productsList.stream()
                .map(product->product.price)
                .reduce(0.0f,Float::sum);   // accumulating price, by referring method of Float class
        System.out.println(totalPrice2);
        System.out.println("accumulating price using colletors ");
        double totalPrice3 = productsList.stream()
                .collect(Collectors.summingDouble(product->product.price));
        System.out.println(totalPrice3);
        System.out.println(" MAX using lamda expression  ");
        Product productA = productsList.stream().max((product1, product2)->product1.price > product2.price ? 1: -1).get();
        System.out.println(productA.price);
        System.out.println(" min() method to get min Product price  ");
        Product productB = productsList.stream().min((product1, product2)->product1.price > product2.price ? 1: -1).get();
        System.out.println(productB.price);
        System.out.println(" // count number of products based on the filter   ");
        long count = productsList.stream()
                .filter(product->product.price<30000)
                .count();
        System.out.println(count);
System.out.println("convert to set using  collect() it as Set remove duplicate elements)");
        Set<Float> productPriceList3 =
                productsList.stream()
                        .filter(product->product.price < 30000)   // filter product on the base of price
                        .map(product->product.price)
                        .collect(Collectors.toSet());
        System.out.println(productPriceList3);
        System.out.println("c collect() to map");
        Map<Integer,String> productPriceMap =
                productsList.stream().distinct()
                        .collect(Collectors.toMap(p->p.id, p->p.name));

        System.out.println(productPriceMap);
        System.out.println("c collect() to List");
        List<Float> productPriceList4 =
                productsList.stream()
                        .filter(p -> p.price > 30000) // filtering data
                        .map(Product::getPrice)         // fetching price by referring getPrice method
                        .collect(Collectors.toList());  // collecting as list
        System.out.println(productPriceList4);
        System.out.println("distinct() ");
        productsList.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("AnyMatch() ");
       boolean answer =  productsList.stream().anyMatch(x -> x.getPrice() == 30000);
        boolean answer2 =  productsList.stream().anyMatch(x -> x.getName().contains("Laptop"));
        System.out.println(answer);
        System.out.println(answer2);

        System.out.println("findAny()");
        Optional<Product> aoptional =  productsList.stream().findAny();
        System.out.println(aoptional.get());
        System.out.println("findFirst()");
        Optional<Product> aoptional2 =  productsList.stream().findFirst();
        System.out.println(aoptional2.get());

        System.out.println("anyMatch()");
        boolean answer4 = productsList.stream().anyMatch (x -> x.getPrice() == 30000);
        System.out.println(answer4);

        System.out.println("equals()");
        boolean answer6 = productsList.stream().equals (new Product(2,"Dell Laptop",30000f));
        System.out.println(answer6);

        System.out.println("noneMatch()");
        boolean answer7 = productsList.stream().noneMatch (x -> x.getPrice() == 0);
        System.out.println(answer7);

        System.out.println("dropWhile()");
         productsList.stream().dropWhile (x -> x.getName().contains("HP")).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("toUnmodifiableList()");
        productsList.stream().dropWhile (x -> x.getName().contains("HP")).collect(Collectors.toUnmodifiableList()).forEach(System.out::println);

        System.out.println("parallelStream()");
        boolean isParalle = productsList.parallelStream().dropWhile (x -> x.getName().contains("HP")).isParallel();

        System.out.println("isParallel "+isParalle );

        System.out.println("parallelStream()");
        //productPriceList, productPriceList2, productPriceList4;
        System.out.println("flatMap()");
    ArrayList<ArrayList<Float>> combinedList =  new ArrayList<>();
    combinedList.add(new ArrayList<>(productPriceList));
        combinedList.add(new ArrayList<>(productPriceList2));
        combinedList.add(new ArrayList<>(productPriceList4));

        combinedList.stream().flatMap(x -> x.stream()).collect(Collectors.toList()).parallelStream().forEach(System.out::println);
        System.out.println("flatMapToDouble()");
        combinedList.stream().flatMapToDouble(x -> x.stream().mapToDouble(t->new Double(t/13))).forEach(System.out::println);
        System.out.println("mapToInt()");
        productsList.stream().mapToInt(x -> new Float(x.getPrice()).intValue()/5).forEach(System.out::println);

        System.out.println("flatMapToLong()");
        combinedList.stream().flatMapToLong(x ->x.stream().mapToLong(f->f.longValue() * 99999999)).forEach(System.out::println);
        System.out.println("peek()");
        long sum = productsList.stream().map(x->x.getPrice()).peek(System.out::println).count();
        System.out.println("forEachOrdered()");
        productsList.stream().forEachOrdered(System.out::println);

        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        System.out.println("sorted()");
        list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()).forEach(System.out::print);


        productsList.stream().sorted(Comparator.comparing(x->x.getPrice())).forEach(System.out::println);
        System.out.println("skip()");
        productsList.stream().skip(5).forEach(System.out::println);
        System.out.println("toArray()");
        Arrays.stream(productsList.stream().toArray()).forEach(System.out::println);
        System.out.println("takeWhile()");
        productsList.stream().takeWhile(x-> x.getName().contains("HP")).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("parallel()");
        productsList.stream().parallel().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("sequential()");
        productsList.stream().sequential().collect(Collectors.toList()).stream().parallel().forEach(System.out::println);
        System.out.println("unordered()");
        productsList.stream().unordered().forEach(System.out::println);

        list.stream()
                .unordered()
                .collect(Collectors.toList()).forEach(System.out::print);

        productsList.stream().close();
        System.out.println("iterator()");
        productsList.stream().skip(5).iterator().forEachRemaining(System.out::println);
        System.out.println("spliterator()");
        productsList.stream().skip(5).spliterator().forEachRemaining(System.out::println);

        productsList.stream().

    }
}