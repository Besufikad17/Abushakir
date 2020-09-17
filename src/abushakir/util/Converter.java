package abushakir.util;

import java.util.ArrayList;

import static abushakir.util.Constants.geezNumbers;

public class Converter {
    public ArrayList<Integer> divide(int denominator, int numinator){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(numinator/denominator);
        result.add(numinator % denominator);
        return result;
    }

    public String convert_1_10_ToGeez(int num) throws Calander_Exceptions.EthiopicNumberException {
        if(num < 1){
            throw new Calander_Exceptions.EthiopicNumberException("Zero (0) and Negative numbers doesn't exsit in" +
                    " Ethiopic numerals");
        }else{
            return geezNumbers.get(num);
        }
    }

    public String convert_11_100_ToGeez(int num) throws Calander_Exceptions.EthiopicNumberException {
        String number = "";
        if(num == 100){
            return geezNumbers.get(num);
        }else{
            ArrayList<Integer> result = divide(10,num);
            if (result.get(1) > 0){
                number = geezNumbers.get(result.get(0) * 10) + geezNumbers.get(result.get(1));
            }else{
                number = geezNumbers.get(result.get(0) * 10);
            }
        }
        return number;
    }

    public String convert_111_1000_ToGeez(int num) throws Calander_Exceptions.EthiopicNumberException {
        ArrayList<Integer> result = divide(100,num);
        String number = "";
        if (result.get(1) == 0){
            return geezNumbers.get(result.get(0)) + geezNumbers.get(100);
        }
        String right = result.get(0) == 1 ? geezNumbers.get(100) :
                geezNumbers.get(result.get(0)) + geezNumbers.get(100);
        String left = result.get(1) <= 10 ? convert_1_10_ToGeez(result.get(1)) :
                convert_1_10_ToGeez(result.get(1));
        return right + left;
    }

    public String convertToGeez(int num) throws Calander_Exceptions.EthiopicNumberException {
        String geezNumber = "";
        if (num < 1){
            throw new Calander_Exceptions.EthiopicNumberException("Zero (0) and Negative numbers doesn't exsit in" +
                    " Ethiopic numerals");
        }else if (num > 1 && num < 10){
            geezNumber = convert_1_10_ToGeez(num);
        }else if (num > 10 && num < 100){
            geezNumber = convert_11_100_ToGeez(num);
        }else if (num > 100 && num < 1000){
            geezNumber = convert_111_1000_ToGeez(num);
        }else if (num > 1000 && num < 10000){
            ArrayList<Integer> result = divide(100,num);
            String number = "";
            if (result.get(1) == 0){
                return result.get(0) < 11 ?
                        geezNumbers.get(result.get(0)) + geezNumbers.get(100) :
                        convert_11_100_ToGeez(result.get(0)) + geezNumbers.get(100);
            }
            String right = result.get(1) < 11 ? convert_1_10_ToGeez(result.get(1)) :
                    convert_11_100_ToGeez(result.get(1));
            String left = convert_11_100_ToGeez(result.get(0));
            geezNumber =  left + geezNumbers.get(100) +right;
        }
        return geezNumber;
    }
}
