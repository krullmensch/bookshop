public class Pruefer {

    private static boolean isDigit(char c) {
        String N = "0123456789";
        return N.indexOf(c) != -1;
    }

    private static boolean isAlphabetic(char c) {
        String A = "abcdegfhijklmnopqrstuvwxyzßABCDEFGHIJKLMNOPSTUVWXYZäöüÄÖÜ";
        return A.indexOf(c) != -1;
    }

    private static boolean isIllegalChar(char c){
        String A = ":/;";
        return A.indexOf(c) != -1;
    }

    public static boolean isPreis(String s){
        if(isInteger(s) || isDouble(s)) return true;
        else return false;
    }

    public static boolean isInteger(String s) {
        boolean isInt = true;
        for (int i = 0; i < s.length(); i++) {
            isInt = isInt & (s.charAt(i) >= '0') & (s.charAt(i) <= '9');
        }
        return isInt;
    }

    private static boolean isDouble(String s) {
        boolean isFloat = true;
        if (s.indexOf('.') == -1) return false;
        else {
            int posKomm = s.indexOf('.'); //die erste Position eines Kommas wird gespeichert
            for (int i = 0; i < s.length(); i++) {
                if (i < posKomm || i > posKomm && s.charAt(i) != '.') {
                    isFloat = isFloat & (s.charAt(i) >= '0') & (s.charAt(i) <= '9');
                } else if (i == posKomm) isFloat = true;
                else return false;
            }
            return isFloat;
        }
    }

    public static boolean checkField(String s){
        if(s.equals("")) return false;
        else {
            boolean valid = true;
            for (int i = 0; i < s.length(); i++) {
                if(isIllegalChar(s.charAt(i))) valid = false;
            }
            return valid;
        }
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
