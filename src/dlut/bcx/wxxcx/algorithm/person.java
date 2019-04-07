package dlut.bcx.wxxcx.algorithm;

public class person {
    public static final int current_pnum = 4;
    public static final double PI=3.1415926;
    public static final double EARTH_RADIUS=6378.137;//地球近似半径
    private double lat;//纬度
    private double lng;//经度
    private int kind;//阵营


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "person{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", kind=" + kind +
                '}';
    }

    person(double lat, double lng, int kind){
        this.lat=lat;
        this.lng=lng;
        this.kind=kind;
    }
}
