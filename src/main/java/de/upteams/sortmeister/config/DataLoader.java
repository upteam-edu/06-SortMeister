package de.upteams.sortmeister.config;

import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.service.ContainerService;
import de.upteams.sortmeister.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {
    private final ContainerService containerService;
    private final ItemService itemService;

    public DataLoader(ContainerService containerService, ItemService itemService) {
        this.containerService = containerService;
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) {
        // Predefined containers
        Container glass = containerService.create(new Container("Glass", "#00FF00", "Green container for colored glass"));
        Container paper = containerService.create(new Container("Paper", "#0000FF", "Blue container for paper"));
        Container plastic = containerService.create(new Container("Plastic", "#FFA500", "Orange container for plastic"));

        // Predefined items
        // Glass items
        itemService.create(new Item("glass bottle", glass.getId()));
        itemService.create(new Item("glass jar", glass.getId()));
        itemService.create(new Item("colored glass", glass.getId()));
        itemService.create(new Item("clear glass", glass.getId()));
        itemService.create(new Item("glass packaging", glass.getId()));
        itemService.create(new Item("wine glass", glass.getId()));
        itemService.create(new Item("glass cup", glass.getId()));
        itemService.create(new Item("glass window pane", glass.getId()));

        // Paper items
        itemService.create(new Item("newspaper", paper.getId()));
        itemService.create(new Item("magazine", paper.getId()));
        itemService.create(new Item("cardboard packaging", paper.getId()));
        itemService.create(new Item("white paper", paper.getId()));
        itemService.create(new Item("paper envelope", paper.getId()));
        itemService.create(new Item("office paper", paper.getId()));
        itemService.create(new Item("paper notebook", paper.getId()));

        // Plastic items
        itemService.create(new Item("plastic bottle", plastic.getId()));
        itemService.create(new Item("PET packaging", plastic.getId()));
        itemService.create(new Item("plastic bag", plastic.getId()));
        itemService.create(new Item("plastic container", plastic.getId()));
        itemService.create(new Item("plastic lid", plastic.getId()));
        itemService.create(new Item("yogurt cup", plastic.getId()));
        itemService.create(new Item("food wrap", plastic.getId()));
        itemService.create(new Item("plastic film", plastic.getId()));
    }
}
