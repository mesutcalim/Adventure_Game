import java.util.Random;

public abstract class BattleLocation extends Location {
    protected Obstacle obstacle;
    protected String award;
    Random r=new Random();
    BattleLocation(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.obstacle = obstacle;
        this.name = name;
        this.award = award;
    }
    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsCount + " tane " + obstacle.getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç :");
        String selCase = input.nextLine();
        selCase = selCase.toUpperCase();
        if (selCase.equals("S")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " bölgesindeki tüm düşmanları temizlediniz !");
                if (this.award.equals("Food") && player.getInv().isFood() == false) {
                    System.out.println(this.award + " Kazandınız! ");
                    player.getInv().setFood(true);
                } else if (this.award.equals("Water") && player.getInv().isWater() == false) {
                    System.out.println(this.award + " Kazandınız! ");
                    player.getInv().setWater(true);
                } else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
                    System.out.println(this.award + " Kazandınız! ");
                    player.getInv().setFirewood(true);
                }
                return true;
            }

            if(player.getHealthy() <= 0) {
                System.out.println("Öldünüz !");
                return false;
            }

        }
        else if(selCase.equals("K")){
            Location location = new SafeHouse(player);
        }
        return true;
    }

    public boolean combat(int obsCount) {
        for (int i = 0; i < obsCount; i++) {
            int defObsHealth = obstacle.getHealth();
            playerStats();
            enemyStats(i);
            while (player.getHealthy() > 0 && obstacle.getHealth() > 0) {
                System.out.print("<V>uruş veya <K>aç :");
                String selCase = input.nextLine();
                selCase = selCase.toUpperCase();
                if (selCase.equals("V")) {
                    if (obstacle.getHealth() > 0 && player.getDefaultHealthy()>0) {
                        int vurusSirasi=r.nextInt(10);
                        if(vurusSirasi>=5){
                            System.out.println("Önce siz vurdunuz !");
                            obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
                            afterHit();

                            System.out.println();
                            System.out.println("Sonra canavar size vurdu !");
                            player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor().getBlock()));
                            afterHit();
                        }
                        else{
                            System.out.println();
                            System.out.println("Önce canavar size vurdu !");
                            player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor().getBlock()));
                            afterHit();

                            System.out.println("Sonra siz vurdunuz !");
                            obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
                            afterHit();
                        }

                    }
                    else if (obstacle.getHealth() == 0) {
                        System.out.println("Düşmanı yendiniz !");
                        player.setMoney(player.getMoney() + obstacle.getAward());
                        System.out.println("Güncel Paranız : " + player.getMoney());
                        obstacle.setHealth(defObsHealth);
                    } else {
                        return false;
                    }

                }
                else {Location location = new SafeHouse(player);
                break;}
                if(obstacle.getName().equals("Yılan") && obstacle.getHealth()<=0){
                    int awardChance = r.nextInt(100);
                    int secondChance = r.nextInt(100);
                    if(awardChance < 15 ){
                        if( secondChance< 50){
                            this.getPlayer().getInv().setWeapon(Weapon.getWeaponObjByID(1));
                            System.out.println("tabanca kazandınız");
                        } else if (secondChance>=50 &&  secondChance< 80) {
                            this.getPlayer().getInv().setWeapon(Weapon.getWeaponObjByID(2));
                            System.out.println("kılıç kazandınız");
                        }
                        else{
                            this.getPlayer().getInv().setWeapon(Weapon.getWeaponObjByID(3));
                            System.out.println("tüfek kazandınız");
                        }
                    }
                    else if(awardChance>=15 && awardChance < 30){
                        if( secondChance< 50){
                            this.getPlayer().getInv().setArmor(Armor.getArmorObjByID(1));
                            System.out.println("hafif zırh kazandınız");
                        } else if (secondChance>=50 &&  secondChance< 80) {
                            this.getPlayer().getInv().setArmor(Armor.getArmorObjByID(2));
                            System.out.println("orta zırh kazandınız");
                        }
                        else{
                            this.getPlayer().getInv().setArmor(Armor.getArmorObjByID(3));
                            System.out.println("ağır zırh kazandınız");
                        }
                    }
                    else if(awardChance>=30 && awardChance < 55){
                        if(secondChance < 50){
                            this.getPlayer().setMoney(getPlayer().getMoney() + 1);
                            System.out.println("1 para kazandınız");
                        } else if (secondChance>=50 && secondChance < 80) {
                            this.getPlayer().setMoney(getPlayer().getMoney() + 5);
                            System.out.println("5 para kazandınız");
                        }
                        else{
                            this.getPlayer().setMoney(getPlayer().getMoney() + 10);
                            System.out.println("10 para kazandınız");
                        }
                    }
                    else {
                        System.out.println("Hiç bir şey kazanamadınız");

                    }
                }
            }

            if (obstacle.getHealth() < player.getHealthy()) {
                System.out.println("Düşmanı yendiniz !");
                player.setMoney(player.getMoney() + obstacle.getAward());
                System.out.println("Güncel Paranız : " + player.getMoney());
                obstacle.setHealth(defObsHealth);
            } else {
                return false;
            }
            System.out.println("-------------------");


        }
        return true;
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri\n--------------");
        System.out.println("Can:" + player.getHealthy());
        System.out.println("Hasar:" + player.getTotalDamage());
        System.out.println("Para:" + player.getMoney());
        System.out.println("Zırh:" + player.getInv().getArmor().getName());
        System.out.println("Silah:" + player.getInv().getWeapon().getName());
    }

    public void enemyStats(int i) {
        System.out.println( i+1 +". " + obstacle.getName() + " Değerleri\n--------------");
        System.out.println("Can:" + obstacle.getHealth());
        System.out.println("Hasar:" + obstacle.getDamage());
        System.out.println("Ödül:" + obstacle.getAward());
    }

    public void afterHit() {
        System.out.println("Oyuncu Canı:" + player.getHealthy());
        System.out.println(obstacle.getName() + " Canı:" + obstacle.getHealth());
        System.out.println();
    }

}
