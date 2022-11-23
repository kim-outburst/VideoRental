package VideoRental.model;

public enum VideoType {
    VHS, CD, DVD;

    public static VideoType convert(int videoType) {
        VideoType type = VHS;
        switch (videoType) {
            case 1:
                type = VHS;
                break;
            case 2:
                type = CD;
                break;
            case 3:
                type = DVD;
                break;
        }
        return type;
    }
}
