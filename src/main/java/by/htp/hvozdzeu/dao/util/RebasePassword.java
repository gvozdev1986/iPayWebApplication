package by.htp.hvozdzeu.dao.util;

public final class RebasePassword {

    public char[] rebasePSWD(String s){
        return s.toCharArray();
    }

    public String rebasePSWD(char[] s){
        return String.valueOf(s);
    }

}

