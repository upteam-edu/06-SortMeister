package de.upteams.sortmeister.config;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.dto.ItemDto;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.service.AdvertService;
import de.upteams.sortmeister.service.ContainerService;
import de.upteams.sortmeister.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final AdvertService advertService;
    private final ContainerService containerService;
    private final ItemService itemService;

    public DataLoader(AdvertService advertService, ContainerService containerService, ItemService itemService) {
        this.advertService = advertService;
        this.containerService = containerService;
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataLoader running... Populating initial data.");

        if (!advertService.getAllAdverts().isEmpty() || !containerService.getAll().isEmpty() || !itemService.getAllItems().isEmpty()) {
            System.out.println("Database already contains data. Skipping DataLoader.");
            return;
        }

        advertService.createAdvert(new AdvertDto(null, "Summer Sale", "Big discounts on all items!", "http://example.com/summer-sale.jpg"));
        advertService.createAdvert(new AdvertDto(null, "New Arrivals", "Check out our latest collection.", "http://example.com/new-arrivals.png"));
        advertService.createAdvert(new AdvertDto(null, "Clearance Event", "Last chance for great deals!", null)); // Пример с null для photoUrl

        Container paperContainer = containerService.createContainer(new Container(null, "Paper", "#ADD8E6", "For paper waste like newspapers and magazines."));
        Container plasticContainer = containerService.createContainer(new Container(null, "Plastic", "#FFD700", "For plastic bottles, containers, and bags."));
        Container glassContainer = containerService.createContainer(new Container(null, "Glass", "#90EE90", "For glass bottles and jars."));
        Container organicContainer = containerService.createContainer(new Container(null, "Organic", "#8B4513", "For food scraps, garden waste, and compostable materials."));
        Container metalContainer = containerService.createContainer(new Container(null, "Metal", "#C0C0C0", "For metal cans, foil, and other metal items."));

        itemService.createItem(new ItemDto(null, "Newspaper", "Paper", "Daily news from yesterday.", paperContainer.getId()));
        itemService.createItem(new ItemDto(null, "Milk Bottle", "Plastic", "Empty milk bottle.", plasticContainer.getId()));
        itemService.createItem(new ItemDto(null, "Glass Jar", "Glass", "Used for pickles.", glassContainer.getId()));
        itemService.createItem(new ItemDto(null, "Banana Peel", "Organic", "Compostable fruit waste.", organicContainer.getId()));
        itemService.createItem(new ItemDto(null, "Soda Can", "Metal", "Empty aluminum can.", metalContainer.getId()));

        System.out.println("DataLoader finished. Data populated."); // Для отладки
    }
}