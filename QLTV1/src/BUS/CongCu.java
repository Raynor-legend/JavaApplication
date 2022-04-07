/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static java.lang.Integer.valueOf;
import java.util.regex.Pattern;

/**
 *
 * @author Kieu Oanh
 */
public class CongCu {

    public static boolean checkGmail(String s) {
        String gmail = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        return Pattern.matches(gmail, s);
    }

    public static boolean checkPhoneNumes(String s) {
        String phoneNumes = "[03|05|07|08|09]{1}[0-9]{9}";
        return Pattern.matches(phoneNumes, s);
    }

    public static boolean checkNume(String s) {
        String nume = "[0-9]{1,4}";
        return Pattern.matches(nume, s);
    }

    public static boolean checkPrice(String s) {
        String num = "[0-9]{1,8}";
        return Pattern.matches(num, s);
    }

    public static String chuanHoa(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }

    public static String chuanHoaDanhTuRieng(String s) {
        s = chuanHoa(s);
        s.toLowerCase();
        String temp[] = s.split(" ");
        s = "";
        for (int i = 0; i < temp.length; i++) {
            s += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                s += " ";
            }
        }
        return s;
    }

    private static boolean checkma(String s) {
        String ma = "[a-zA-Z0-9]+$";
        return Pattern.matches("[a-zA-Z0-9]", s);
    }
    private static final String FULLNAME_PATTERN
            = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ"
            + "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ"
            + "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$";

    public static boolean checkNames(String fullname) {
        if (fullname == null) {
            return false;
        }
        return fullname.matches(FULLNAME_PATTERN);
    }

    public static boolean checkCMND(String s) {
        String cMND = "[0-9]{9}|[0-9]{12}";
        return Pattern.matches(cMND, s);
    }
    private static final String CHAR_PATTERN
            = "^[0-9a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ"
            + "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ"
            + "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$";

    public static boolean checkChar(String fullname) {
        if (fullname == null) {
            return false;
        }
        return fullname.matches(CHAR_PATTERN);
    }

    public static boolean isLength50(String name) {
        if (name.length() > 50 || name.length() < 1) {
            return false;
        }
        return true;
    }

    public static boolean isLength15(String name) {
        if (name.length() > 15 || name.length() < 1) {
            return false;
        }
        return true;
    }

    public static boolean isLength10(String name) {
        if (name.length() > 10 || name.length() < 1) {
            return false;
        }
        return true;
    }

    private static final String MA_PATTERN
            = "^[0-9a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ"
            + "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ"
            + "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$";

    public static boolean checkMa(String name) {
        if (name == null) {
            return false;
        }
        return name.matches(MA_PATTERN);
    }

    public static void main(String[] args) {
//        String a="10557 bvku89045t6y7uioÁDASDASDASDASDASDASDADASSKDFJHSDFJOIEJFLKSDJFOIEJFLKSDJFOISEJFLSKDJFOISJEFLKSJDFOISJEFLKSJDFOISJEFLKSJDFOISJDFLKJSOIEJFLKDJFOISJEFKSDJFOISJEFLKSDJFOISJEFLKDJFOSIJEFOIJW";
//        
//        
//        if(isLength50(a))
//            System.out.println("dung");
//        else System.out.println("sai");
        String a = "KH09";
        if (!checkma(a)) {
            System.out.println("Đúng");
        } else {
            System.out.println("Sai");
        }
    }
}
