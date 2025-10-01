package week06.raffi.id.umn.ac;

public class Handphone extends Barang {
    private String warna;
    public static int total = 0;

    public Handphone(int id, double harga, String nama, int stok, String warna) {
    	super(id, harga, nama, stok);
        this.warna = warna;
        total++;
    }

    public String getWarna() { 
    	return warna; }
}