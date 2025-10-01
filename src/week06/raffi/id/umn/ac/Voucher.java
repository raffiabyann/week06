package week06.raffi.id.umn.ac;

public class Voucher extends Barang {
    private double pajak;
    public static int total = 0;

    public Voucher(int id, double harga, String nama, int stok, double pajak) {
        super(id, harga, nama, stok);
        this.pajak = pajak;
        total++;
    }

    public double getPajak() { 
    	return pajak; }
    public double getHargaJual() { 
    	return harga + pajak; }
}

