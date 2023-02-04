import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var items = new Item[10];

        AddRandomItems(items);

        var Hero = new GameCharacter("Hero",100,20,40);
        var Boss = new GameCharacter("Boss",400,10);

        var isThirdRound = 0;
        while (Hero.Health > 0 || Boss.Health > 0){
            isThirdRound++;
            System.out.println("--------------------------------");
            if (CheckWinner(Hero)) break;
            if(isThirdRound % 3 == 0){
                var randomItem = GetRandomItem(items, Hero);
                Hero.UseItem(randomItem);
                isThirdRound = 0;
            }
            Hero.Fight(Boss);
            if (CheckWinner(Boss)) break;
            Boss.Fight(Hero);
            System.out.println("--------------------------------");
            System.out.println();
            Thread.sleep(1000);
        }
    }

    private static Item GetRandomItem(Item[] items, GameCharacter Hero) {
        var random = new Random();
        var randomIndex = random.nextInt(0, items.length);

        //Select a random item
        var randomItem = items[randomIndex];

        //If Hero has less than 30 health he can only get a health potion.
        if (Hero.Health <= 30){
            var healthPotion = Arrays.stream(items).filter(item -> item.ItemType.equals(ItemType.HealthPotion)).findFirst().get();;

            // If healthPotion is a healthPotion we set randomItem to a healthPotion
            if(healthPotion.ItemType.equals(ItemType.HealthPotion)) randomItem = healthPotion;
        }
        System.out.printf("A random %s has been dropped! ", randomItem.ItemType);
        return randomItem;
    }

    private static void AddRandomItems(Item[] items) {
        var random = new Random();
        for (int i = 0; i < 10; i++) {
            var randomItemNumber = random.nextInt(0, 3);

            if(randomItemNumber == 0) {
                items[i] = new Item(ItemType.HealthPotion);
            }
            else if(randomItemNumber == 1) {
                items[i] = new Item(ItemType.StrengthPotion);
            }
            else {
                items[i] = new Item(ItemType.StaminaPotion);
            }
        }
    }


    private static boolean CheckWinner(GameCharacter character) {
        if (character.Health > 0) {
            return false;
        }
        var winnerName = character.Name.equals("Hero") ? "Boss" : "Hero";
        System.out.println(winnerName + " won the game");
        return true;
    }
}