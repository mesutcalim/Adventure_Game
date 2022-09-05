public class SafeHouse extends NormalLocation {

    SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    public boolean getLocation() {
        player.setHealthy(player.getDefaultHealthy());
        System.out.println("İyileştiniz...");
        System.out.println("Şuan GÜvenli Evdesiniz.");
        return true;
    }

}
