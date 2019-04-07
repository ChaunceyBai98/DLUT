package dlut.bcx.wxxcx.algorithm;

public class point {
    //固定点结构体
    public static final int current_pnum = 4;
    public static double PI=3.1415926;
    public static double EARTH_RADIUS=6378.137;//地球近似半径
    private int id;
    private double lat;//纬度
    private double lng;//经度
    private double r;//半径
    private int kind;//阵营
    private int people_num;//来访人数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",lat:" + lat +
                ",lng:" + lng +
                ",r:" + r +
                ",kind:" + kind +
                ",people_num:" + people_num +
                '}';
    }

    public point(int id, double lat, double lng, int kind, int people_num) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.r = 0;
        this.kind = kind;
        this.people_num = people_num;
    }
}
