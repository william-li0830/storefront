package buyable;

/**
 *
 * @author willi
 */
public class BuyableElectronics extends Buyable {
    private String brandName;
    
    public BuyableElectronics(double price, String name, String brand){
        super(price, name, "Electronics");
        this.brandName=brand;
    }
    public String getBrandName(){
        return brandName;
    }
}
