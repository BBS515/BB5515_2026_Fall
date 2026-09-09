import java.util.Random;
import java.util.Scanner;

public class NimGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] piles = {3, 4, 5};
        boolean playerTurn = true;

        System.out.println("🎮 Nim Oyunu Başladı!");
        System.out.println("Kurallar: Sırayla bir yığından taş al. Son taşı alan kaybeder!");

        while (sum(piles) > 0) {
            if (playerTurn) {
                System.out.println("\nSenin sıran!");
                printPiles(piles);

                System.out.print("Hangi yığından taş almak istiyorsun? (1-3): ");
                int pile = scanner.nextInt() - 1;

                System.out.print("Kaç taş almak istiyorsun?: ");
                int count = scanner.nextInt();

                if (pile < 0 || pile >= piles.length || count <= 0 || count > piles[pile]) {
                    System.out.println("❌ Geçersiz hamle, tekrar dene.");
                    continue;
                }

                piles[pile] -= count;
                System.out.println("✅ Yığın " + (pile + 1) + " 'den " + count + " taş aldın.");
            } else {
                // Bilgisayarın hamlesi (rastgele)
                int pile;
                do {
                    pile = random.nextInt(piles.length);
                } while (piles[pile] == 0);

                int count = random.nextInt(piles[pile]) + 1;
                piles[pile] -= count;
                System.out.println("\n💻 Bilgisayar Yığın " + (pile + 1) + " 'den " + count + " taş aldı.");
            }

            if (sum(piles) == 0) {
                if (playerTurn) {
                    System.out.println("\n😢 Son taşı sen aldın. Kaybettin!");
                } else {
                    System.out.println("\n🎉 Bilgisayar son taşı aldı. Sen kazandın!");
                }
                break;
            }

            playerTurn = !playerTurn; // sıra değiştir
        }

        scanner.close();
    }

    // Yığınların toplamını döndürür
    public static int sum(int[] piles) {
        int total = 0;
        for (int p : piles) {
            total += p;
        }
        return total;
    }

    // Yığınları ekrana yazdırır
    public static void printPiles(int[] piles) {
        System.out.print("Yığınlar: ");
        for (int i = 0; i < piles.length; i++) {
            System.out.print("[" + (i + 1) + ":" + piles[i] + "] ");
        }
        System.out.println();
    }
}
