
package hr.algebra.model;

public class OrderDetail {

    public OrderDetail(String productName, float subtotal, float shipping, float tax, float total) {
        this.productName = productName;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.tax = tax;
        this.total = total;
    }
	private String productName;
	private float subtotal;
	private float shipping;
	private float tax;
	private float total;



	public String getProductName() {
		return productName;
	}

	public String getSubtotal() {
		return String.format("%.2f", subtotal);
	}

	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}
	
	public String getTotal() {
		return String.format("%.2f", total);
	}
}
