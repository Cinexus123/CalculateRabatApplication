package products;

public enum AvailableProducts {

    PRODUCTS1(1L,"Trousers",500.0),
    PRODUCTS2(2L,"Shirt",700.0),
    PRODUCTS3(3L,"Hat",900.0),
    PRODUCTS4(4L,"Shoes",1100.0),
    PRODUCTS5(5L,"Coat",1300.0);


    private final Long id;
    private final String product;
    private final Double price;

    AvailableProducts(Long id, String product, Double price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }
}
