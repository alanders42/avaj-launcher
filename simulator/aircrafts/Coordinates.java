package simulator.aircrafts;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates (int longitudes, int latitudes, int heights) {
        if (longitudes < 0){ longitudes = 0;}
        if (latitudes < 0){ latitudes = 0;}
        if (height < 0){ height = 0;}
        if (height > 100){ height = 100;}
        this.longitude = longitudes;
        this.latitude = latitudes;
        this.height = heights;
    }

    public void updateCoordinates(int longitudes, int latitudes, int heights) {
        if (longitudes < 0){ longitudes = 0;}
        if (latitudes < 0){ latitudes = 0;}
        if (height < 0){ height = 0;}
        if (height > 100){ height = 100;}
        this.longitude = longitudes;
        this.latitude = latitudes;
        this.height = heights;
    }


    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
    
}