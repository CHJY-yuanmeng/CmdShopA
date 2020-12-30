package pojo;

public class Product {
private String productId;
private String productName;
private float productPrice;
private String productDsec;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDsec() {
        return productDsec;
    }

    public void setProductDsec(String productDsec) {
        this.productDsec = productDsec;
    }

    @Override
    public String toString() {
        int len=productName.length();
        String nullStr="                   ";
        String Str=nullStr.substring(len);//空格填充物

        return "" +
                "商品ID='" + productId + '\'' +nullStr.substring(productId.length())+
                "商品名称='" + productName + '\'' +nullStr.substring(productName.length())+
                "价格=" + productPrice +nullStr.substring(String.valueOf(productPrice).length())+
                "描述='" + productDsec + '\'' +nullStr.substring(productDsec.length())
                ;
    }
}
