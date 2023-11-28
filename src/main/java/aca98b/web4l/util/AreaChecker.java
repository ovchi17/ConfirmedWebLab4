package aca98b.web4l.util;

public class AreaChecker {
    public static boolean getResult(float x, float y, float r){
        boolean resultF = false;
        // 1st quarter - small circle quarter
        if (x >= 0 && y >= 0) {
            if (x <= r/2 && y <= r/2 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2, 2))) { //TODO: проверить!
                resultF = true;
            }
        }

        // 2nd quarter - horizontal rectangle
        if (x <= 0 && y >= 0) {
            if (y <= r / 2 && x >= -1 * r) {
                resultF = true;
            }
        }

        // 3rd quarter - big triangle
        if (x <= 0 && y <= 0) {
            if (x >= -r && y >= -r && x + y >= -r){
                resultF = true;
            }
        }

        return resultF;
    }
}