//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
// Beim Positionieren werden immer Bruchteile von h,min,s beruecksichtigt 
public class Analoguhr implements ITuWas {

    Behaelter	uhr;
    Kreis			ring; 
    Linie			sekunde;
    Linie			minute;
    Linie			stunde;

    Taktgeber		takt;

    int				xPos	= 100;
    int				yPos	= 100;
    int				breit	= 100;
    int				hoch	= 100;
    int				mitteX	= 50;
    int				mitteY	= 50;
    int				radius	= 50;
    int				laengeS	= 50;
    int				laengeM	= (int) (50 * 0.75);
    int				laengeH	= (int) (50 * 0.5);

    public Analoguhr() {
        this(0, 0, 50);
    }

    public Analoguhr(int neuesX, int neuesY, int neuerRadius) {
        xPos = neuesX;
        yPos = neuesY;
        radius = neuerRadius;
        breit = 2 * radius;
        hoch = 2 * radius;
        mitteX = radius;
        mitteY = radius;
        laengeS = radius;
        laengeM = (int) (radius * 0.75);
        laengeH = (int) (radius * 0.5);

        uhr = new Behaelter(xPos, yPos, breit, hoch);

        ring = new Kreis(uhr, 0, 0, radius);
        ring.setzeFarbe("schwarz");
        ring.rand();

        stunde = new Linie(uhr, mitteX, mitteY, mitteX, mitteY - laengeH);
        stunde.setzeFarbe("schwarz");
        stunde.setzeLinienDicke(8);

        minute = new Linie(uhr, mitteX, mitteY, mitteX, mitteY - laengeM);
        minute.setzeFarbe("schwarz");
        minute.setzeLinienDicke(4);

        sekunde = new Linie(uhr, mitteX, mitteY, mitteX, mitteY - laengeS);
        sekunde.setzeFarbe("schwarz");
        sekunde.setzeLinienDicke(1);

        setzeSekunde(StaticTools.jetzt_Sekunde());
        setzeMinute(StaticTools.jetzt_Minute());
        setzeStunde(StaticTools.jetzt_Stunde());

        takt = new Taktgeber(this, 0);
        takt.endlos();
    }

    // Beim Positionieren werden immer h,min,s beruecksichtigt 
    int	sec	= 0;
    int	min	= 0;
    int	h	= 0;

    public void setzeSekunde(int neueSec) {
        sec = neueSec;
        double richtung = (-6 * sec + 90) * Math.PI / 180;
        int dx = (int) (Math.cos(richtung) * laengeS);
        int dy = -(int) (Math.sin(richtung) * laengeS);
        sekunde.setzeEndpunkte(mitteX, mitteY, mitteX + dx, mitteY + dy);
    }

    public void setzeMinute(int neueMin) {
        min = neueMin;
        double richtung = (-6 * (min+sec/60.0) + 90) * Math.PI / 180;
        int dx = (int) (Math.cos(richtung) * laengeM);
        int dy = -(int) (Math.sin(richtung) * laengeM);
        minute.setzeEndpunkte(mitteX, mitteY, mitteX + dx, mitteY + dy);
    }

    public void setzeStunde(int neueH) {
        h = neueH;
        double richtung = (-30 * (h+min/60.0+sec/3600.0) + 90 ) * Math.PI / 180;
        int dx = (int) (Math.cos(richtung) * laengeH);
        int dy = -(int) (Math.sin(richtung) * laengeH);
        stunde.setzeEndpunkte(mitteX, mitteY, mitteX + dx, mitteY + dy);
    }

    public void tuWas(int ID) {
        sec++;
        if (sec >= 60) {
            min++;
            sec = 0;
        }

        if (min >= 60) {
            min = 0;
            h++;
        }
        while(h>=12){
            h -=12;
        }
        setzeSekunde(sec);
        setzeMinute(min);
        setzeStunde(h);

    }

    public void setzePosition(int neuesX , int neuesY){
        uhr.setzePosition(neuesX, neuesY);
    }

    public static void main(String[] args) {
        Analoguhr u = new Analoguhr(50, 50, 50);
        u.setzePosition(200,200);
        // Zeichnung.setzeRasterEin();
    }

}
