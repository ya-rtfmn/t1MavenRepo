package schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartRequest {
    @JsonProperty("product_id")
    private int productId;
    private int quantity;

    public CartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
