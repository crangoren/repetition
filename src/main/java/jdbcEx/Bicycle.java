package jdbcEx;

public class Bicycle {
    private final int id;
    private final String model;
    private final String serialNo;

    public Bicycle(int id, String model, String serialNo) {
        this.id = id;
        this.model = model;
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", serial='" + serialNo + '\'' +
                '}';
    }
}
