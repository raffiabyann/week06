package week06.raffi.id.umn.ac;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        ArrayList<Order> daftarPesanan = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        daftarBarang.add(new Handphone(1, 14499000, "Samsung S9+ Hitam", 10, "Hitam"));
        daftarBarang.add(new Handphone(2, 17999000, "iPhone X Hitam", 10, "Hitam"));
        daftarBarang.add(new Voucher(3, 20000, "Google Play", 100, 0.1));

        int pilih;
        do {
            System.out.println("-----------Menu Toko Voucher & HP-----------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            pilih = sc.nextInt();

            switch (pilih) {
                case 1 -> {
                    System.out.println("Daftar Barang Toko Voucher & HP");
                    for (int i = 0; i < daftarBarang.size(); i++) {
                        Barang b = daftarBarang.get(i);
                        System.out.println("ID     : " + b.getId());
                        System.out.println("Nama   : " + b.getNama());
                        System.out.println("Stock  : " + b.getStok());
                        if (b instanceof Voucher v) {
                            System.out.println("Nominal: " + String.format("%.0f", v.getHarga()));
                            System.out.println("Harga  : " + String.format("%.0f", v.getHargaJual()));
                        } else {
                            System.out.println("Harga  : " + String.format("%.0f", b.getHarga()));
                        }
                        System.out.println("------------------------------------------");
                    }

                    System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
                    int id = sc.nextInt();
                    if (id == 0) break;

                    Barang barangDipilih = null;
                    for (int i = 0; i < daftarBarang.size(); i++) {
                        if (daftarBarang.get(i).getId() == id) {
                            barangDipilih = daftarBarang.get(i);
                            break;
                        }
                    }

                    if (barangDipilih == null) {
                        System.out.println("Pemilihan barang tidak sesuai");
                        break;
                    }

                    System.out.print("Masukkan Jumlah : ");
                    int jumlah = sc.nextInt();

                    if (jumlah <= 0 || jumlah > barangDipilih.getStok()) {
                        System.out.println("Stok tidak mencukupi jumlah pesanan");
                        break;
                    }

                    double totalHarga = (barangDipilih instanceof Voucher v)
                            ? v.getHargaJual() * jumlah
                            : barangDipilih.getHarga() * jumlah;

                    System.out.println(jumlah + " @ " + barangDipilih.getNama() +
                                       " dengan total harga " + String.format("%.0f", totalHarga));
                    System.out.print("Masukkan jumlah uang : ");
                    double uang = sc.nextDouble();

                    if (uang < totalHarga) {
                        System.out.println("Jumlah uang tidak mencukupi");
                        break;
                    }

                    barangDipilih.minusStok(jumlah);

                    Order pesanan;
                    if (barangDipilih instanceof Handphone h) {
                        pesanan = new Order("OH" + (Order.total + 1), h, jumlah);
                    } else {
                        pesanan = new Order("OV" + (Order.total + 1), (Voucher) barangDipilih, jumlah);
                    }
                    daftarPesanan.add(pesanan);

                    System.out.println("Berhasil dipesan");
                }

                case 2 -> {
                    System.out.println("Daftar Pesanan Toko Multiguna");
                    if (daftarPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan.");
                    } else {
                        for (int i = 0; i < daftarPesanan.size(); i++) {
                            Order o = daftarPesanan.get(i);
                            System.out.println("ID     : " + o.getId());
                            if (o.getHandphone() != null) {
                                System.out.println("Nama   : " + o.getHandphone().getNama());
                                System.out.println("Jumlah : " + o.getJumlah());
                                System.out.println("Total  : " + String.format("%.0f",
                                        o.getHandphone().getHarga() * o.getJumlah()));
                            } else {
                                System.out.println("Nama   : " + o.getVoucher().getNama());
                                System.out.println("Jumlah : " + o.getJumlah());
                                System.out.println("Total  : " + String.format("%.0f",
                                        o.getVoucher().getHargaJual() * o.getJumlah()));
                            }
                            System.out.println("------------------------------------------");
                        }
                    }
                }

                case 3 -> {
                    System.out.print("Voucher / Handphone (V/H): ");
                    String jenis = sc.next();

                    sc.nextLine(); 

                    System.out.print("Nama : ");
                    String nama = sc.nextLine(); 

                    System.out.print("Harga : ");
                    double harga = sc.nextDouble();
                    System.out.print("Stok : ");
                    int stok = sc.nextInt();

                    int idBaru = daftarBarang.size() + 1;

                    if (jenis.equalsIgnoreCase("v")) {
                        System.out.print("PPN : ");
                        double ppn = sc.nextDouble();
                        daftarBarang.add(new Voucher(idBaru, harga, nama, stok, ppn));
                        System.out.println("Voucher telah berhasil diinput");
                    } else if (jenis.equalsIgnoreCase("h")) {
                        System.out.print("Warna : ");
                        String warna = sc.next();
                        daftarBarang.add(new Handphone(idBaru, harga, nama, stok, warna));
                        System.out.println("Handphone telah berhasil diinput");
                    } else {
                        System.out.println("Jenis barang tidak valid!");
                    }
                }

                case 0 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 0);

        sc.close();
    }
}
