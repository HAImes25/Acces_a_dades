import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.util.*;
import java.io.*;

public class Main {

    static class Carrier {
        String name;
        String status;
        int costCents;

        Carrier(String name, String status, int costCents) {
            this.name = name;
            this.status = status;
            this.costCents = costCents;
        }
    }

    static class Order {
        String id;
        String type;
        String priority;
        boolean paid;
        String customer;
        String product;
        String deliveryNote;
        List<Carrier> carriers;

        Order(String id, String type, String priority, boolean paid, String customer,
              String product, String deliveryNote, List<Carrier> carriers) {
            this.id = id;
            this.type = type;
            this.priority = priority;
            this.paid = paid;
            this.customer = customer;
            this.product = product;
            this.deliveryNote = deliveryNote;
            this.carriers = carriers;
        }

        int getTotalCost() {
            int total = 0;
            for (Carrier c : carriers) {
                total += c.costCents;
            }
            return total;
        }
    }

    static class Store {
        String id;
        String name;
        List<Order> orders;

        Store(String id, String name, List<Order> orders) {
            this.id = id;
            this.name = name;
            this.orders = orders;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Store> stores = parseXML();

        // Part 1 + Part 5: list with filters
        Scanner sc = new Scanner(System.in);

        System.out.print("Filtrar per botiga? (ST01/ST02/ST03/TOTS): ");
        String storeFilter = sc.nextLine().trim().toUpperCase();

        System.out.print("Filtrar per tipus? (ONLINE/PICKUP/EXPRESS/TOTS): ");
        String typeFilter = sc.nextLine().trim().toUpperCase();

        // Create resultat.txt
        PrintWriter writer = new PrintWriter("resultat/resultat.txt", "UTF-8");

        // === PART 1 + 5: Print filtered orders ===
        writer.println("=== LLISTAT DE PEDIDOS ===");
        for (Store store : stores) {
            if (!storeFilter.equals("TOTS") && !store.id.equals(storeFilter)) continue;

            for (Order order : store.orders) {
                if (!typeFilter.equals("TOTS") && !order.type.equals(typeFilter)) continue;

                String line = String.format("[%s-%s] %s | %-7s | %s | %-5s | %-7s | %-21s | %s",
                    store.id, store.name, order.id, order.type, order.priority,
                    order.paid, order.customer, order.product, order.deliveryNote);

                System.out.println(line);
                writer.println(line);
            }
        }

        // === PART 3: Carrier statistics ===
        writer.println();
        writer.println("=== TRANSPORTISTES ===");

        Map<String, Integer> failedCount = new HashMap<>();
        Map<String, List<Integer>> costMap = new HashMap<>();

        for (Store store : stores) {
            for (Order order : store.orders) {
                for (Carrier c : order.carriers) {
                    failedCount.merge(c.name, c.status.equals("FAILED") ? 1 : 0, Integer::sum);
                    if (!c.status.equals("SKIP")) {
                        costMap.computeIfAbsent(c.name, k -> new ArrayList<>()).add(c.costCents);
                    }
                }
            }
        }

        String[] carrierNames = {"dhl", "ups", "local"};

        writer.println("FAILED per transportista:");
        for (String name : carrierNames) {
            writer.println("- " + name + ": " + failedCount.getOrDefault(name, 0));
        }

        writer.println();
        writer.println("Cost mitjà (cents) sense SKIP:");
        for (String name : carrierNames) {
            List<Integer> costs = costMap.get(name);
            if (costs != null && !costs.isEmpty()) {
                double avg = costs.stream().mapToInt(Integer::intValue).average().orElse(0);
                writer.printf("- %s: %.1f cents%n", name, avg);
            } else {
                writer.println("- " + name + ": 0.0 cents");
            }
        }

        // === PART 4: Top 3 most expensive shipments ===
        writer.println();
        writer.println("=== TOP 3 ENVIAMENTS MÉS CARS ===");

        List<Order> allOrders = new ArrayList<>();
        Map<String, String> storeIdToName = new HashMap<>();
        for (Store store : stores) {
            storeIdToName.put(store.id, store.name);
            allOrders.addAll(store.orders);
        }

        allOrders.sort((a, b) -> Integer.compare(b.getTotalCost(), a.getTotalCost()));

        int rank = 1;
        for (Order o : allOrders) {
            if (rank > 3) break;
            String storeName = "";
            for (Store s : stores) {
                if (s.orders.contains(o)) {
                    storeName = "(" + s.id + "-" + s.name + ")";
                    break;
                }
            }
            writer.printf("#%d %s %s - %s - %d cents%n", rank, o.id, storeName, o.product, o.getTotalCost());
            rank++;
        }

        writer.close();
        System.out.println("\nresultat.txt generat correctament!");
    }

    static List<Store> parseXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("src/orders.xml");

        List<Store> stores = new ArrayList<>();
        NodeList storeNodes = doc.getElementsByTagName("Store");

        for (int i = 0; i < storeNodes.getLength(); i++) {
            Element storeElem = (Element) storeNodes.item(i);
            String storeId = storeElem.getAttribute("id");
            String storeName = storeElem.getAttribute("name");

            List<Order> orders = new ArrayList<>();
            NodeList orderNodes = storeElem.getElementsByTagName("Order");

            for (int j = 0; j < orderNodes.getLength(); j++) {
                Element orderElem = (Element) orderNodes.item(j);

                String orderId = orderElem.getAttribute("id");
                String type = orderElem.getAttribute("type");
                String priority = orderElem.getAttribute("priority");
                boolean paid = Boolean.parseBoolean(orderElem.getAttribute("paid"));
                String customer = orderElem.getAttribute("customer");

                String product = orderElem.getElementsByTagName("Product").item(0).getTextContent();

                String deliveryNote = "---";
                NodeList deliveryNodes = orderElem.getElementsByTagName("DeliveryNote");
                if (deliveryNodes.getLength() > 0) {
                    deliveryNote = deliveryNodes.item(0).getTextContent();
                }

                // Parse carriers
                List<Carrier> carriers = new ArrayList<>();
                NodeList carrierNodes = orderElem.getElementsByTagName("Carrier");
                for (int k = 0; k < carrierNodes.getLength(); k++) {
                    Element carrierElem = (Element) carrierNodes.item(k);
                    String name = carrierElem.getAttribute("name");
                    String status = carrierElem.getAttribute("status");
                    int cost = Integer.parseInt(carrierElem.getAttribute("costCents"));
                    carriers.add(new Carrier(name, status, cost));
                }

                orders.add(new Order(orderId, type, priority, paid, customer, product, deliveryNote, carriers));
            }

            stores.add(new Store(storeId, storeName, orders));
        }

        return stores;
    }
}
