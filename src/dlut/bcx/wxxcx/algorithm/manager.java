package dlut.bcx.wxxcx.algorithm;

import java.util.Scanner;

import static java.lang.Math.*;

public class manager {

    public static final int current_pnum = 4;
    public static double PI=3.1415926;
    public static double EARTH_RADIUS=6378.137;//地球近似半径
    // 求弧度(经纬度算距离用)
    double get_radian(double d){
        return d * PI / 180.0;   //角度1˚ = π / 180
    }
    //用经纬度计算距离
    double get_distance(double lat1, double lng1, double lat2, double lng2){
        //lat纬度,getLng() 经度
        double radLat1 = get_radian(lat1);
        double radLat2 = get_radian(lat2);
        double a = radLat1 - radLat2;
        double b = get_radian(lng1) - get_radian(lng2);

        double dst = 2 * asin((sqrt(pow(sin(a / 2), 2) + cos(radLat1) * cos(radLat2) * pow(sin(b / 2), 2) )));

        dst = dst * EARTH_RADIUS;
        dst= round(dst * 10000) / 10000;
        return dst;
    }

    //初始化距离矩阵
    void init_distance(point []a, double[][] distance_matrix){System.out.println("距离矩阵:");
        for (int pa=0;pa < current_pnum ; pa++) {
            for (int pb=0; pb < current_pnum; pb++) {
                if(pa!=pb)
                    //经纬度算距离
                    distance_matrix[pa][pb] = get_distance(a[pa].getLat(),a[pa].getLng(),a[pb].getLat(),a[pb].getLng());
                else
                    distance_matrix[pa][pb]=0;//自己和自己的距离为0
                    System.out.print(distance_matrix[pa][pb]+"\t");
            }
            System.out.println();
        }
    }

    //从人数计算半径
    void get_r_from_num(point[] a){System.out.println("半径:");
        for (int i=0; i<current_pnum; i++) {
            a[i].setR(a[i].getPeople_num()*0.01+10);
            System.out.print(a[i].getR()+"\t");
        }
        System.out.println();
    }
    //改变阵营
    void change_kind(point[] a) {
        System.out.println("阵营:");
        for (int i = 0; i < current_pnum; i++) {
            if (a[i].getPeople_num() < 0) {
                a[i].setKind(a[i].getKind()==2 ? 1 : 2);
                a[i].setPeople_num(0);
            }
            System.out.print(a[i].getKind()+"\t");
        }
        System.out.println();
    }
        //改变碰撞矩阵
    void change_crash(point[] a, boolean[][] crash, double[][] r_sum, double[][] distance) {
        //如果两个不同阵营的点的半径之和超过距离就使他们crash矩阵上的值
        System.out.println("碰撞矩阵:");
        for (int i = 0; i < current_pnum; ++i) {
            for (int j = 0; j < current_pnum; ++j) {
                if (i != j && a[i].getKind() != a[j].getKind() && r_sum[i][j] >= distance[i][j]) {
                    //不是自己和自己&&不是同阵营&&半径和>=距离
                    crash[i][j] = true;
                } else {
                    crash[i][j] = false;
                }
                System.out.print(crash[i][j]+"\t");
            }
            System.out.println();
        }
    }
            //计算半径和矩阵
    void calculate_r_sum(point[] a, double[][] r_sum) {
        System.out.println("半径和:");
        for (int i = 0; i < current_pnum; ++i) {
            for (int j = 0; j < current_pnum; ++j) {
                if (i != j) r_sum[i][j] = a[i].getR() + a[j].getR();
                else r_sum[i][j] = 0; //不计算自己和自己的半径
                System.out.print(r_sum[i][j]+"\t");
            }
            System.out.println();
        }
    }
    //访问人数改变(根据不同阵营)
    void change_visit_num(point [] a, boolean[][] crash, person p){
        System.out.println("人数:");
        for (int i = 0; i < current_pnum; ++i) {
            if (get_distance(a[i].getLat(), a[i].getLng(), p.getLat(), p.getLng()) <= a[i].getR()) {//人在圈里了
                System.out.print("人在第"+i+"个圈里\t");
                if (a[i].getKind() == p.getKind()) {//如果阵营相同
                    a[i].setKind(p.getKind());
                    a[i].setPeople_num(a[i].getPeople_num()+1);
                    for (int j = 0; j < current_pnum; ++j) {//如果有碰撞的
                        if (crash[i][j] == true) {
                            a[i].setPeople_num(a[i].getPeople_num()-1);
                        }
                    }
                }else if(a[i].getKind()==0){//点初始不带阵营
                    a[i].setKind(p.getKind());
                    a[i].setPeople_num(a[i].getPeople_num()+1);
                }
                else {//阵营不同
                    a[i].setPeople_num(a[i].getPeople_num() - 1);
                }
                System.out.print(a[i].getPeople_num()+"\t");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean flag=true;
        boolean crash[][]=new boolean[current_pnum][current_pnum];//碰撞矩阵,两个不同阵营矩阵相碰,置一
        double distance[][]=new double[current_pnum][current_pnum];//距离矩阵:一个圆心到其它圆心的距离
        double r_sum[][]=new double[current_pnum][current_pnum];//半径和矩阵
        point [] points= {
                new point(1,100,200,0,1000000),//id,纬,经,半径,阵营
                new point(2,200,300,0,2000000),
                new point(3,500,500,0,3000000),
                new point(4,1000,1000,0,4000000)
        };
        Scanner in =new Scanner(System.in);
        person p = new person(100,100,1);
        manager h1 = new manager();

        h1.init_distance(points,distance);//就算一次就行了
        while (flag) {
            h1.get_r_from_num(points);
            h1.calculate_r_sum(points, r_sum);
            h1.change_crash(points, crash, r_sum, distance);
            h1.change_visit_num(points, crash, p);
            h1.change_kind(points);
            for (int i = 0; i < current_pnum; i++) {
                System.out.println(points[i].toString());
            }
            flag = in.nextInt() == 1;

        }
    }

}
