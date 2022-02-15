package entities.Scenery.groundTypes;

public class DarkGrass extends Ground{
    public DarkGrass(int x, int y, String var) {
        super(x, y);
        switch (var.charAt(0)){
            case 't' ->{
                ssRow = 3;
                switch (var) {
                    case "tl" -> ssCol = 38;
                    case "t" -> ssCol = 39;
                    case "tr" -> ssCol = 40;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
            case 'm' -> {
                ssRow = 4;
                switch (var) {
                    case "ml" -> ssCol = 38;
                    case "m" -> ssCol = 39;
                    case "mr" -> ssCol = 40;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
            case 'b' -> {
                ssRow = 5;
                switch (var) {
                    case "bl" -> ssCol = 38;
                    case "b" -> ssCol = 39;
                    case "br" -> ssCol = 40;
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
            case 'i' -> {
                switch (var) {
                    case "itl" -> {
                        ssCol = 38;
                        ssRow = 1;
                    }
                    case "itr" -> {
                        ssCol = 39;
                        ssRow = 1;
                    }
                    case "ibl" -> {
                        ssCol = 38;
                        ssRow = 2;
                    }
                    case "ibr" -> {
                        ssCol = 39;
                        ssRow = 2;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + var);
                }
            }
        }
        collideable=false;
        loadImage(imagePath);
    }
}
