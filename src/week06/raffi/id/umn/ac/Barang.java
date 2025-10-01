package week06.raffi.id.umn.ac;

public class Barang {
	protected int id;
	protected double harga;
	protected String nama;
	protected int stok;
	
	public Barang (int id, double harga, String nama, int stok) {
		this.id=id;
		this.harga=harga;
		this.nama=nama;
		this.stok=stok;
	}
	public int getId() {
		return id;
	}
	public double getHarga() {
		return harga;
	}
	public String getNama() {
		return nama;
	}
	public int getStok() {
		return stok;
	}
	public void minusStok (int jumlah) {
		if (stok >= jumlah) {
			stok -= jumlah;
		}
	}
}
