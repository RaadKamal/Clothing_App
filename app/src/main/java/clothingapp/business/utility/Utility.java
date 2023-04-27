package clothingapp.business.utility;

public class Utility {
    public static int clamp(int val, int min, int max){
        int clampedVal = val;

        if(clampedVal < min)
            clampedVal = min;
        if(clampedVal > max)
            clampedVal = max;

        return clampedVal;
    }
}
