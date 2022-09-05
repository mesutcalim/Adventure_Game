
public class Inventory {
    private boolean water,food,firewood;
    private Weapon weapon;
    private Armor armor;
    private int damage;

    Inventory(){
        this.water = false;
        this.food = false;
        this.firewood = false;
        this.weapon= new Weapon(0,"Yumruk",0,0);
        this.armor = new Armor(0,"Yok",0,0);
        this.damage = weapon.getDamage();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }



}

