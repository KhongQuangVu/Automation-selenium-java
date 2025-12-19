package javaTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Topic_01_DataType {
    // Kiểu dữ liệu nguyên thủy (Primitive Type)
    // Ký tự
    //char
    //char c = 'K';

    //Số nguyên
    //byte : 128 to 127
    //byte bNumber = 127;

    // short
    //short sNumber = 32767;

    // int
    //int iNumber = 64536;

    // long
    //long lNumber = 922337;

    // Số thực
    // float
    //float fNumber = 9.57f;

    //double
    //double dNumber = 99.5d;

    // Logic : Giới tính/ câu trả lời...
    //boolean
    //boolean sex = true;

    // Kiểu dữ liệu tham chiếu (Reference Type)
    // Chuỗi
    // String
    String fullName = " ";

    //Array
    //String[] student = {"Khong Quang Vũ",
    //        "Khong",
    //        "Quang",
    //        "Vu"};

    // Collection/ List/ Set/ Queue
    //List<String> studentAddress = new ArrayList<String>();

    // Class/ Object
    //FirefoxDriver ffDriver = new FirefoxDriver();

    public static void main(String[] args){
        int i = 10;
        int x = i;
        System.out.println(x);
        System.out.println(i);

        i = 100;
        System.out.println(x);
        System.out.println(i);

        Topic_01_DataType topic01 = new Topic_01_DataType();
        topic01.fullName = "Khong Quang A";

        Topic_01_DataType topic02 = topic01;
        System.out.println(topic01.fullName);
        System.out.println(topic02.fullName);

        topic01.fullName = "Khong Quang B";
        System.out.println(topic01.fullName);
        System.out.println(topic02.fullName);
    }
}
