import java.util.Scanner;

public class ToolStore extends NormalLocation {

    ToolStore(Player player) {
        super(player, "Mağaza");
    }

    public boolean getLocation() {
        System.out.println("Para : " + player.getMoney());
        System.out.println("1. Silahlar");
        System.out.println("2. Zırhlar");
        System.out.println("3. Çıkış");
        int selectLocation = input.nextInt();

        switch(selectLocation) {
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Çıkış yapılıyor");
                break;

        }
        return true;

    }

    public void printWeapon()
    {
        System.out.println("-----------------Silahlar----------------------");
        for(Weapon w: Weapon.weapons()){
            System.out.println(w.getId() +" " + w.getName()+" "+w.getDamage()+" "+ w.getPrice());
        }
    }

    public void buyWeapon() {
        System.out.println("Bir silah seçiniz:  ");
        int selectedWeaponID = input.nextInt();
        while (selectedWeaponID < 1 || selectedWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz değer. Lütfen Tekrar deneyiniz...");
            selectedWeaponID = input.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectedWeaponID);
        if(selectedWeapon != null ){
            if (selectedWeapon.getPrice()>this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır.");
            }
            else{
                System.out.println(selectedWeapon.getName() + "  satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız : "+ this.getPlayer().getMoney());
            }
        }
    }
    public void printArmor()
    {
        System.out.println("-----------------Zırhlar----------------------");
        for(Armor a: Armor.armors()){
            System.out.println(a.getId() +" " + a.getName()+" "+a.getBlock()+" "+ a.getPrice());
        }
    }
    public void buyArmor() {
        System.out.println("Bir zırh seçiniz:  ");
        int selectedArmorID = input.nextInt();
        while (selectedArmorID < 1 || selectedArmorID > Armor.armors().length){
            System.out.println("Geçersiz değer. Lütfen Tekrar deneyiniz...");
            selectedArmorID = input.nextInt();
        }
        Armor selectedArmor = Armor.getArmorObjByID(selectedArmorID);
        if(selectedArmor != null ){
            if (selectedArmor.getPrice()>this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır.");
            }
            else{
                System.out.println(selectedArmor.getName() + "  satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız : "+ this.getPlayer().getMoney());
            }
        }
    }
}
