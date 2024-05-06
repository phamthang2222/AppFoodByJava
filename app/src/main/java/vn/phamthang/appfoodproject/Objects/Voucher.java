package vn.phamthang.appfoodproject.Objects;

public class Voucher {
    private String id;
    private String code;
    private String value;

    public Voucher(String id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }

    public Voucher() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
