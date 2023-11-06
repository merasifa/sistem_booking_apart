import java.util.Scanner;

public class App{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array 2 dimensi untuk menyimpan informasi pemesanan kamar
        String[][] bookings = new String[4][7]; // Maksimal 4 pengguna, dan 7 informasi pemesanan per pengguna
        String[] usernames = {"User1", "Gabriel", "Mera", "Chika"}; // array untuk pengguna

        System.out.println("=====     Selamat Datang Di Sistem Booking Apartment     =====");
        System.out.println("===========Silahkan login terlebih dahulu===========");
        System.out.println("====================================================");

        boolean found = false;
        int userIndex = -1; // Indeks pengguna yang sedang login
        while (true) {
            System.out.print("Masukkan Username: ");
            String key = input.nextLine();

            boolean y = false;
            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(key)) {
                    found = true;
                    userIndex = i;
                    break;
                }
            }
            if (true) {
                System.out.print("Masukkan password anda: ");
                String password = input.nextLine();

                if (password.equals("123")) {
                    System.out.println("Selamat masuk ke sistem");
                    break; // Keluar dari perulangan while setelah login berhasil
                } else {
                    System.out.println("Peringatan password anda salah!");
                }
            } else {
                System.out.println("Peringatan username anda salah!");
            }
        }

        int hargapermalam = 0, jmlmalam, biayatambahan, totalbiaya;

        System.out.println("Tipe Kamar");
        System.out.println("1 : Studio (Rp.100000)");
        System.out.println("2 : Duplex (Rp.150000)");
        System.out.println("3 : Triplex (Rp.200000)");
        System.out.print("Masukkan tipe kamar yang diinginkan: ");
        int tipekamar = input.nextInt();

        System.out.print("Masukkan jumlah malam yang diinginkan: ");
        while (true) {
            if (input.hasNextInt()) {
                jmlmalam = input.nextInt();
                if (jmlmalam > 0) {
                    break; // Keluar dari perulangan jika input valid
                } else {
                    System.out.println("Jumlah malam harus lebih dari 0. Silakan coba lagi.");
                }
            } else {
                System.out.print("Masukkan angka yang valid: ");
                input.next(); // Hapus token tidak valid dari input
            }
        }

        // Menghitung total biaya berdasarkan tipe kamar
        switch (tipekamar) {
            case 1:
                bookings[userIndex][0] = "Kamar Tipe Studio";
                hargapermalam = 100000;
                break;
            case 2:
                bookings[userIndex][0] = "Kamar Tipe Duplex";
                hargapermalam = 150000;
                break;
            case 3:
                bookings[userIndex][0] = "Kamar Tipe Triplex";
                hargapermalam = 200000;
                break;
            default:
                System.out.println("Tipe kamar tidak valid.");
                return;
        }

        bookings[userIndex][1] = "Jumlah Malam: " + jmlmalam;

        totalbiaya = jmlmalam * hargapermalam;

        // Memproses tambahan fasilitas
        System.out.println("Fasilitas Tambahan");
        System.out.println("1. Laundry");
        System.out.println("2. Sarapan");
        System.out.println("3. Tidak Ada");
        System.out.print("Masukan Fasilitas Tambahan yang diinginkan: ");
        int tambahan = input.nextInt();
        if (tambahan == 1) {
            biayatambahan = 20000 * jmlmalam; // Biaya laundry
            totalbiaya += biayatambahan;
            bookings[userIndex][2] = "Biaya laundry: Rp." + biayatambahan;
        } else if (tambahan == 2) {
            biayatambahan = 25000 * jmlmalam; // Biaya sarapan
            totalbiaya += biayatambahan;
            bookings[userIndex][2] = "Biaya sarapan: Rp." + biayatambahan;
        } else if (tambahan != 3) {
            System.out.println("Tambahan fasilitas tidak valid.");
            return;
        }

        System.out.println("Harga yang harus dibayarkan adalah " + totalbiaya);

        // Pemberian Rating dan Ulasan
        System.out.print("Beri rating (1-5): ");
        int rating = input.nextInt();
        if (rating >= 1 && rating <= 5) {
            bookings[userIndex][3] = "Rating: " + rating;
            input.nextLine(); // Menghapus karakter newline dari input sebelumnya
            System.out.print("Tulis ulasan: ");
            String ulasan = input.nextLine();
            bookings[userIndex][4] = "Ulasan: " + ulasan;
        } else {
            System.out.println("Rating tidak valid.");
            return;
        }

        System.out.println("Pilih Opsi Pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. Transfer Bank");
        System.out.print("Masukkan Pilihan Pembayaran: ");
        int paymentChoice = input.nextInt();
        input.nextLine();

        switch (paymentChoice) {
            case 1:
                bookings[userIndex][5] = "Pembayaran: Cash";
                break;
            case 2:
                bookings[userIndex][5] = "Pembayaran: Transfer Bank";
                break;
            default:
                System.out.println("Pilihan Pembayaran tidak valid.");
                return;
        }

        // Assuming you have a statusPemesanan variable declared elsewhere
        String statusPemesanan = "Dipesan";
        System.out.println("Status Pemesanan: " + statusPemesanan);

        // Lanjutkan dengan kode promo
        boolean promoAktif = false;
        while (true) {
            System.out.print("Apakah Anda ingin mengaktifkan promo? (Ya/Tidak): ");
            String promoChoice = input.next();
            if (promoChoice.equalsIgnoreCase("Ya") || promoChoice.equalsIgnoreCase("Tidak")) {
                if (promoChoice.equalsIgnoreCase("Ya")) {
                    System.out.print("Berapa jumlah malam yang Anda pesan? ");
                    int jmlMalam = input.nextInt();
                    if (jmlMalam >= 3) {
                        double diskon = 0.1; // 10% diskon
                        totalbiaya -= (totalbiaya * diskon);
                        System.out.println("Anda mendapatkan diskon sebesar 10%.");
                    }
                    promoAktif = true;
                    break;
                } else {
                    break;
                }
            } else {
                System.out.println("Masukkan pilihan yang valid (Ya/Tidak).");
            }
        }

        if (promoAktif) {
            bookings[userIndex][6] = "Total biaya setelah promo: Rp." + totalbiaya;
        }

        // Print konfirmasi booking information
        System.out.println("Konfirmasi Pemesanan:");
        System.out.println("Username: " + usernames[userIndex]);
        System.out.println("Tipe Kamar: " + bookings[userIndex][0]);
        System.out.println(bookings[userIndex][1]);
        System.out.println(bookings[userIndex][2]);
        System.out.println(bookings[userIndex][3]);
        System.out.println(bookings[userIndex][4]);
        System.out.println(bookings[userIndex][5]);

        System.out.println("Pemesanan berhasil!");
        System.out.println("Status Pemesanan: " + statusPemesanan);
    }
}