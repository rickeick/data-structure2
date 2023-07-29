package entities;
public class Produto {
    Integer item_id;
    String title;
    String domain_id;
    Integer product_id;
    Float price;
    String category_id;
    String condition;

    public Produto(
        Integer item_id,
        String title,
        String domain_id,
        Integer product_id,
        Float price,
        String category_id,
        String condition
    ) {
        this.item_id = item_id;
        this.title = title;
        this.domain_id = domain_id;
        this.product_id = product_id;
        this.price = price;
        this.category_id = category_id;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return
            "item_id: "+item_id+"\n"+
            "title: "+title+"\n"+
            "domain_id: "+domain_id+"\n"+
            "product_id: "+product_id+"\n"+
            "price: "+price+"\n"+
            "category_id: "+category_id+"\n"+
            "condition: "+condition+"\n";
    }
}
