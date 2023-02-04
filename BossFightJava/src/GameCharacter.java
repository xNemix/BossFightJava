import java.util.Random;

public class GameCharacter {

    public String Name;
    public int Health;
    public int MaxHealth;
    private int Stamina;
    private int MaxStamina;
    private int Strength;

    private Random Rand;


    public GameCharacter(String name,int health, int stamina) {
        this(name,health,stamina, 20);
    }

    public GameCharacter(String name,int health, int stamina, int strength) {
        Name = name;
        MaxHealth = health;
        Health = health;
        MaxStamina = stamina;
        Stamina = stamina;
        Strength = strength;
        Rand = new Random();
    }


    public void Fight(GameCharacter enemy){

        //If the boss is the one to attack, set Strength to a random value 0 - 30;
        if(Name.equals("Boss")) {
            Strength = Rand.nextInt(0, 31);
        }


        //If stamina is empty, recharge and skip turn.
        if (Stamina <= 0) {
            Recharge();
            return;
        }

        //Do damage to enemy. Remove 10 stamina each time you damage your opponent.
        enemy.Health -= Strength;
        Stamina -= 10;
        System.out.printf("%s hit %s with %d damage, %s now has %d health left.%n", Name, enemy.Name, Strength, enemy.Name, enemy.Health);

        //Reset Strength after attack. This is because of strength potions...
        if (Name.equals("Hero")) Strength = 40;
    }

    private void Recharge() {
        Stamina = MaxStamina;
        Strength = 40;
        System.out.println(Name + " recharged to max stamina");
    }

    public void UseItem(Item item) {
        if (item.ItemType.equals(ItemType.HealthPotion)){
            Health = MaxHealth;
        }
        if (item.ItemType.equals(ItemType.StaminaPotion)){
            Stamina = MaxStamina;
        }
        if (item.ItemType.equals(ItemType.StrengthPotion)){
            Strength += 30;
        }
        System.out.printf("%s used a %s. ", Name, item.ItemType);
    }
}
