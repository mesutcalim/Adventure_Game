import java.util.Scanner;

public class Game {
    Player player;
    Location location;
    Scanner scan = new Scanner(System.in);

    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.println("Oyuna başlamadan önce isminizi giriniz : ");
        String playerName = scan.nextLine();
        player = new Player(playerName);
        player.selectChar();
        start();
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println();
            System.out.println("Eylem gerçekleştirmek için bir yer seçiniz : ");
            System.out.println("1. Güvenli Ev --> Size ait güvenli bir mekan, düşman yok !");
            System.out.println("2. Dükkan --> Silah veya Zırh alabilirsiniz!");
            System.out.println("3. Mağara --> Karşınıza 1-3 zombi çıkabilir !");
            System.out.println("4. Orman --> Karşınıza 1-3 vampir çıkabilir !");
            System.out.println("5. Nehir --> Karşınıza 1-3 ayı çıkabilir !");
            System.out.println("6. Maden --> Karşınıza 1-5 yılan çıkabilir !");
            System.out.print("Gitmek istediğiniz yer : ");
            int sellLoc = scan.nextInt();
            while (sellLoc < 0 || sellLoc > 6) {
                System.out.print("Lütfen geçerli bir yer seçiniz : ");
                sellLoc = scan.nextInt();
            }

            switch (sellLoc) {
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInv().isFood()==false){
                        location = new Cave(player);
                        break;
                    }
                    else{
                        System.out.println("Burayı önceden temizlediniz...!");
                        System.out.println("Güvenli eve dönüyorsunuz...!");
                        break;
                    }

                case 4:
                    if(player.getInv().isFirewood()==false){
                        location = new Forest(player);
                        break;
                    }
                    else{
                        System.out.println("Burayı önceden temizlediniz...!");
                        System.out.println("Güvenli eve dönüyorsunuz...!");
                        break;
                    }
                case 5:
                    if(!player.getInv().isWater()==false){
                        location = new River(player);
                        break;
                    }
                    else{
                        System.out.println("Burayı önceden temizlediniz...!");
                        System.out.println("Güvenli eve dönüyorsunuz...!");
                        break;
                    }
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }

            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInv().isFirewood() && player.getInv().isFood() && player.getInv().isWater()) {
                    System.out.println("**********************************************");
                    System.out.println("Tebrikler Oyunu Kazandınız !");
                    System.out.println("**********************************************");
                    break;
                }
            }

            if (!location.getLocation()) {
                System.out.println("Oyun Bitti !");
                break;
            }

        }
    }
}
