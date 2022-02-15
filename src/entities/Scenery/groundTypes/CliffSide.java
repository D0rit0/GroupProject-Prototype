package entities.Scenery.groundTypes;

public class CliffSide extends Ground{

    public CliffSide(int x, int y,String var) {
        super(x, y);
        switch (var.charAt(0)){
            case 't' ->{
                ssRow = 7;
                switch (var) {
                    case "tl" -> ssCol = 1;
                    case "t" -> ssCol = 2;
                    case "tr" -> ssCol = 3;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
            case 'm' -> {
                ssRow = 8;
                switch (var) {
                    case "ml" -> ssCol = 1;
                    case "mr" -> ssCol = 3;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
            case 'b' -> {
                ssRow = 9;
                switch (var) {
                    case "bl" -> ssCol = 1;
                    case "b" -> ssCol = 2;
                    case "br" -> ssCol = 3;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
        }
        collideable=true;
        loadImage("src\\resources\\atlas_32x.png");
    }
}
