public class Pruefer {

    private static boolean isDigit(char c) {
        String N = "0,1,2,3,4,5,6,7,8,9";
        return N.indexOf(c) != -1;
    }

    private static boolean isAlphabetic(char c) {
        String A = "a,b,c,d,e,g,f,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        return A.indexOf(c) != -1;
    }

    public static boolean isInteger(String s) {
        boolean isInt = true;
        for (int i = 0; i < s.length(); i++) {
            isInt = isInt & (s.charAt(i) >= '0') & (s.charAt(i) <= '9');
        }
        return isInt;
    }


    public static boolean emailCheck(String code) {
        if (code.equals("")) {
            return false;
        } else {
            return emailCheckAB(code, 0);
        }
    }

    //A::= aB...zB|0B...9B
    //B::= aB...zB|0B...9B|@CCCD
    private static boolean emailCheckAB(String code, int pos) {
        if (Pruefer.isAlphabetic(code.charAt(pos)) || Pruefer.isDigit(code.charAt(pos))) {
            return emailCheckAB(code, pos + 1);
        } else if (pos != 0 && code.charAt(pos) == '@') {
            return emailCheckCD(code, pos + 1, pos + 4);
        } else {
            return false;
        }
    }

    //C::= aC...zC|0C...9C|a...z|0...9
    //D::= .de|.com
    private static boolean emailCheckCD(String code, int pos, int posMin) {
        if (Pruefer.isAlphabetic(code.charAt(pos)) || Pruefer.isDigit(code.charAt(pos))) {
            return emailCheckCD(code, pos + 1, posMin);
        } else if (pos >= posMin) {
            if (code.substring(pos).equals(".de") && pos + 3 == code.length()) {
                return true;
            } else if (code.substring(pos).equals(".com") && pos + 4 == code.length()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }



}
